package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.collect.DeviceState;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.produce.ProduceDuty;
import com.fw.entity.produce.ProduceReworkMonitor;
import com.fw.enums.ResultEnum;
import com.fw.service.RedisService;
import com.fw.service.collect.dao.DeviceStateDao;
import com.fw.service.produce.dao.BarcodeQueryDao;
import com.fw.service.produce.dao.ProduceDutyDao;
import com.fw.service.produce.service.BarcodeQueryService;
import com.fw.service.produce.service.ProduceDutyService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.FwConst;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 15:54
 **/


@Service
public class BarcodeQueryServiceImpl implements BarcodeQueryService {

    @Autowired
    private BarcodeQueryDao barcodeQueryDao;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private DeviceStateDao deviceStateDao;
    @Autowired
    private RedisService redisService;
    @Override
    public Result findProductProcessList() {
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        List<LinkedHashMap<String,Object>> list=barcodeQueryDao.findProductProcessList();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                if(!CollectionUtils.isEmpty(productList)){
                    productList.forEach(vo -> {
                        if (item.get("productDevicesId")!=null&&item.get("productDevicesId").toString().equals(vo.getId())) {
                            item.put("productDevicesName",vo.getName());
                            item.put("productDevicesCode",vo.getCode());
                        }
                    });
                }
                //用户设置
                if(!CollectionUtils.isEmpty(userList)){
                    userList.forEach(vo -> {
                        if (item.get("createUser")!=null && item.get("createUser").equals(vo.getId())) {
                            item.put("createUserName",vo.getName());
                        }
                    });
                }
            });
        }
        return ResultUtils.success(list);
    }

    @Override
    public Result findMoldingInjectionList(String productOrder, String partsCode, String partsName, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<LinkedHashMap<String,Object>> list=barcodeQueryDao.findMoldingInjectionList(productOrder,partsCode,partsName);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                if(!CollectionUtils.isEmpty(productList)){
                    productList.forEach(vo -> {
                        if (item.get("productDevicesId")!=null&&item.get("productDevicesId").toString().equals(vo.getId())) {
                            item.put("productDevicesName",vo.getName());
                            item.put("productDevicesCode",vo.getCode());
                        }
                    });
                }
                //用户设置
                if(!CollectionUtils.isEmpty(userList)){
                    userList.forEach(vo -> {
                        if (item.get("createUser")!=null && item.get("createUser").equals(vo.getId())) {
                            item.put("createUserName",vo.getName());
                        }
                    });
                }
            });
        }
        return ResultUtils.success(new PageInfo<>(list));
    }

    @Override
    public Result findNowProductOrder(Integer productDevicesId) {
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        List<LinkedHashMap<String,Object>> list=barcodeQueryDao.findNowProductOrder(productDevicesId);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                if(!CollectionUtils.isEmpty(productList)){
                    productList.forEach(vo -> {
                        if (item.get("productDevicesId")!=null&&item.get("productDevicesId").toString().equals(vo.getId())) {
                            item.put("productDevicesName",vo.getName());
                            item.put("productDevicesCode",vo.getCode());
                        }
                    });
                }
                //用户设置
                if(!CollectionUtils.isEmpty(userList)){
                    userList.forEach(vo -> {
                        if (item.get("createUser")!=null && item.get("createUser").equals(vo.getId())) {
                            item.put("createUserName",vo.getName());
                        }
                    });
                }
            });
        }
        return ResultUtils.success(list);
    }

    @Override
    public Result findProductStatusList(String productDevicesCode) {
        return ResultUtils.success(barcodeQueryDao.findProductStatusList(productDevicesCode));
    }

    @Override
    public Result findBadProductList(Integer planMoldingId) {
        return ResultUtils.success(barcodeQueryDao.findBadProductList(planMoldingId));
    }

    @Override
    public Result findFeedingDetailList(String productOrder) {
        return ResultUtils.success(barcodeQueryDao.findFeedingDetailList(productOrder));
    }

    @Override
    public Result findMoldingMonitorList(Integer planMoldingId,String productOrder,String status) {
        List statusList=null;
        if(!CheckUtils.isNull(status)){
            Arrays.asList(status.split(","));
        }
        return ResultUtils.success(barcodeQueryDao.findMoldingMonitorList(planMoldingId,productOrder, statusList));
    }

    @Override
    public Result findReworkInjectionList(String productOrder, String partsCode, String partsName, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ProduceReworkMonitor> pageInfo = new PageInfo<>(barcodeQueryDao.findReworkInjectionList(productOrder,partsCode,partsName));
        return ResultUtils.success(pageInfo);
    }

    @Override
    public Result findDevicesStateByCode(String devicesCode) {
        if(CheckUtils.isNull(devicesCode)){
            return ResultUtils.error("参数错误");
        }
        DeviceState device = deviceStateDao.findDevice(devicesCode);
        if(device == null){
            device = new DeviceState();
            device.setDeviceId(devicesCode);
            device.setTmOnlineState(0);
        }
        Integer isExist = 0;
        if(device.getTmOnlineState() == 0){
            if(redisService.hasKeyAndItem(FwConst.DEVICE_STATE_LOG_KEY,devicesCode)){
                isExist = Integer.parseInt(redisService.hget(FwConst.DEVICE_STATE_LOG_KEY, devicesCode).toString());
            }
        }else if(device.getTmOnlineState() == 1){
            if(redisService.hasKeyAndItem(FwConst.DEVICE_STATE_LOG_KEY,devicesCode)){
                redisService.hset(FwConst.DEVICE_STATE_LOG_KEY, devicesCode,"0");
            }
        }
        device.setIsExistLog(isExist);
        return ResultUtils.success(device);
    }
}
