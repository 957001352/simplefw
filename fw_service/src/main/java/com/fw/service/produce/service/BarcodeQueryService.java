package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.entity.produce.ProduceFeedingDetail;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 生产过程监控
 * @author lpsong
 * @since 2020-12-17
 */
public interface BarcodeQueryService {

    /**
     * 生产过程监控查询
     * @param
     * @return
     */
    Result findProductProcessList();


    /**
     *  半成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode 零件代码
     * @param partsName 零件名称
     * @return
     */
    Result findMoldingInjectionList(@Param(value = "productOrder") String productOrder,
                                    @Param(value = "partsCode") String partsCode,
                                    @Param(value = "partsName") String partsName,
                                    @Param(value = "partsName") Integer pageNum,
                                    @Param(value = "partsName") Integer pageSize);


    /**
     * 生产工况
     * @param productDevicesId 生产设备ID
     * @return
     */
    Result findNowProductOrder(Integer productDevicesId);
    /**
     * 设备状态监控
     * @param productDevicesCode 设备编码
     * @return
     */
    Result  findProductStatusList(String productDevicesCode);

    /**
     * 产品次品分布
     * @param planMoldingId
     * @return
     */
    Result findBadProductList(Integer planMoldingId);
    /**
     * 投料明细查询
     * @param productOrder
     * @return
     */
    Result findFeedingDetailList(String productOrder);
    /**
     * 查询注塑过程
     * @param planMoldingId
     * @return
     */
    Result findMoldingMonitorList(Integer planMoldingId,String productOrder,String status);


    /**
     *  成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode 零件代码
     * @param partsName 零件名称
     * @return
     */
    Result findReworkInjectionList(@Param(value = "productOrder") String productOrder,
                                   @Param(value = "partsCode") String partsCode,
                                   @Param(value = "partsName") String partsName,
                                   @Param(value = "partsName") Integer pageNum,
                                   @Param(value = "partsName") Integer pageSize);

    /**
     *  根据设备code查询设备在线状态
     */
    Result findDevicesStateByCode(String devicesCode);
}
