package com.fw.web.audit.service;


import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTask;
import com.fw.web.audit.service.fbk.AuditItemServiceFbk;
import com.fw.web.audit.service.fbk.AuditTaskServiceFbk;
import com.fw.web.config.FeignMultipartConfig;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/auditTask", fallback = AuditTaskServiceFbk.class,configuration = FeignMultipartConfig.class)
public interface AuditTaskService {


    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "taskNo",required = false) String taskNo,
                           @RequestParam(value = "exeUser",required = false) String exeUser,
                           @RequestParam(value = "status",required = false) Integer status,
                           @RequestParam(value = "executeTime",required = false) String executeTime,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    );

    @GetMapping(value = "/findTaskDetailsById")
    public Result findTaskDetails(@RequestParam(value = "id",required = true) Integer id,@RequestParam(value = "status",required = true) Integer status);


    @PostMapping(value = "/exeTask")
    public Result exeTask(@RequestBody AuditTask auditTask);
}
