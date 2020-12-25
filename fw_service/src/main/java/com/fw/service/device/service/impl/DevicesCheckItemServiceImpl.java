package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesCheckItemDao;
import com.fw.service.device.service.DevicesCheckItemService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@Service
public class DevicesCheckItemServiceImpl implements DevicesCheckItemService {

    @Autowired
    private DevicesCheckItemDao devicesCheckItemDao;


    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;


    @Override
    public Result save(DevicesCheckItem devicesCheckItem) {
        Integer flag = 0;
        if (devicesCheckItemDao.isRepeatName(devicesCheckItem)>0) {
            return ResultUtils.error("点检项目名称重复");
        }
        //id为空新增设备保养项目
        if (CheckUtils.isNull(devicesCheckItem.getId())) {
            flag = devicesCheckItemDao.insert(devicesCheckItem);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            DevicesCheckItem checkItem = devicesCheckItemDao.selectById(devicesCheckItem.getId());
            if (CheckUtils.isNull(checkItem)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = devicesCheckItemDao.update(devicesCheckItem);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        flag = devicesCheckItemDao.delete(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, String devicesClassify, String content,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DevicesCheckItem> lists = devicesCheckItemDao.findList(name, devicesClassify,content);
        List<DevicesClassify> devicesClassifies = e2CServicesUtil.getdevicesClassify(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(devicesClassifies) && !CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                devicesClassifies.forEach(vo -> {
                    if (!CheckUtils.isNull(item.getDevicesClassify())&&item.getDevicesClassify().equals(vo.getId())) {
                        item.setClassifyName(vo.getClassifyName());
                    }
                });
            });
        }
        PageInfo<DevicesCheckItem> CheckItemPage = new PageInfo<>(lists);
        return ResultUtils.success(CheckItemPage);
    }



}
