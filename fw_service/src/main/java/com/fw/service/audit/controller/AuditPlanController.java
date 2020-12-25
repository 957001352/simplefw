package com.fw.service.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditPlan;
import com.fw.service.audit.service.AuditItemService;
import com.fw.service.audit.service.AuditPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 10:55
 **/
@RestController
@RequestMapping("/auditPlan")
public class AuditPlanController {

    @Autowired
    private AuditPlanService auditPlanService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody AuditPlan auditPlan) {
        return auditPlanService.save(auditPlan);
    }

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return auditPlanService.delete(ids);
    }

    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "auditTeamName", required = false)String auditTeamName,
                           @RequestParam(value = "userName", required = false)String userName,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return auditPlanService.findList(name,auditTeamName,userName,pageNum,pageSize);
    }

}
