package com.fw.web.audit.service;


import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTeam;
import com.fw.web.audit.service.fbk.AuditItemServiceFbk;
import com.fw.web.audit.service.fbk.AuditTeamServiceFbk;
import com.fw.web.config.FeignMultipartConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/auditTeam", fallback = AuditTeamServiceFbk.class,configuration = FeignMultipartConfig.class)
public interface AuditTeamService {


    @PostMapping(value = "/save")
    public Result save(@RequestBody AuditTeam auditTeam);

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids);

    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
