package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditQuestion;
import com.fw.entity.device.DevicesCheck;
import com.fw.entity.device.DevicesCheckDetail;
import com.fw.entity.device.DevicesCheckTeam;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.entity.plan.InjectionMolding;
import com.fw.enums.ResultEnum;
import com.fw.service.audit.dao.AuditQuestionDao;
import com.fw.service.device.dao.DevicesCheckDao;
import com.fw.service.device.dao.DevicesCheckTeamDao;
import com.fw.service.device.service.DevicesCheckService;
import com.fw.service.enums.CodeEnum;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 设备点检
 * @author lpsong
 * @since 2020-10-21
 */
@Service
public class DevicesCheckServiceImpl implements DevicesCheckService {

    @Autowired
    private DevicesCheckDao devicesCheckDao;

    @Autowired
    private AuditQuestionDao auditQuestionDao;
    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private AuthUserUtil authUserUtil;

    @Value("${E2C.webUrl}")
    private String webUrl;
    @Autowired
    private DevicesCheckTeamDao devicesCheckTeamDao;
    @Autowired
    private  InjectionMoldingDao injectionMoldingDao;

    @Override
    @Transactional
    public Result insert(Integer executeUser, Integer productDevicesId) {
        Integer flag = 0;
        //获取当前小时
        int hour=  Integer.parseInt(DateUtils.getCurrentTime("HH"));
        Integer shirt=-1;//班次，默认夜班
        if(hour>8&&hour<20){
            shirt=0;//白班
        }else if(hour>=20&&hour<=23){
            shirt=1;//夜班，前一天
        }else if(hour>=0&&hour<8){
            shirt=2;//夜班，当天
        }
        //查询当前用户登录设备下是否有点检任务，如果没有则新增点检任务
        if(devicesCheckDao.findIsExistCheckTask(executeUser,productDevicesId,shirt)==0) {
            DevicesCheck devicesCheck=new DevicesCheck();
            //设置点击任务单号
            devicesCheck.setCode(devicesCheckDao.findCode(CodeEnum.DEVICES_05.getCode()));
            //点击设备
            devicesCheck.setProductDevicesId(productDevicesId);
            //查询排查计划
            InjectionMolding molding=injectionMoldingDao.findLatestByDeviceId(productDevicesId);
            if(molding!=null){
                //设置生产指令
                devicesCheck.setProductCode(molding.getProductCode());
            }
            //根据设备查询保养项目
           List<DevicesCheckDetail> checkItemList= devicesCheckTeamDao.findCheckItemListByDevicve(productDevicesId);
           if (!CollectionUtils.isEmpty(checkItemList)) {
               //设置点击表单
               devicesCheck.setCheckTeamIds(checkItemList.stream().map(DevicesCheckDetail::getCheckTeamId).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
               //devicesCheck.setCheckTeamIds(checkItemList.stream().map(DevicesCheckDetail::getCheckTeamId).collect(Collectors.joining(",")));
               //插入点击任务
               devicesCheck.setExecuteUser(executeUser);
               flag = devicesCheckDao.insert(devicesCheck);
               if(flag>0){
                   checkItemList.forEach(item -> {
                       DevicesCheckDetail checkDetail=new DevicesCheckDetail();
                       checkDetail.setCheckRecordId(devicesCheck.getId());
                       checkDetail.setCheckItemId(item.getCheckItemId());
                       devicesCheckDao.insertCheckDetail(checkDetail);
                   });
               }
           }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result save(DevicesCheck devicesCheck) {
        Integer flag = 0;
       if (!CheckUtils.isNull(devicesCheck.getId())) {
            //修改时先查询数据是否存在,多个窗口操作问题
            DevicesCheck check = devicesCheckDao.selectById(devicesCheck.getId());
            if (CheckUtils.isNull(check)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            devicesCheck.setExecuteUser(authUserUtil.userId());
            devicesCheck.setExecuteTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            flag = devicesCheckDao.update(devicesCheck);
            if(flag>0){
                //更新点检项目
                List<DevicesCheckDetail> checkDetailList=devicesCheck.getCheckDetailList();
                if(checkDetailList!=null&&checkDetailList.size()>0){
                    for(DevicesCheckDetail checkDetail:checkDetailList){
                       devicesCheckDao.updateCheckDetail(checkDetail);
                    }
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        flag = devicesCheckDao.delete(list);
        //删除点检项目
        if(flag>0){
            devicesCheckDao.deleteCheckDetail(list);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String code, String devicesClassify, String devicesCode,Integer status,Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询E2C生产设备
        Map<String, String> params=new HashMap<String, String>();
        params.put("name",devicesCode);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(),params);
        List<String> productDevicesIds=null;
        if(!CheckUtils.isNull(devicesCode)){
            //如果设备编号查询条件不为空，根据设备编号查询结果为空，则直接返回空，如果不为空，则根据设备id进行数据查询
            if(productDevices==null||productDevices.size()==0){
                return ResultUtils.success(new PageInfo<>());
            }else{
                productDevicesIds=  productDevices.stream().map(DevicesItemVo::getId).collect(Collectors.toList());
            }
        }
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(),params);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesCheck> lists = devicesCheckDao.findList(code,devicesClassify, productDevicesIds,status);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                //生产设备
                if(!CheckUtils.isNull(item.getProductDevicesId())){
                    if(productList!=null&&productList.size()>0){
                        productList.forEach(vo -> {
                            if (item.getProductDevicesId().toString().equals(vo.getId())) {
                                item.setProductDevices(vo.getName());
                                item.setClassifyName(vo.getClassifyName());
                                if(vo.getClassifySet()!=null&&!CheckUtils.isNull(vo.getClassifySet().getImagePath())){
                                    item.setImagePath(webUrl+vo.getClassifySet().getImagePath());
                                }
                            }
                        });
                    }
                }
                //用户设置
                if(userList!=null&&userList.size()>0){
                    userList.forEach(vo -> {
                        if (!CheckUtils.isNull(item.getExecuteUser()) && item.getExecuteUser().equals(vo.getId())) {
                            item.setExecuteUserName(vo.getName());
                        }
                    });
                }
            });
        }
        PageInfo<DevicesCheck> CheckItemPage = new PageInfo<>(lists);
        return ResultUtils.success(CheckItemPage);
    }

    @Override
    public Result findCheckTask(Integer executeUser, Integer productDevicesId) {
        //获取当前小时
        int hour=  Integer.parseInt(DateUtils.getCurrentTime("HH"));
        Integer shirt=-1;//班次，默认夜班
        if(hour>8&&hour<20){
            shirt=0;//白班
        }else if(hour>=20&&hour<=23){
            shirt=1;//夜班，前一天
        }else if(hour>=0&&hour<8){
            shirt=2;//夜班，当天
        }
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        //查询当前用户登录设备下是否有点检任务
        List<DevicesCheck> list=devicesCheckDao.findCheckTask(executeUser,productDevicesId,shirt);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(productList)) {
            list.forEach(item -> {
                //生产设备
                productList.forEach(vo -> {
                    if (item.getProductDevicesId().toString().equals(vo.getId())) {
                        item.setProductDevices(vo.getName());
                    }
                });
            });
        }
        return ResultUtils.success(list);
    }
}
