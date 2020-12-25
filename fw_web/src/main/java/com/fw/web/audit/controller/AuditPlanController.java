package com.fw.web.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditPlan;
import com.fw.web.audit.service.AuditItemService;
import com.fw.web.audit.service.AuditPlanService;
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
@RequestMapping(value = "/auditPlan")
@Api(description = "分层审核计划管理", value = "AuditPlanController")
public class AuditPlanController {

    @Autowired
    private AuditPlanService auditPlanService;

    @ApiOperation("新增/更新/禁用/启用")
    @PostMapping(value = "/save")
    public Result save(@RequestBody AuditPlan auditPlan) {
        return auditPlanService.save(auditPlan);
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return auditPlanService.delete(ids);
    }

    @ApiOperation("查询")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "auditTeamName", required = false)String auditTeamName,
                           @RequestParam(value = "userName", required = false)String userName,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)

    {
        return auditPlanService.findList(name,auditTeamName,userName,pageNum,pageSize);
    }

}
