package com.fw.service.audit.dao;

import com.fw.entity.audit.AuditItemDetail;
import com.fw.entity.audit.AuditQuestion;
import com.fw.entity.plan.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 问题管理
 * @author lpsong
 * @since 2020-11-26
 */
@Repository
public interface AuditQuestionDao {

    /**
     * 新增
     * @param auditQuestion
     * @return
     */
    Integer insert(AuditQuestion auditQuestion);


    /**
    * 批量新增
     * @param list
    * @return
    */
    Integer insertBatch(@Param("list") List<AuditItemDetail> list, @Param("createUser") Integer createUser);
    /**
     * 修改
     * @param auditQuestion
     * @return
     */
    Integer update(AuditQuestion auditQuestion);


    /**
     *取消
     * @param list
     * @return
     */
    Integer delete(List<String> list);
    /**
     * 根据id获取仓库信息
     *
     * @param id
     * @return
     */
    AuditQuestion selectById(@Param("id") Integer id);

    /**
    *  列表查询
     * @param createUserIds
    * @return
    */
    List<AuditQuestion> findList(@Param("createUserIds") List<Integer> createUserIds,@Param("status") Integer status);


}
