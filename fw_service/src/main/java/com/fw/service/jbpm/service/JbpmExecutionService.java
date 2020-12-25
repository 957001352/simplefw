package com.fw.service.jbpm.service;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmExecution;
import org.apache.ibatis.annotations.Param;

/**
 * 审核任务办理
 * @author lpsong
 * @since 2020-10-22
 */
public interface JbpmExecutionService {

    /**
     *任务发起
     * @param jbpmExecution
     * @return
     */
    Result startExecution(JbpmExecution jbpmExecution);





    /**
     *任务办理
     * @param jbpmExecution
     * @return
     */
    Result doTask(JbpmExecution jbpmExecution);




    /**
     * 待办任务列表查询
     * @param jbpmNo
     * @param createUser
     * @param startTime
     * @param endTime
     * @return
     */
    Result findWaitTaskList(String jbpmNo,
                            String createUser,
                            String startTime,
                            String endTime,
                            Integer pageNum,
                            Integer pageSize);

    /**
     * 已办任务列表查询
     * @param jbpmNo
     * @param createUser
     * @param startTime
     * @param endTime
     * @return
     */
    Result findHistTaskList(String jbpmNo,
                            String createUser,
                            String startTime,
                            String endTime,
                            Integer pageNum,
                            Integer pageSize);

    Result findHistTaskDeatilList(Integer executionId);
}
