package com.fw.service.audit.dao;


import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditPlan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditPlanDao {

    Integer insert(AuditPlan auditPlan);

    Integer update(AuditPlan auditPlan);

    Integer delete(List<String> list);

    List<AuditPlan> findList(@Param(value = "name") String name,@Param(value = "auditTeamName") String auditTeamName);

    AuditPlan findPlanByIdOrName(@Param(value = "id") Integer id,@Param(value = "name") String name);
    Integer findPlanByIdAndName(@Param(value = "id") Integer id,@Param(value = "name") String name);

    List<AuditPlan> findListByStatus();
}
