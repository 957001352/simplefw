package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldScrap;
import com.fw.exceptions.MyException;
import com.fw.service.mould.dao.MouldScrapDao;
import com.fw.service.mould.service.MouldScrapService;
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
public class MouldScrapServiceImpl implements MouldScrapService {
    @Autowired
    private MouldScrapDao mouldScrapDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private JbpmUtil jbpmUtil;
    @Override
    public Result save(MouldScrap mouldScrap) {
        int re = -1;
        if(CheckUtils.isNull(mouldScrap.getId())){
            User user = headerUtil.loginUser();
            mouldScrap.setCreatUser(user.getId());
            re = mouldScrapDao.insert(mouldScrap);
            if(re > 0){
                re = jbpmUtil.startExecution(null, mouldScrap.getId().toString(), user.getId(), Const.JBPM_FORM_CODE_MOULD_SCRAP);
                if(re <= 0){
                    throw new MyException("1000","审核提交失败");
                }
            }
        }else{
            re = mouldScrapDao.update(mouldScrap);
        }
        return re > 0? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList() {
        return ResultUtils.success(mouldScrapDao.findList());
    }
}
