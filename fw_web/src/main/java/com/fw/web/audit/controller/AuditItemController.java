package com.fw.web.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.web.audit.service.AuditItemService;
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
@RequestMapping(value = "/auditItem")
@Api(description = "分层审核项目", value = "AuditItemController")
public class AuditItemController {

    @Autowired
    private AuditItemService auditItemService;

    @ApiOperation("新增/更新")
    @PostMapping(value = "/save")
    public Result save(@RequestBody AuditItem auditItem) {
        return auditItemService.save(auditItem);
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return auditItemService.delete(ids);
    }

    @ApiOperation("查询")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return auditItemService.findList(name,pageNum,pageSize);
    }

}
