package com.fw.web.jbpm.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmDeployment;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.jbpm.service.JbpmDeploymentService;
import org.springframework.stereotype.Service;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-21
 */
@Service
public class JbpmDeploymentServiceFbk implements JbpmDeploymentService {

    @Override
    public Result save(JbpmDeployment jbpmDeployment) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String code, String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findJbpmFormList() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }


    @Override
    public Result findUserList() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
