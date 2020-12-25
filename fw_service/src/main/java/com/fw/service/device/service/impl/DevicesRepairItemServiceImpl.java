package com.fw.service.device.service.impl;


import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.device.DevicesRepairItem;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesRepairItemDao;
import com.fw.service.device.service.DevicesRepairItemService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: dhlk_fw_plat
 * @description: 设备维修项目业务逻辑层
 * @author: wqiang
 * @create: 2020-10-20 14:59
 **/
@Service
public class DevicesRepairItemServiceImpl implements DevicesRepairItemService {

    @Autowired
    private DevicesRepairItemDao devicesRepairItemDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Override
    public Result save(DevicesRepairItem devicesRepairItem) {

        Integer flag = 0;
        if (CheckUtils.isNull(devicesRepairItem.getId())) {
            DevicesRepairItem dri = devicesRepairItemDao.findItemById(null, devicesRepairItem.getName());
            if (dri != null) {
                return ResultUtils.error("项目名称已存在，请重新输入");
            }
            flag = devicesRepairItemDao.insert(devicesRepairItem);
        } else {
            DevicesRepairItem drm = devicesRepairItemDao.findItemById(devicesRepairItem.getId(), null);
            if (drm == null) {
                return ResultUtils.error("修改失败，请刷新页面");
            }
            if (devicesRepairItemDao.findItemByIdAndName(devicesRepairItem.getId(), devicesRepairItem.getName()) > 0) {
                return ResultUtils.error("项目名称已存在，请重新输入");
            }
            flag = devicesRepairItemDao.update(devicesRepairItem);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, String devicesClassify, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询E2C生产设备
        Map<String, String> params = new HashMap<String, String>();
        params.put("classifyId", devicesClassify);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(), params);

        List<DevicesRepairItem> repairItemList = devicesRepairItemDao.findList(name, devicesClassify);
        if (!CollectionUtils.isEmpty(productDevices) && !CollectionUtils.isEmpty(repairItemList)) {
            repairItemList.forEach(item -> {
                productDevices.forEach(vo -> {
                    if (item.getDevicesClassify().equals(vo.getClassifyId())) {
                        item.setDevicesName(vo.getClassifySet().getClassifyName());
                    }
                });
            });
        }
        PageInfo<DevicesRepairItem> devicesRepairPage = new PageInfo<>(repairItemList);
        return ResultUtils.success(devicesRepairPage);
    }

    @Override
    public Result findDecicesList() {
        return ResultUtils.success(e2CServicesUtil.getProductDevices(headerUtil.cloudToken()));
    }


    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        return devicesRepairItemDao.delete(lists) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
