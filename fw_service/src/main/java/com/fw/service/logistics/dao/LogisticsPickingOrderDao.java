package com.fw.service.logistics.dao;


import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.logistics.LogisticsPickingOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
@Repository
public interface LogisticsPickingOrderDao {


    Integer insert(LogisticsPickingOrder logisticsPickingOrder);


    List<LogisticsPickingOrder> findList(@Param(value = "outHouseId") Integer outHouseId,@Param(value = "productOrder") String productOrder);

 }
