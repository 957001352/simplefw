package com.fw.web.audit.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditQuestion;
import com.fw.entity.plan.Customer;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.audit.service.AuditQuestionService;
import com.fw.web.plan.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AuditQuestionServiceFbk implements AuditQuestionService {

    @Override
    public Result save(AuditQuestion auditQuestion) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String dutyUser, Integer status, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
