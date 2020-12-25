package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 入库明细Dao接口
 * @author gchen
 * @since 2020-11-12
 */
@Repository
public interface LogisticsStoreHouseDetailDao {
    /**
     * 新增
     * @param logisticsStoreHouseDetails
     */
    Integer insert(@Param("logisticsStoreHouseDetails") List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails);

    /**
     * 根据订单号查询入库明细
     */
    List<LogisticsStoreHouseDetail> findByOrderNo(@Param("storeHouseId") String storeHouseId);

    /**
     * 入库到暂存区
     */
    Integer storeHouseIn(@Param("storeHouseId") Integer storeHouseId,
                         @Param("storageLocationId") Integer storageLocationId);

    /**
     * 绑定批次号
     */
    Integer batchBound(@Param("logisticsStoreHouseDetails") List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails);

    /**
     * 根据入库id查询入库明细
     */
    List<LogisticsStoreHouseDetail> findByStoreHouseId(Integer storeHouseId);

    /**
     * 删除入库单相关明细
     */
    Integer delete(Integer storeHouseId);
}
