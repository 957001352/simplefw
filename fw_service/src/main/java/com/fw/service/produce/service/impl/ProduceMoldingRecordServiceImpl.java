package com.fw.service.produce.service.impl;

import com.alibaba.fastjson.JSON;
import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.produce.CallOrCastMaterial;
import com.fw.entity.produce.ProduceMoldingRecord;
import com.fw.enums.ResultEnum;
import com.fw.service.RedisService;
import com.fw.service.produce.dao.ProduceMoldingRecordDao;
import com.fw.service.produce.service.ProduceMoldingRecordService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: dhlk_fw_plat
 * @description: 生产报工记录业务逻辑层
 * @author: wqiang
 * @create: 2020-12-14 11:53
 **/
@Service
public class ProduceMoldingRecordServiceImpl implements ProduceMoldingRecordService {

    @Autowired
    private ProduceMoldingRecordDao produceMoldingRecordDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private RedisService redisService;


    @Override
    public Result save(ProduceMoldingRecord produceMoldingRecord) {
        if (produceMoldingRecord == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        produceMoldingRecord.setCreateUser(authUserUtil.userId());
        return produceMoldingRecordDao.insert(produceMoldingRecord) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String ofNo, String userName, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);

        List<ProduceMoldingRecord> list = produceMoldingRecordDao.findList(ofNo);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());

        if (!CollectionUtils.isEmpty(list)) {
            //获取用户名称
            if (!CollectionUtils.isEmpty(userList)) {
                for (ProduceMoldingRecord produceMoldingRecord : list) {
                    for (User user : userList) {
                        if (produceMoldingRecord.getCreateUser() == user.getId()) {
                            produceMoldingRecord.setCreateUserName(user.getName());
                        }
                    }
                }
            }
            //获取设备编码
            if (!CollectionUtils.isEmpty(productDevices)) {
                for (ProduceMoldingRecord produceMoldingRecord : list) {
                    for (DevicesItemVo vo : productDevices) {
                        if (produceMoldingRecord.getProductDevicesId() == Integer.parseInt(vo.getId())) {
                            produceMoldingRecord.setDeviceCode(vo.getName());
                        }
                    }
                }
            }

        }
        if (!CheckUtils.isNull(userName)) {
            List<ProduceMoldingRecord> resultList = list.stream().filter(e -> e.getCreateUserName().equals(userName)).collect(Collectors.toList());
            PageInfo<ProduceMoldingRecord> lp = new PageInfo<>(resultList);
            return ResultUtils.success(lp);
        }

        PageInfo<ProduceMoldingRecord> listPage = new PageInfo<>(list);
        return ResultUtils.success(listPage);
    }


    /**
     * 叫料或者投料
     * @param productDeviceCode 设备编码
     * @param callType      叫料类型
     * @param CastType      投料种类
     * @return
     */
    @Override
    public Result callMaterialOrCastMaterial(String productDeviceCode, Integer callType, String CastType) {

        boolean flag = false;
        if(CheckUtils.isNull(productDeviceCode) || CheckUtils.isNull(callType)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        CallOrCastMaterial callOrCastMaterial = new CallOrCastMaterial();
        if(callType == 1){ //叫料
            callOrCastMaterial.setCallTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            callOrCastMaterial.setCallType(1);
            callOrCastMaterial.setProductDeviceCode(productDeviceCode);
            flag = redisService.set("FW_PDA_"+productDeviceCode+"_call",JSON.toJSONString(callOrCastMaterial),600);
        }
        if(callType == 2){//投料
            callOrCastMaterial.setCallTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            callOrCastMaterial.setCallType(2);
            callOrCastMaterial.setCastType(CastType);
            callOrCastMaterial.setProductDeviceCode(productDeviceCode);
            flag = redisService.set("FW_PDA_"+productDeviceCode+"_cast", JSON.toJSONString(callOrCastMaterial),600);
        }
        return flag ? ResultUtils.success() : ResultUtils.failure();
    }


    /**
     * 获取叫料投料集合
     * @return
     */
    @Override
    public Result findCallAndCastList() {
        List<String> values = redisService.getValues("FW_PDA_*");
        return ResultUtils.success(values);
    }
}
