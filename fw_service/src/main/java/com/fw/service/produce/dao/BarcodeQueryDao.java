package com.fw.service.produce.dao;


import com.fw.entity.produce.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 生产过程监控
 * @author lpsong
 * @since 2020-12-17
 */
@Repository
public interface BarcodeQueryDao {


    /**
     * 生产过程监控查询
     * @param
     * @return
     */
    List<LinkedHashMap<String,Object>> findProductProcessList();


    /**
    *  半成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode 零件代码
     * @param partsName 零件名称
    * @return
    */
    List<LinkedHashMap<String,Object>> findMoldingInjectionList(@Param(value = "productOrder") String productOrder,
                                                                @Param(value = "partsCode") String partsCode,
                                                                @Param(value = "partsName") String partsName);


    /**
     * 生产工况
     * @param productDevicesId 生产设备ID
     * @return
     */
    List<LinkedHashMap<String,Object>> findNowProductOrder(Integer productDevicesId);

    /**
    * 设备状态监控
     * @param productDevicesCode 设备编码
    * @return
    */
    List<LinkedHashMap<String,Object>>  findProductStatusList(String productDevicesCode);

    /**
    * 产品次品分布
     * @param planMoldingId
    * @return
    */
    List<LinkedHashMap<String,Object>> findBadProductList(Integer planMoldingId);

    /**
    * 投料明细查询
     * @param productOrder
    * @return
    */
    List<ProduceFeedingDetail> findFeedingDetailList(String productOrder);

    /**
    * 查询注塑过程
     * @param planMoldingId
     * @param productOrder
    * @return
    */
    List<ProduceMoldingMonitor> findMoldingMonitorList(@Param(value = "planMoldingId") Integer planMoldingId,
                                                        @Param(value = "productOrder") String productOrder,
                                                        @Param(value = "status") List<String> status);


    /**
     *  成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode 零件代码
     * @param partsName 零件名称
     * @return
     */
    List<ProduceReworkMonitor> findReworkInjectionList(@Param(value = "productOrder") String productOrder,
                                                       @Param(value = "partsCode") String partsCode,
                                                       @Param(value = "partsName") String partsName);
}
