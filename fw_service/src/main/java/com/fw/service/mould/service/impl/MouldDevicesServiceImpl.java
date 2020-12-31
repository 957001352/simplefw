package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.jbpm.JbpmExecution;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldUseRecord;
import com.fw.exceptions.MyException;
import com.fw.service.jbpm.dao.JbpmExecutionDao;
import com.fw.service.mould.dao.MouldDevicesDao;
import com.fw.service.mould.dao.MouldUseRecordDao;
import com.fw.service.mould.service.MouldDevicesService;
import com.fw.service.mould.service.MouldKeepTaskService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.systemconst.Const;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MouldDevicesServiceImpl implements MouldDevicesService {
    @Autowired
    private MouldDevicesDao mouldDevicesDao;
    @Autowired
    private MouldUseRecordDao mouldUseRecordDao;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private JbpmExecutionDao jbpmExecutionDao;

    @Autowired
    private MouldKeepTaskService mouldKeepTaskService;

    @Value("${attachment.path}")
    private String rootPath;
    @Override
    public Result save(MouldDevices mouldDevices) {
        if(mouldDevicesDao.mouldCodeIsRepeat(mouldDevices) > 0){
            return ResultUtils.error("存在相同编号模具");
        }
        int re = -1;
        if(CheckUtils.isNull(mouldDevices.getId())){
            re = mouldDevicesDao.insert(mouldDevices);
        }else{
            re =mouldDevicesDao.update(mouldDevices);
        }
        return re >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(String code, String name, Integer status, Integer turnStatus, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MouldDevices> mouldDevices = mouldDevicesDao.findList(code, name, turnStatus, status,rootPath, Const.JBPM_FORM_CODE_MOULD_TURN);
        for (MouldDevices mouldDevice:mouldDevices) {
            //获取模具的开合模次数 初始开合模次数+使用的开合模次数
            mouldDevice.setProcess(mouldDevice.getInitCount());
        }
        PageInfo<MouldDevices> mouldDevicesPageInfo = new PageInfo<>(mouldDevices);
        return ResultUtils.success(mouldDevicesPageInfo);
    }

    @Override
    public Result delete(String ids) {
        if(CheckUtils.isNull(ids)) return ResultUtils.error("参数错误");
        Integer conut = mouldDevicesDao.delete(ids.split(","));
        return conut > 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findUnBoundMould() {
        return ResultUtils.success(mouldDevicesDao.findMouldNoBound());
    }

    @Override
    @Transactional
    public Result saveUseRecord(MouldUseRecord mouldUseRecord) {
        int re = -1;
        if(CheckUtils.isNull(mouldUseRecord.getId())){
            re = mouldUseRecordDao.insert(mouldUseRecord);
        }else{
            if(mouldUseRecord.getStatus()==1){
                mouldUseRecord.setCreateUser(headerUtil.loginUser().getId());
            }
            re =mouldUseRecordDao.update(mouldUseRecord);
            if(re > 0){
                MouldUseRecord mouldUse = mouldUseRecordDao.findOne(mouldUseRecord.getId());
                if(mouldUse.getOpreate() == 1){
                    Integer con = mouldKeepTaskService.issuedTask(4, mouldUse.getMouldDevicesId(), mouldUse.getInjectionMoldingId());
                    if(con < 1){
                        throw new MyException("500","产后保养任务创建失败");
                    }
                }
            }
        }
        return re > 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findListUseRecord(String mouldCode,Integer status,Integer pageNum,Integer pageSize) {
        if(CheckUtils.isNull(status)) ResultUtils.error("参数错误");
        PageHelper.startPage(pageNum,pageSize);
        List<MouldUseRecord> mouldUseRecords = mouldUseRecordDao.findList(mouldCode,status);
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        for (DevicesItemVo devicesItemVo:productDevices) {
            for (MouldUseRecord mouldUseRecord:mouldUseRecords) {
                if(mouldUseRecord.getProductDevicesId() != null && devicesItemVo.getId().equals(mouldUseRecord.getProductDevicesId().toString())){
                    mouldUseRecord.setDevicesCode(devicesItemVo.getName());
                }
            }
        }
        PageInfo<MouldUseRecord> pageInfo = new PageInfo<>(mouldUseRecords);
        return ResultUtils.success(pageInfo);
    }
}
