package com.fw.service.jbpm.controller;

import com.fw.domain.Result;
import com.fw.entity.jbpm.JbpmExecution;
import com.fw.service.jbpm.service.JbpmExecutionService;
import com.fw.service.util.JbpmUtil;
import com.fw.utils.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 流程审核
 * @author lpsong
 * @since 2020-10-22
 */
@RestController
@RequestMapping("/jbpmExecution")
public class JbpmExecutionController {

    @Autowired
    private JbpmExecutionService jbpmExecutionService;



    @Autowired
    private JbpmUtil jbpmUtil;
    /**
     * 任务发起
     * @param jbpmExecution
     * @return
     */
    @PostMapping(value = "/startExecution")
    //@RequiresPermissions("jbpmExecution:startExecution")
    public Result startExecution(@RequestBody JbpmExecution jbpmExecution) {
        Integer flag=jbpmUtil.startExecution(jbpmExecution.getId(),jbpmExecution.getDataId(),jbpmExecution.getCreateUser(),jbpmExecution.getFormCode());
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 任务办理
     * @param jbpmExecution
     * @return
     */
    @PostMapping(value = "/doTask")
    //@RequiresPermissions("jbpmExecution:doTask")
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
    @GetMapping("/findWaitTaskList")
    @RequiresAuthentication
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
    @GetMapping("/findHistTaskList")
    @RequiresAuthentication
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
    @RequiresAuthentication
    public Result findHistTaskDeatilList(@RequestParam(value = "executionId", required = false) Integer executionId) {
        return jbpmExecutionService.findHistTaskDeatilList(executionId);
    }

}
