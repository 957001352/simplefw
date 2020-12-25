package com.fw.service.audit.dao;


import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTeam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditTeamDao {

    Integer insert(AuditTeam auditTeam);

    Integer update(AuditTeam auditTeam);

    Integer delete(List<String> list);

    List<AuditTeam> findList(@Param(value = "name") String name);

    AuditTeam findTeamByIdOrName(@Param(value = "id") Integer id,@Param(value = "name") String name);
    Integer findTeamByIdAndName(@Param(value = "id") Integer id,@Param(value = "name") String name);
}
