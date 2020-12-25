package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsStoreHouse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 入库Dao接口
 * @author gchen
 * @since 2020-11-12
 */
@Repository
public interface LogisticsStoreHouseDao {
    /**
     * 批量插入
     * @param logisticsStoreHouses
     */
    Integer insert(@Param("logisticsStoreHouses") List<LogisticsStoreHouse> logisticsStoreHouses);

    /**
     * 新增
     * @param logisticsStoreHouse
     */
    Integer insertOne(LogisticsStoreHouse logisticsStoreHouse);

    /**
     * 查询
     */
    List<LogisticsStoreHouse> findList(@Param("orderNo")String orderNo,
                                       @Param("houseNo")String houseNo,
                                       @Param("houseTypes")String[] houseTypes,
                                       @Param("status")Integer status,
                                       @Param("startTime")String startTime,
                                       @Param("endTime")String endTime,
                                       @Param("partsType")String partsType);
    /**
     * 修改
     */
    Integer update(LogisticsStoreHouse logisticsStoreHouse);

    /**
     * 根据id查询
     */
    LogisticsStoreHouse findByStoreHouseId(Integer storeHouseId);

    /**
     * 获取单号
     * @param code
     * @return
     */
    String findCode(@Param(value = "code") String code);

    /**
     * 删除入库单
     */
    Integer delete(Integer id);
}
