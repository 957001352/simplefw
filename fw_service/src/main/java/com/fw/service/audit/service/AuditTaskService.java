package com.fw.service.audit.service;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTask;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public interface AuditTaskService {

    Result findList(String taskNo, String exeUser, Integer status, String executeTime,Integer pageNum,Integer pageSize);

    /**
     * 根据任务ID查询详情
     * @param id
     * @return
     */
    Result findTaskDetails(Integer id,Integer status);


    /**
     * 任务执行
     * @param auditTask
     * @return
     */
    Result exeTask(AuditTask auditTask);
}
