package com.fw.service.logistics.dao;


import com.fw.entity.logistics.LogisticsTakeOrder;
import com.fw.entity.logistics.LogisticsTakeOrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsTakeOrderDao {

    Integer updateTakeOrder(LogisticsTakeOrder logisticsTakeOrder);

    Integer updateTakeOrderDetail(LogisticsTakeOrderDetail logisticsTakeOrderDetail);

    List<LogisticsTakeOrder> findList(@Param(value = "taskNo") String taskNo,@Param(value = "status") Integer status,@Param(value = "kind") Integer kind,@Param(value = "startTime") String startTime, @Param("stopTime") String stopTime);

    //根据收货订单号和物料号查询收货明细
    LogisticsTakeOrderDetail findDetailByTakeOrderIdAndProductID(@Param(value = "takeOrderId") Integer takeOrderId, @Param(value = "productId") Integer productId);

    LogisticsTakeOrder findById(@Param(value = "id") Integer id);

    /**
     *
     * @param date 日期 20201212
     * @return
     */
    String createFwBatchNo(@Param(value = "date") String date);

    //根据id查询收货明细
    LogisticsTakeOrderDetail findDetailByid(@Param(value = "id") String id);

}
