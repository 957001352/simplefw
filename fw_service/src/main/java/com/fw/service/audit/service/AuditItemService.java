package com.fw.service.audit.service;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;

public interface AuditItemService {

    Result save(AuditItem auditItem);

    Result delete(String ids);

    Result findList(String name,Integer pageNum, Integer pageSize);
}
