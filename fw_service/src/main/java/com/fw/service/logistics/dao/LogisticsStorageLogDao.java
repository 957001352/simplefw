package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsStorageLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 库存操作日志 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-03
 */
@Repository
public interface LogisticsStorageLogDao {

    /**
     * 新增
     *
     * @param logisticsStorageLogs
     * @return
     */
    Integer insert(List<LogisticsStorageLog> logisticsStorageLogs);

    /**
    * 列表查询
     * @param
    * @return
    */
    List<LogisticsStorageLog> findList();
}
