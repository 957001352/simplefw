package com.fw.web.jbpm.controller;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmExecution;
import com.fw.web.jbpm.service.JbpmExecutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 流程审核
 * @author lpsong
 * @since 2020-10-22
 */
@RestController
@RequestMapping("/jbpmExecution")
@Api(description = "流程审核", value = "JbpmExecutionController")
public class JbpmExecutionController {

    @Autowired
    private JbpmExecutionService jbpmExecutionService;

    /**
     * 任务发起
     * @param jbpmExecution
     * @return
     */
    @PostMapping(value = "/startExecution")
    public Result startExecution(@RequestBody JbpmExecution jbpmExecution) {
        return jbpmExecutionService.startExecution(jbpmExecution);
    }

    /**
     * 任务办理
     * @param jbpmExecution
     * @return
     */
    @ApiOperation("任务办理")
    @PostMapping(value = "/doTask")
    public Result doTask(@RequestBody JbpmExecution jbpmExecution) {
        return jbpmExecutionService.doTask(jbpmExecution);
    }



    /**
     * 待办任务列表查询
     * @param jbpmNo
     * @param createUser
     * @param startTime
     * @param endTime
     * @return
     */
    @ApiOperation("待办任务列表查询")
    @GetMapping("/findWaitTaskList")
    public Result findWaitTaskList(@RequestParam(value = "jbpmNo", required = false) String jbpmNo,
                                   @RequestParam(value = "createUser", required = false) String createUser,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return jbpmExecutionService.findWaitTaskList(jbpmNo, createUser,startTime, endTime,pageNum, pageSize);
    }


    /**
     * 已办任务列表查询
     * @param jbpmNo
     * @param createUser
     * @param startTime
     * @param endTime
     * @return
     */
    @ApiOperation("已办任务列表查询")
    @GetMapping("/findHistTaskList")
    public Result findHistTaskList(@RequestParam(value = "jbpmNo", required = false) String jbpmNo,
                                   @RequestParam(value = "createUser", required = false) String createUser,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return jbpmExecutionService.findHistTaskList(jbpmNo, createUser,startTime, endTime,pageNum, pageSize);
    }


    /*
     * 查询审核记录列表
     * @param executionId
     * @return
     */
    @GetMapping("/findHistTaskDeatilList")
    public Result findHistTaskDeatilList(@RequestParam(value = "executionId", required = false) Integer executionId) {
        return jbpmExecutionService.findHistTaskDeatilList(executionId);
    }
}
