package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.InfoBox;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.entity.logistics.LogisticsOutHouseDetail;
import com.fw.entity.logistics.LogisticsOutSubpackage;

import java.util.List;

/**
 * 出库管理
 *
 * @author gchen
 * @date 2020/11/16
 */
public interface LogisticsOutHouseService {
    /**
     * 列表查询
     * @param houseNo houseType status startTime endTime orderNo
     */
    Result findList(String houseNo, String houseType, String status, String startTime, String endTime, Integer pickStatus,Integer pageNum, Integer pageSize);

    /**
     * 根据物料编码查询物料库存详情
     * @param qrCode
     */
    Result findByQrCode(String qrCode,Integer outHouseId,Integer type);

    /**
     * 添加出库拆包明细   内部出库
     * @param infoBox
     */
    Result saveOutSubPack(InfoBox<LogisticsOutHouseDetail> infoBox);

    /**
     * 根据出库对象id查询拆包明细
     * @param outHouseId
     */
    Result findOutSubpackage(String outHouseId);

    /**
     * 外部出库
     * @param infoBox
     */
    Result saveOutHouseDetail(InfoBox<LogisticsDeliveryPlan> infoBox);

    /**
     * 根据生产指令查询出库明细
     * @param productOrder
     * @return
     */
    Result findDetailByProductOrder(String productOrder);
}
