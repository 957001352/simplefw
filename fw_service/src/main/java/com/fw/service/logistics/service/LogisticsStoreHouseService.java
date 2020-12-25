package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;

import java.util.List;

/**
 * 入库管理
 *
 * @author gchen
 * @date 2020/11/13
 */
public interface LogisticsStoreHouseService {
    /**
     * 列表查询
     * @param houseNo houseType status startTime endTime orderNo
     */
    Result findList(String orderNo,String houseNo,String houseType,Integer status,String startTime,String endTime,String partsType,Integer pageNum,Integer pageSize);
    /**
     * 根据入库订单号查询入库明细
     */
    Result findByOrderNo(String storeHouseId);
    /**
     * 入库操作
     * @param storeHouseId
     */
    Result storeHouseIn(Integer storeHouseId);
    /**
     * 绑定批次号
     * @param logisticsStoreHouseDetails
     */
    Result batchBound(List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails);

    /**
     * 保存入库单和入库明细
     * @param logisticsStoreHouse
     */
    Result saveStoreHouse(LogisticsStoreHouse logisticsStoreHouse);

    /**
     * 新增入库单
     * @param logisticsStoreHouse
     */
    Result save(LogisticsStoreHouse logisticsStoreHouse);

    /**
     * 删除入库单
     * @param logisticsStoreHouse
     */
    Result delete(LogisticsStoreHouse logisticsStoreHouse);

    /**
     *
     */
    Result findByStoreHouseId(Integer storeHouseId);
}
