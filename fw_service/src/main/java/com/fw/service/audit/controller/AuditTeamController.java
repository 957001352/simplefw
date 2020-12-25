package com.fw.service.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTeam;
import com.fw.service.audit.service.AuditItemService;
import com.fw.service.audit.service.AuditTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 10:55
 **/
@RestController
@RequestMapping("/auditTeam")
public class AuditTeamController {

    @Autowired
    private AuditTeamService auditTeamService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody AuditTeam auditTeam) {
        return auditTeamService.save(auditTeam);
    }

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return auditTeamService.delete(ids);
    }

    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return auditTeamService.findList(name,pageNum,pageSize);
    }

}
