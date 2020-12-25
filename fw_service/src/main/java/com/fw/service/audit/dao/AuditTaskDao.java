package com.fw.service.audit.dao;


import com.fw.entity.audit.AuditItemDetail;
import com.fw.entity.audit.AuditPlan;
import com.fw.entity.audit.AuditTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface AuditTaskDao {

    Integer insert(AuditTask auditTask);

    Integer update(AuditTask auditTask);

    Integer delete(List<String> list);

    List<LinkedHashMap<String,Object>> findList(
            @Param(value = "taskNo") String taskNo,
            @Param(value = "status") Integer status,
            @Param(value = "executeTime") String executeTime);

    /**
     * 根据任务ID查询已完成详情
     * @param id
     * @return
     */
    List<LinkedHashMap<String,Object>> findTaskDetails(@Param(value = "id") Integer id);

    /**
     * 根据任务ID查询未完成详情
     * @param id
     */
    List<LinkedHashMap<String,Object>> findUnfinishedTaskDetails(@Param(value = "id") Integer id);


    /**
     * 查询审核结果
     * @param list
     * @return
     */
    Integer insertItemDetails(List<AuditItemDetail> list);

    //获取任务单号
    String findCode(@Param(value = "code") String code);

}
