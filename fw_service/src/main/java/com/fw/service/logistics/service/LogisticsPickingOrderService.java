package com.fw.service.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.logistics.LogisticsPickingOrder;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
public interface LogisticsPickingOrderService {

    Result save(LogisticsPickingOrder logisticsPickingOrder);

    Result findList(Integer outHouseId,String  productOrder);
    /**
     * 根据出库单查询领料明细
     */
    Result findByOutHouseId(Integer outHouseId);

}
