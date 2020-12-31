package com.fw.service.logistics.dao;


import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.logistics.LogisticsPickingOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
@Repository
public interface LogisticsPickingOrderDao {


    Integer insert(LogisticsPickingOrder logisticsPickingOrder);


    List<LogisticsPickingOrder> findList(@Param(value = "outHouseId") Integer outHouseId,@Param(value = "productOrder") String productOrder);

    /**
     * 批量修改实出数量
     */
    Integer updateStorageCountList(@Param("logisticsPickingOrders") List<LogisticsPickingOrder> logisticsPickingOrders);

    /**
     * 根据出库单id查询发货单
     */
    List<LogisticsPickingOrder> findByOutHouseId(@Param("outHouseId") Integer outHouseId);
}
