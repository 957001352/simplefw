package com.fw.web.jbpm.service;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmExecution;
import com.fw.web.jbpm.service.fbk.JbpmExecutionServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-22
 */
@FeignClient(value = "fw-service/jbpmExecution", fallback = JbpmExecutionServiceFbk.class)
public interface JbpmExecutionService {


    /**
     * 任务发起
     * @param jbpmExecution
     * @return
     */
    @PostMapping(value = "/startExecution")
    Result startExecution(@RequestBody JbpmExecution jbpmExecution);

    /**
     * 任务办理
     * @param jbpmExecution
     * @return
     */
    @PostMapping(value = "/doTask")
    Result doTask(@RequestBody JbpmExecution jbpmExecution);



    /**
     * 待办任务列表查询
     * @param jbpmNo
     * @param createUser
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/findWaitTaskList")
    Result findWaitTaskList(@RequestParam(value = "jbpmNo", required = false) String jbpmNo,
                                   @RequestParam(value = "createUser", required = false) String createUser,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 已办任务列表查询
     * @param jbpmNo
     * @param createUser
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/findHistTaskList")
    Result findHistTaskList(@RequestParam(value = "jbpmNo", required = false) String jbpmNo,
                                   @RequestParam(value = "createUser", required = false) String createUser,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /*
     * 查询审核记录列表
     * @param executionId
     * @return
     */
    @GetMapping("/findHistTaskDeatilList")
    Result findHistTaskDeatilList(@RequestParam(value = "executionId", required = false) Integer executionId);
}
