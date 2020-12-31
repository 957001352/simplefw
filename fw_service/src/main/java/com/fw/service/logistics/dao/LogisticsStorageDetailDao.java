package com.fw.service.logistics.dao;

import com.fw.entity.logistics.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品库存 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-03
 */
@Repository
public interface LogisticsStorageDetailDao {
    /**
     * 入库，退库
     *
     * @param logisticsStorageDetails
     * @return
     */
    Integer inStorage(@Param("logisticsStorageDetails") List<LogisticsStorageDetail> logisticsStorageDetails);
    /**
    * 上架  @param id storageId
     * @param logisticsUpHouseDetails
    * @return
    */
    Integer updateUpLocation(List<LogisticsUpHouseDetail> logisticsUpHouseDetails);
    /**
     * 下架  @param id storageId
     * @param logisticsDownHouseDetails
     * @return
     */
    Integer updateDownLocation(List<LogisticsDownHouseDetail> logisticsDownHouseDetails);

    /**
     * 移库  @param id storageId
     * @param logisticsMoveHouseDetails
     * @return
     */
    Integer updateMoveLocation(List<LogisticsMoveHouseDetail> logisticsMoveHouseDetails);
    /**
     * 出库 @param  id  storageCount
     * @param logisticsStorageDetails
     * @return
     */
    Integer updateCount(List<LogisticsStorageDetail> logisticsStorageDetails);
    /**
     * 根据id获取仓库信息
     *
     * @param id
     * @return
     */
    LogisticsStorageDetail selectById(@Param("id") Integer id);

    /**
     * 获取库存信息
     * @param id
     * @return
     */
    LogisticsStorageDetail getProductDetail(Integer id);
    /**
     * 获取上架物料库存信息
     * @param id
     * @return
     */
    LogisticsStorageDetail getUpProductDetail(Integer id);

    /**
     * 获取下架物料库存信息
     * @param id
     * @return
     */
    LogisticsStorageDetail getDownProductDetail(@Param("id") Integer id,@Param("storageLocationId") Integer storageLocationId);

}
