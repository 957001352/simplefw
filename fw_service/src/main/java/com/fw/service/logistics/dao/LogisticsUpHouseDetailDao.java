package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsUpHouseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 上架明细 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-10
 */
@Repository
public interface LogisticsUpHouseDetailDao {

    /**
     * 批量插入
     *
     * @param logisticsUpHouseDetail
     * @return
     */
    Integer batchInsert(@Param("logisticsUpHouseDetail") List<LogisticsUpHouseDetail> logisticsUpHouseDetail);

    /**
     * 上下架记录
     *
     * @param code
     * @param storageName
     * @param startCreateTime
     * @return
     */
    List<LogisticsUpHouseDetail> findAllList(@Param("code") String code, @Param("storageName") String storageName, @Param("startCreateTime") String startCreateTime, @Param("endCreateTime") String endCreateTime);
}
