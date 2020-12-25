package com.fw.service.audit.service;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditQuestion;
import com.fw.entity.plan.Customer;

/**
 * 问题管理
 * @author lpsong
 * @since 2020-11-26
 */
public interface AuditQuestionService {

    /**
     * 新增
     * @param auditQuestion
     * @return
     */
    Result save(AuditQuestion auditQuestion);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result delete(String ids);
    /**
    *  列表查询
     * @param dutyUser
     * @param pageNum
     * @param pageSize
    * @return
    */
    Result findList(String dutyUser, Integer status,Integer pageNum, Integer pageSize);


}
