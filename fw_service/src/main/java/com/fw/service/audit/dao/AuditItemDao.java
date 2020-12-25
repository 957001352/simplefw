package com.fw.service.audit.dao;


import com.fw.entity.audit.AuditItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditItemDao {

    Integer insert(AuditItem auditItem);

    Integer update(AuditItem auditItem);

    Integer delete(List<String> list);

    List<AuditItem> findList(@Param(value = "name") String name);

    AuditItem findItemByNameOrid(@Param(value = "id") Integer id,@Param(value = "name") String name);

    Integer findItemByIdAndName(@Param(value = "id") Integer id,@Param(value = "name") String name);
}
