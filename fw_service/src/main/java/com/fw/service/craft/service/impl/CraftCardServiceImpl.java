package com.fw.service.craft.service.impl;

import com.alibaba.fastjson.JSON;
import com.fw.domain.Result;
import com.fw.entity.craft.*;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.jbpm.JbpmHistTask;
import com.fw.entity.plan.InjectionMolding;
import com.fw.exceptions.MyException;
import com.fw.service.craft.dao.*;
import com.fw.service.craft.service.CraftCardService;
import com.fw.service.jbpm.service.JbpmExecutionService;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.util.*;
import com.fw.systemconst.Const;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CraftCardServiceImpl implements CraftCardService {
    @Autowired
    private CardParamsDao cardParamsDao;
    @Autowired
    private CraftCardDao craftCardDao;
    @Autowired
    private CardLogDao cardLogDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private JbpmUtil jbpmUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private JbpmExecutionService jbpmExecutionService;
    @Autowired
    private InjectionMoldingDao injectionMoldingDao;
    @Autowired
    private CollectDeviceDao collectDeviceDao;
    @Autowired
    private CardUtil cardUtil;
    @Autowired
    private CardUpdateParamsDao cardUpdateParamsDao;
    @Override
    public Result save(CardParams cardParams) {
        int re = -1;
        // 判断该零件和设备的工艺卡是否已存在
        Integer exist = cardParamsDao.isExist(cardParams);
        if(exist > 0){
            return ResultUtils.error("该零件和设备的工艺卡是否已存在");
        }
        if(CheckUtils.isNull(cardParams.getId())){
            User user = headerUtil.loginUser();
            cardParams.setCreateUser(user.getId());
            //生成工艺卡编号
            String code = FwConst.GYK+DateUtils.getToday("yyyyMMddHHmmssSSS");
            cardParams.setCode(code);

            re = cardParamsDao.insert(cardParams);
        }else {
            re = cardParamsDao.update(cardParams);
        }
        return re > 0? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        if(CheckUtils.isNull(ids)) return ResultUtils.error("参数错误");
        Integer con = cardParamsDao.delete(ids.split(","));
        return con > 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(String partCode,String partName,Integer productId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CardParams> list = cardParamsDao.findList(partCode, partName, productId,Const.JBPM_FORM_CODE_CARD_CHANGE);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        for(CardParams cardParams:list) {
            if(cardParams.getProductId() != null){
                for (DevicesItemVo devicesItemVo:productDevices) {
                    if(devicesItemVo.getId().equals(cardParams.getProductId().toString())){
                        cardParams.setDevicesItemVo(devicesItemVo);
                    }
                }
            }
        }
        PageInfo<CardParams> paramsPageInfo = new PageInfo<>(list);
        return ResultUtils.success(paramsPageInfo);
    }

    @Override
    public Result findListCard() {
        return ResultUtils.success(craftCardDao.findList());
    }

    @Override
    @Transactional
    public Result saveCardLog(CardLog cardLog) {
        User user = headerUtil.loginUser();
        cardLog.setCreateUser(user.getId());
        Integer insert = cardLogDao.insert(cardLog);
        if(insert > 0){
            insert = jbpmUtil.startExecution(null, cardLog.getId().toString(), user.getId(), Const.JBPM_FORM_CODE_CARD_CHANGE);
            if(insert <= 0){
                throw new MyException("1000","审核提交失败");
            }
        }
        return insert > 0? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findCardLog(Integer cardLogId,Integer paramsId) {
        List<CardLog> cardLogs = cardLogDao.findList(cardLogId,paramsId,Const.JBPM_FORM_CODE_CARD_CHANGE);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        for (CardLog cardLog:cardLogs) {
            if(cardLog.getCreateUser() != null){
                for (User user:userList) {
                    if(cardLog.getCreateUser().equals(user.getId())){
                        cardLog.setUser(user);
                    }
                }
            }
            if(!CheckUtils.isNull(cardLog.getExecutionId())){
                Result result = jbpmExecutionService.findHistTaskDeatilList(cardLog.getExecutionId());
                cardLog.setExecution((List<JbpmHistTask>) result.getData());
            }
        }

        return ResultUtils.success(cardLogs);
    }

    @Override
    @Transactional
    public Result cardChangePass(Integer id) {
        CardLog cardLog = cardLogDao.findById(id);
        CardParams cardParams = cardParamsDao.findById(cardLog.getParamsId());
        cardParams.setUpdateUser(cardLog.getCreateUser());
        cardParams.setUpdateTime(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
        cardParams.setUpdateType(cardLog.getUpdateType());

        cardLog.setCraftPreData(cardParams.getCraftData());//将原工艺参数保存到记录中
        cardParams.setCraftData(cardLog.getCraftAfterData());//设置新的工艺参数
        cardLog.setCraftAfterData(null);//将记录中的变更参数置为空

        cardLogDao.update(cardLog);
        Integer count = cardParamsDao.update(cardParams);
        return count > 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findDeviceByPartCode(String partCode) {
        List<CardParams> cardParamsList =  cardParamsDao.findDeviceByPartCode(partCode);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        List<DevicesItemVo> devices = new ArrayList<>();
        for (DevicesItemVo devicesItemVo:productDevices) {
            for (CardParams cardParams:cardParamsList) {
                if(cardParams.getProductId() != null && devicesItemVo.getId().equals(cardParams.getProductId().toString())){
                    devices.add(devicesItemVo);
                }
            }
        }
        return ResultUtils.success(devices);
    }

    @Override
    public Result findIngCardByDeviceId(DevicesItemVo devicesItemVo) {
        InjectionMolding ingByDeviceId = injectionMoldingDao.findIngByDeviceId(Integer.parseInt(devicesItemVo.getId()));
        if(ingByDeviceId == null){
            return ResultUtils.success();
        }
        //获取设备和零件绑定的工艺卡
        CardParams cardParams = cardParamsDao.findByDeviceProduct(ingByDeviceId.getProductDevicesId(),ingByDeviceId.getPartsCode());
        //获取设备和零件绑定的工艺卡参数
        String craftData = cardParams.getCraftData();
        //获取设备采集的数据
        List<CollectDevice> collectDevices = collectDeviceDao.findByProductCode(devicesItemVo.getCode());
        if(collectDevices.size() == 0){ //没有采集到数据直接返回工艺卡参数
            return ResultUtils.success(cardParams);
        }
        //将设备采集的数据转化为属性为key,值为value的map
        Map<String,String> map = collectDevices.stream().collect(Collectors.toMap(CollectDevice::getParamKey, CollectDevice::getParamValue));
        //获取设备和零件绑定的工艺卡模板id
        Integer cardId = cardParams.getCardId();
        ChinaCardTwo chinaCardTwo = null;
        switch (cardId){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5://海天
                chinaCardTwo = JSON.parseObject(craftData, ChinaCardTwo.class);
                String[] fieldName = cardUtil.getFieldName(chinaCardTwo);
                for (String field:fieldName) {
                    Object fieldValueByName = cardUtil.getFieldValueByName(field, chinaCardTwo);
                    if(fieldValueByName != null){
                        String value = cardUtil.dataIsQualified(fieldValueByName.toString(), map.get(cardUtil.fields.get(field)));
                        cardUtil.setFieldValueByName(chinaCardTwo,field,value);
                    }
                }
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
        }
        cardParams.setCraftData(JSON.toJSONString(chinaCardTwo));
        return ResultUtils.success(cardParams);
    }

    @Override
    public Result findUpdateParamLog(String deviceCode, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return ResultUtils.success(new PageInfo<>(cardUpdateParamsDao.findList(deviceCode)));
    }

    @Override
    public Result findById(Integer id) {
        return ResultUtils.success(cardParamsDao.findById(id));
    }

    @Override
    public Result findCardLogById(Integer id) {
        return ResultUtils.success(cardLogDao.findById(id));
    }
}
