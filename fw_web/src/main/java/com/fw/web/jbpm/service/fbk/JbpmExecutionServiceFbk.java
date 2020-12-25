package com.fw.web.jbpm.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmExecution;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.jbpm.service.JbpmExecutionService;
import org.springframework.stereotype.Service;

/**
 * 审核流程管理
 * @author lpsong
 * @since 2020-10-21
 */
@Service
public class JbpmExecutionServiceFbk implements JbpmExecutionService {

    @Override
    public Result startExecution(JbpmExecution jbpmExecution) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result doTask(JbpmExecution jbpmExecution) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findWaitTaskList(String jbpmNo, String createUser, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findHistTaskList(String jbpmNo, String createUser, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findHistTaskDeatilList(Integer executionId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
