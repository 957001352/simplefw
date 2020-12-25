package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsOutHouseDetail;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 出库明细Dao接口
 * @author gchen
 * @since 2020-11-12
 */
@Repository
public interface LogisticsOutHouseDetailDao {
    /**
     * 新增
     * @param logisticsOutHouseDetails
     */
    Integer insert(@Param("logisticsOutHouseDetails") List<LogisticsOutHouseDetail> logisticsOutHouseDetails);

    /**
     * 根据订单号查询入库明细
     */
    List<LogisticsOutHouseDetail> findByOutHouseId(@Param("outHouseId") String outHouseId);

    /**
     * 批量修改
     */
    Integer update(@Param("logisticsOutHouseDetails") List<LogisticsOutHouseDetail> logisticsOutHouseDetails);

    /**
     * 根据订单号,物料id查询入库明细
     */
    List<LogisticsOutHouseDetail> findByOutHouseIdProduct(@Param("outHouseId") Integer outHouseId,
                                                          @Param("productId") Integer productId);

    /**
     * 根据生产指令查询LogisticsOutHouseDetail
     * @param productOrder
     * @return
     */
    List<LogisticsOutHouseDetail> findDetailByProductOrder(String productOrder);

    /**
     * 新增
     */
    Integer insertOne(LogisticsOutHouseDetail logisticsOutHouseDetail);

    /**
     * 根据物料库存id获取LogisticsOutHouseDetail
     * @param storageDetailId
     * @return
     */
    LogisticsOutHouseDetail getStorageCount(Integer storageDetailId);

    /**
     * 根据物料库存id获取LogisticsOutHouseDetail对象
     * @param storageDetailId
     * @return
     */
    List<LogisticsOutHouseDetail> findByDetailId(Integer storageDetailId);


    /**
     * 根据id修改实出数量和投料数量
     * @param id
     * @param
     * @param
     * @return
     */
    Integer updateFeedCount(@Param("id") Integer id, @Param("feedCount")Integer feedCount);
}
