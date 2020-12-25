package com.fw.web.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTask;
import com.fw.web.audit.service.AuditItemService;
import com.fw.web.audit.service.AuditTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 11:05
 **/
@RestController
@RequestMapping(value = "/auditTask")
@Api(description = "分层审核任务", value = "AuditTaskController")
public class AuditTaskController {

    @Autowired
    private AuditTaskService auditTaskService;

    @ApiOperation(value = "获取任务列表")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "taskNo",required = false) String taskNo,
                           @RequestParam(value = "exeUser",required = false) String exeUser,
                           @RequestParam(value = "status",required = false) Integer status,
                           @RequestParam(value = "executeTime",required = false) String executeTime,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ){
        return auditTaskService.findList(taskNo,exeUser,status,executeTime,pageNum,pageSize);
    }


    @ApiOperation(value = "获取任务详细信息")
    @GetMapping(value = "/findTaskDetailsById")
    public Result findTaskDetails(@RequestParam(value = "id",required = true) Integer id,@RequestParam(value = "status",required = true) Integer status) {
        return auditTaskService.findTaskDetails(id,status);
    }

    /**
     * 任务执行
     * @param auditTask
     * @return
     */
    @ApiOperation(value = "任务执行")
    @PostMapping(value = "/exeTask")
    public Result exeTask(@RequestBody AuditTask auditTask){
        return auditTaskService.exeTask(auditTask);
    }

}
