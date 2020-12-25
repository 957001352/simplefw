package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldStop;
import com.fw.exceptions.MyException;
import com.fw.service.mould.dao.MouldStopDao;
import com.fw.service.mould.service.MouldStopService;
import com.fw.service.util.HeaderUtil;
import com.fw.service.util.JbpmUtil;
import com.fw.systemconst.Const;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MouldStopServiceImpl implements MouldStopService {
    @Autowired
    private MouldStopDao mouldStopDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private JbpmUtil jbpmUtil;
    @Override
    public Result save(MouldStop mouldStop) {
        int re = -1;
        if(CheckUtils.isNull(mouldStop.getId())){
            User user = headerUtil.loginUser();
            mouldStop.setCreatUser(user.getId());
            re = mouldStopDao.insert(mouldStop);
            if(re > 0){
                re = jbpmUtil.startExecution(null, mouldStop.getId().toString(), user.getId(), Const.JBPM_FORM_CODE_MOULD_STOP);
                if(re <= 0){
                    throw new MyException("1000","审核提交失败");
                }
            }
        }else{
            re = mouldStopDao.update(mouldStop);
        }
        return re >= 0? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList() {
        return ResultUtils.success(mouldStopDao.findList());
    }
}
