package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 物料管理 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-03
 */
@Repository
public interface LogisticsProductDao {

    /**
     * 批量插入
     *
     * @param logisticsProduct
     * @return
     */
    Integer batchInsert(@Param("logisticsProduct") List<LogisticsProduct> logisticsProduct);

    /**
     * 批量修改库存
     *
     * @param logisticsProduct
     * @return
     */
    Integer batchUpdateStore(@Param("logisticsProduct") List<LogisticsProduct> logisticsProduct);

    /**
     * 批量修改超期
     *
     * @param logisticsProduct
     * @return
     */
    Integer batchUpdateWarn(@Param("logisticsProduct") List<LogisticsProduct> logisticsProduct);

    /**
     * 获取物料
     *
     * @param id
     * @return
     */
    LogisticsProduct getLogisticsProduct(Integer id);

    /**
     * 修改
     *
     * @param logisticsProduct
     * @return
     */
    Integer update(LogisticsProduct logisticsProduct);

    /**
     * 列表查询
     *
     * @param code
     * @param name
     * @return
     */
    List<LogisticsProduct> findList(@Param("code") String code, @Param("name") String name);

    /**
     * 超期预警
     *
     * @param name
     * @param code
     * @return
     */
    List<LogisticsProduct> findExceedWarn(@Param("name") String name, @Param("code") String code);

    /**
     * 库存预警
     *
     * @param name
     * @param code
     * @return
     */
    List<LogisticsProduct> findStoreWarn(@Param("name") String name, @Param("code") String code);

    /**
     * 根据code获取物料详细信息
     *
     * @param code
     * @return
     */
    LogisticsProduct getProductDetail(@Param("code") String code);

    /**
     * 物料明细列表
     *
     * @param code
     * @param name
     * @param storageCode
     */
    List<LogisticsProduct> findDetailList(@Param("code") String code, @Param("name") String name, @Param("storageCode") String storageCode);

    /**
     * 根据物料库存id查询物料详情
     *
     * @param storageDetailId
     */
    LogisticsProduct findByStorageDetailId(@Param("storageDetailId") Integer storageDetailId);

    /**
     * 获取所有物料code
     *
     * @return
     */
    List<LogisticsProduct> getAllCode();

    /**
     * 根据code获取物料名称
     *
     * @param code
     * @return
     */
    LogisticsProduct getNameByCode(String code);

    /**
     * 根据code获取物料信息
     */
    List<LogisticsProduct> findListByCode(List<String> codes);
}
