package com.fw.service.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.service.audit.service.AuditItemService;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.DirStateFactory;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-29 10:55
 **/
@RestController
@RequestMapping("/auditItem")
public class AuditItemController {

    @Autowired
    private AuditItemService auditItemService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody AuditItem auditItem){
        return auditItemService.save(auditItem);
    }

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids){
        return auditItemService.delete(ids);
    }

    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name",required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ){
        return auditItemService.findList(name,pageNum,pageSize);
    }

}
