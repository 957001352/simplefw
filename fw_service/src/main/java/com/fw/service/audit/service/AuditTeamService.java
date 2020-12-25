package com.fw.service.audit.service;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTeam;

public interface AuditTeamService {

    Result save(AuditTeam auditTeam);

    Result delete(String ids);

    Result findList(String name,Integer pageNum, Integer pageSize);
}
