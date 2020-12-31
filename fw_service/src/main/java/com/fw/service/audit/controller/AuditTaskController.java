package com.fw.service.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTask;
import com.fw.service.audit.service.AuditItemService;
import com.fw.service.audit.service.AuditTaskService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 10:55
 **/
@RestController
@RequestMapping("/auditTask")
public class AuditTaskController {

    @Autowired
    private AuditTaskService auditTaskService;


    @GetMapping(value = "/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "taskNo",required = false) String taskNo,
                           @RequestParam(value = "exeUser",required = false) String exeUser,
                           @RequestParam(value = "status",required = false) Integer status,
                           @RequestParam(value = "executeTime",required = false) String executeTime,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        return auditTaskService.findList(taskNo,exeUser,status,executeTime,pageNum,pageSize);
    }


    @GetMapping(value = "/findTaskDetailsById")
    @RequiresAuthentication
    public Result findTaskDetails(@RequestParam(value = "id",required = true) Integer id,@RequestParam(value = "status",required = true) Integer status) {
        return auditTaskService.findTaskDetails(id,status);
    }

    /**
     * 任务执行
     * @param auditTask
     * @return
     */
    @PostMapping(value = "/exeTask")
    @RequiresPermissions(value = "auditTask:exeTask")
    public Result exeTask(@RequestBody AuditTask auditTask){
        return auditTaskService.exeTask(auditTask);
    }

}
