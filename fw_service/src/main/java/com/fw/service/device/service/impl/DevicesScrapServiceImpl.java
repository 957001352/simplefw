package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesExtension;
import com.fw.entity.device.DevicesScrap;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.jbpm.JbpmExecution;
import com.fw.entity.jbpm.JbpmHistTask;
import com.fw.exceptions.MyException;
import com.fw.service.basic.dao.AttachmentDao;
import com.fw.service.device.dao.DevicesExtensionDao;
import com.fw.service.device.dao.DevicesScrapDao;
import com.fw.service.device.service.DevicesScrapService;
import com.fw.service.jbpm.dao.JbpmExecutionDao;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.systemconst.Const;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DevicesScrapServiceImpl implements DevicesScrapService {
    @Resource
    private DevicesScrapDao devicesScrapDao;
    @Resource
    private E2CServicesUtil e2CServicesUtil;
    @Resource
    private JbpmUtil jbpmUtil;
    @Resource
    private HeaderUtil headerUtil;
    @Resource
    private AttachmentDao attachmentDao;
    @Resource
    private DevicesExtensionDao devicesExtensionDao;
    @Value("${attachment.path}")
    private String attachmentPath;
    @Override
    @Transactional
    public Result save(DevicesScrap devicesScrap) {
        Integer userId = headerUtil.loginUser().getId();
        devicesScrap.setCreatUser(userId);
        Integer insert = devicesScrapDao.insert(devicesScrap);
        if(insert > 0){
            insert = jbpmUtil.startExecution(null, devicesScrap.getId().toString(), userId, Const.JBPM_FORM_CODE_DEVICE_SCRAP);
            if(insert <= 0){
                throw new MyException("1000","审核提交失败");
            }
        }
        return insert > 0? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(String devicesCode, String classifyId,Integer devicesScrapId,Integer pageNum,Integer pageSize) {
        Map<String,String> params = new HashMap<>();
        params.put("name",devicesCode);
        params.put("classifyId",classifyId);
        //从e2c中获取设备信息
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken(),params);

        String ids = productDevices.stream().map(devicesItemVo -> String.valueOf(devicesItemVo.getId())).collect(Collectors.joining(","));

        PageHelper.startPage(pageNum,pageSize);
        List<DevicesScrap> devicesScraps = devicesScrapDao.findList(Const.JBPM_FORM_CODE_DEVICE_SCRAP,devicesScrapId,ids);

        //从e2c中获取用户信息
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        for (DevicesScrap devicesScrap:devicesScraps) {
            //插入申请用户信息
            for (User user:userList) {
                if(user.getId().equals(devicesScrap.getCreatUser())){
                    devicesScrap.setApplyUser(user);
                }
            }
            //插入设备信息
            for (DevicesItemVo devicesItemVo:productDevices) {
                //将报废关联设备信息插入
                if(devicesItemVo.getId().equals(devicesScrap.getProductDevicesId())){
                    devicesScrap.setDevicesItemVo(devicesItemVo);
                }
            }
            String dataId = devicesScrap.getDataId();
            if(!CheckUtils.isNull(dataId)){
                devicesScrap.setBaseFiles(attachmentDao.findByArrayDataId(attachmentPath,dataId.split(",")));
            }
        }
        PageInfo<DevicesScrap> pageInfo = new PageInfo<>(devicesScraps);
        return ResultUtils.success(pageInfo);
    }

    @Override
    public Result findById(Integer devicesScrapId) {
        if(CheckUtils.isNull(devicesScrapId)){
            ResultUtils.error("参数错误");
        }
        return ResultUtils.success(devicesScrapDao.findById(devicesScrapId));
    }

    @Override
    public Result findByDeviceExtensionId(Integer deviceExtensionId) {
        if(CheckUtils.isNull(deviceExtensionId)){
            return ResultUtils.error("参数错误");
        }
        List<DevicesScrap> devicesScraps = devicesScrapDao.findByDeviceExtensionId(deviceExtensionId, Const.JBPM_FORM_CODE_DEVICE_SCRAP);
        for (DevicesScrap devicesScrap:devicesScraps) {
            String dataId = devicesScrap.getDataId();
            if(!CheckUtils.isNull(dataId)){
                devicesScrap.setBaseFiles(attachmentDao.findByArrayDataId(attachmentPath,dataId.split(",")));
            }
        }
        return ResultUtils.success(devicesScraps);
    }

    @Override
    public Result devicesScrapPass(Integer id) {
        DevicesScrap devicesScrap = devicesScrapDao.findById(id);
        if(devicesScrap == null){
            return ResultUtils.failure();
        }
        Integer executionId = devicesScrap.getDevicesExtensionId();
        DevicesExtension devicesExtension = new DevicesExtension();
        devicesExtension.setId(executionId);
        devicesExtension.setStatus(2);
        Integer update = devicesExtensionDao.update(devicesExtension);
        return update > 0?ResultUtils.success():ResultUtils.failure();
    }
}
