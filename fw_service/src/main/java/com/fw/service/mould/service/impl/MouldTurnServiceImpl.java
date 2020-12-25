package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldTurn;
import com.fw.exceptions.MyException;
import com.fw.service.basic.dao.AttachmentDao;
import com.fw.service.mould.dao.MouldDevicesDao;
import com.fw.service.mould.dao.MouldTurnDao;
import com.fw.service.mould.service.MouldTurnService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.systemconst.Const;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MouldTurnServiceImpl implements MouldTurnService {
    @Autowired
    private MouldTurnDao mouldTurnDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private JbpmUtil jbpmUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private MouldDevicesDao mouldDevicesDao;
    @Autowired
    private AttachmentDao attachmentDao;
    @Value("${attachment.path}")
    private String attachmentPath;
    @Override
    public Result save(MouldTurn mouldTurn) {
        int re = -1;
        if(CheckUtils.isNull(mouldTurn.getId())){
            User user = headerUtil.loginUser();
            mouldTurn.setCreatUser(user.getId());
            re = mouldTurnDao.insert(mouldTurn);
            if(re > 0){
                re = jbpmUtil.startExecution(null, mouldTurn.getId().toString(), user.getId(), Const.JBPM_FORM_CODE_MOULD_TURN);
                if(re <= 0){
                    throw new MyException("1000","审核提交失败");
                }
            }
        }else{
            re = mouldTurnDao.update(mouldTurn);
        }
        return re > 0? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList() {
        return ResultUtils.success(mouldTurnDao.findList());
    }

    @Override
    public Result findTurn(Integer mouldDeviceId) {
        if(CheckUtils.isNull(mouldDeviceId)) return ResultUtils.error("参数错误");
        List<MouldTurn> mouldTurns = mouldTurnDao.findByMouldDeviceId(Const.JBPM_FORM_CODE_MOULD_TURN, mouldDeviceId);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());

        for (MouldTurn mouldTurn:mouldTurns) {
            if(!CheckUtils.isNull(mouldTurn.getCreatUser())){
                for (User user:userList) {
                    if(mouldTurn.getCreatUser().equals(user.getId())){
                        mouldTurn.setUser(user);
                    }
                }
            }
            String dataId = mouldTurn.getDataId();
            if(!CheckUtils.isNull(dataId)){
                mouldTurn.setBaseFiles(attachmentDao.findByArrayDataId(attachmentPath,dataId.split(",")));
            }
        }
        return ResultUtils.success(mouldTurns);
    }

    @Override
    public Result findOneById(Integer id) {
        MouldTurn mouldTurn = mouldTurnDao.findOneById(id);
        String dataId = mouldTurn.getDataId();
        if(!CheckUtils.isNull(dataId)){
            mouldTurn.setBaseFiles(attachmentDao.findByArrayDataId(attachmentPath,dataId.split(",")));
        }
        return ResultUtils.success(mouldTurn);
    }

    @Override
    public Result mouldTurnPass(Integer id) {
        Integer update = -1;
        MouldTurn mouldTurn = mouldTurnDao.findOneById(id);
        //判断转段成哪个阶段
        Integer stage = mouldTurn.getStage();
        if(stage != null){
            MouldDevices mouldDevices = new MouldDevices();
            mouldDevices.setId(mouldTurn.getMouldDevicesId());
            mouldDevices.setTurnStatus(stage);
            switch (stage){
                case 0: //试制
                case 1: //量产
                    break;
                case 2: //EOP
                    mouldDevices.setStatus(2);
                    break;
                case 3: //报废
                    mouldDevices.setStatus(8);
                    break;
            }
            update = mouldDevicesDao.update(mouldDevices);
        }
        return update > 0?ResultUtils.success():ResultUtils.failure();
    }
}
