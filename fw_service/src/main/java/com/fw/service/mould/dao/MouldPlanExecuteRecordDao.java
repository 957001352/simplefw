package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldPlanExecuteRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 磨具保养计划执行记录 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-28
 */
@Repository
public interface MouldPlanExecuteRecordDao {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insert(MouldPlanExecuteRecord record);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insertBatch(List<MouldPlanExecuteRecord> record);
}
