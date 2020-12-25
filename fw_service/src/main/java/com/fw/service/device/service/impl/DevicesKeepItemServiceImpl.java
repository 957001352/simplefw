package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.entity.device.KeepItemExtension;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesKeepItemDao;
import com.fw.service.device.dao.KeepItemExtensionDao;
import com.fw.service.device.service.DevicesKeepItemService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 保养项目 服务实现类
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Service
public class DevicesKeepItemServiceImpl implements DevicesKeepItemService {

    @Autowired
    private DevicesKeepItemDao devicesKeepItemDao;

    @Autowired
    private KeepItemExtensionDao keepItemExtensionDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Value("${attachment.path}")
    private String attachmentPath;


    @Override
    @Transactional
    public Result save(DevicesKeepItem devicesKeepItem) {
        Integer flag = 0;
        //id为空新增设备保养项目
        if (CheckUtils.isNull(devicesKeepItem.getId())) {
            //名称不为空校验是否重复
            if (!CheckUtils.isNull(devicesKeepItem.getName())) {
                boolean res = devicesKeepItemDao.verifyName(devicesKeepItem.getName());
                if(res){
                    return ResultUtils.error("保养项目名称重复");
                }
            }
            flag = devicesKeepItemDao.insert(devicesKeepItem);
            //新增成功而且扩展数据存在,则批量插入扩展数据
            if (flag > 0 && !CollectionUtils.isEmpty(devicesKeepItem.getLists())) {
                List<KeepItemExtension> lists = devicesKeepItem.getLists();
                lists.forEach(i -> i.setKeepItemId(devicesKeepItem.getId()));
                flag = keepItemExtensionDao.batchInset(lists);
            }
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            DevicesKeepItem keepItem = devicesKeepItemDao.selectById(devicesKeepItem.getId());
            if (keepItem == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            //修改时表单名称与传过来名称不通的时候进行校验
            if(!devicesKeepItem.getName().equals(keepItem.getName())){
                //校验是否有重复的表单名称
                boolean res = devicesKeepItemDao.verifyName(devicesKeepItem.getName());
                if(res){
                    return ResultUtils.error("保养项目名称重复");
                }
            }
            flag = devicesKeepItemDao.update(devicesKeepItem);
            if (flag > 0 && !CollectionUtils.isEmpty(devicesKeepItem.getLists())) {
                keepItemExtensionDao.batchUpdate(devicesKeepItem.getLists());
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getDevicesKeepItem(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesKeepItem keepItem = devicesKeepItemDao.selectById(id);
        if (keepItem != null) {
            List<KeepItemExtension> itemExtension = keepItemExtensionDao.selectByKeepItemId(id);
            keepItem.setLists(itemExtension);
        }
        return ResultUtils.success(keepItem);
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        flag = devicesKeepItemDao.delete(lists);
        keepItemExtensionDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, String devicesClassify, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DevicesKeepItem> lists = devicesKeepItemDao.findList(name, devicesClassify);
        List<DevicesClassify> productDevices = e2CServicesUtil.getdevicesClassify(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(productDevices) && !CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                productDevices.forEach(vo -> {
                    //设备保养的设备类型id与E2C平台设备的id相等,则给设备保养设置一个设备名称用于列表展示数据
                    if (item.getDevicesClassify().equals(vo.getId())) {
                        item.setDevicesName(vo.getClassifyName());
                    }
                });
            });
        }
        PageInfo<DevicesKeepItem> keepItemPage = new PageInfo<>(lists);
        return ResultUtils.success(keepItemPage);
    }

    @Override
    public Result getDevicesClassify() {
        return ResultUtils.success(e2CServicesUtil.getProductDevices(headerUtil.cloudToken()));
    }


}
