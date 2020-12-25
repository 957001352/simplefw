package com.fw.web.audit.service;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditQuestion;
import com.fw.web.audit.service.fbk.AuditPlanServiceFbk;
import com.fw.web.audit.service.fbk.AuditQuestionServiceFbk;
import com.fw.web.config.FeignMultipartConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 问题管理
 * @author lpsong
 * @since 2020-11-26
 */
@FeignClient(value = "fw-service/auditQuestion", fallback = AuditQuestionServiceFbk.class, configuration = FeignMultipartConfig.class)
public interface AuditQuestionService {

    /**
     * 新增/修改
     * @param auditQuestion
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody AuditQuestion auditQuestion);
    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids", required = false) String ids);
    /**
     * 列表查询
     * @param dutyUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "dutyUser", required = false) String dutyUser,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


}
