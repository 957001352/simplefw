package com.fw.service.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsTakeOrder;
import org.apache.ibatis.annotations.Param;


import java.util.List;


public interface LogisticsTakeOrderService {

    /**
     * 收货后，更新收获信息
     * @param logisticsTakeOrder
     * @return
     */
    Result update(LogisticsTakeOrder logisticsTakeOrder);


    /**
     * 获取收获单详细信息
     * @param taskNo
     * @return
     */
    Result  findList(String taskNo,Integer status,Integer kind,String startTime, String stopTime,Integer pageNum, Integer pageSize);
}
