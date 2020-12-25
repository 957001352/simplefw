package com.fw.service.audit.service;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditPlan;

public interface AuditPlanService {

    Result save(AuditPlan auditPlan);

    Result delete(String ids);

    Result findList(String name,String auditTeamName,String userName,Integer pageNum, Integer pageSize);
}
