package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.entity.device.DevicesKeepTeam;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesKeepItemDao;
import com.fw.service.device.dao.DevicesKeepTeamDao;
import com.fw.service.device.service.DevicesKeepTeamService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 保养项目组 服务实现类
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Service
public class DevicesKeepTeamServiceImpl implements DevicesKeepTeamService {

    @Autowired
    private DevicesKeepTeamDao devicesKeepTeamDao;

    @Autowired
    private DevicesKeepItemDao devicesKeepItemDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Override
    @Transactional
    public Result save(DevicesKeepTeam devicesKeepTeam) {
        Integer flag = 0;
        //新增
        if (CheckUtils.isNull(devicesKeepTeam.getId())) {
            //名称不为空校验是否重复
            if (!CheckUtils.isNull(devicesKeepTeam.getName())) {
                boolean res = devicesKeepTeamDao.verifyName(devicesKeepTeam.getName());
                if(res){
                    return ResultUtils.error("保养表单名称重复");
                }
            }
            flag = devicesKeepTeamDao.insert(devicesKeepTeam);
        }else {
            DevicesKeepTeam keepTeam = devicesKeepTeamDao.selectById(devicesKeepTeam.getId());
            if (keepTeam == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            //修改时表单名称与传过来名称不通的时候进行校验
            if(!devicesKeepTeam.getName().equals(keepTeam.getName())){
                //校验是否有重复的表单名称
                boolean res = devicesKeepTeamDao.verifyName(devicesKeepTeam.getName());
                if(res){
                    return ResultUtils.error("保养表单名称重复");
                }
            }
            flag = devicesKeepTeamDao.update(devicesKeepTeam);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getDevicesKeepTeam(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesKeepTeam keepTeam = devicesKeepTeamDao.selectById(id);
        if (keepTeam == null) {
            return ResultUtils.success(keepTeam);
        }
        String keepItemIds = keepTeam.getKeepItemIds();
        //设备保养项目ids不为空,则获取设备保养项目的集合
        if (!CheckUtils.isNull(keepItemIds)) {
            List<String> ids = Arrays.asList(keepItemIds.split(","));
            List<DevicesKeepItem> items = devicesKeepItemDao.findKeepItemByIds(ids);
            keepTeam.setKeepItems(items);
        }
        List<DevicesClassify> productDevices = e2CServicesUtil.getdevicesClassify(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(productDevices)) {
            productDevices.forEach(vo -> {
                if (keepTeam.getDevicesClassify().equals(vo.getId())) {
                    keepTeam.setClassifyName(vo.getClassifyName());
                }
            });
        }
        return ResultUtils.success(keepTeam);
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        flag = devicesKeepTeamDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getAllKeepItem() {
        return ResultUtils.success(devicesKeepItemDao.findAll());
    }

    @Override
    public Result findList(String name, String devicesClassify, String devicesCode, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //查询E2C生产设备
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", devicesCode);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(), params);
        List<String> productDevicesIds = null;
        if (!CheckUtils.isNull(devicesCode)) {
            //如果设备编号查询条件不为空，根据设备编号查询结果为空，则直接返回空，如果不为空，则根据设备id进行数据查询
            if (productDevices == null || productDevices.size() == 0) {
                return ResultUtils.success(new PageInfo<>());
            } else {
                productDevicesIds = productDevices.stream().map(DevicesItemVo::getId).collect(Collectors.toList());
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DevicesKeepTeam> lists = devicesKeepTeamDao.findList(name, devicesClassify, productDevicesIds);
        PageInfo<DevicesKeepTeam> keepTeamPage = new PageInfo<>(lists);
        return ResultUtils.success(keepTeamPage);
    }

    @Override
    public Result getKeepItemByCycle(Integer cycle) {
        if (CheckUtils.isNull(cycle)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<DevicesKeepItem> lists = devicesKeepItemDao.getKeepItemByCycle(cycle);
        return ResultUtils.success(lists);
    }
}
