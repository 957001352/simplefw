package com.fw.web.audit.controller;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditQuestion;
import com.fw.web.audit.service.AuditQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 问题管理
 * @author lpsong
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/auditQuestion")
@Api(description = "问题管理", value = "AuditQuestionController")
public class AuditQuestionController {

    @Autowired
    private AuditQuestionService auditQuestionService;

    /**
     * 新增/修改
     * @param auditQuestion
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增/修改")
    public Result save(@RequestBody AuditQuestion auditQuestion) {
        return auditQuestionService.save(auditQuestion);
    }
    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam(value = "ids", required = false) String ids) {
        return auditQuestionService.delete(ids);
    }
    /**
     * 列表查询
     * @param dutyUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation(value = "列表查询")
    public Result findList(@RequestParam(value = "dutyUser", required = false) String dutyUser,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return auditQuestionService.findList(dutyUser,status, pageNum, pageSize);
    }
}
