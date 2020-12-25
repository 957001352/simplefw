package com.fw.service.device.dao;

import com.fw.entity.device.DevicesPlanExecuteRecord;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * 设备保养计划下发任务 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Repository
public interface DevicesPlanExecuteRecordDao {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insert(DevicesPlanExecuteRecord record);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insertBatch(List<DevicesPlanExecuteRecord> record);
}
