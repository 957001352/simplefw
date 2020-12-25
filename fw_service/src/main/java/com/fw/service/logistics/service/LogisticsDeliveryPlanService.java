package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *发货计划
 * @author lpsong
 * @since 2020-12-04
 */
public interface LogisticsDeliveryPlanService {

    /**
     * 新增
     *
     * @param logisticsDeliveryPlans
     * @return
     */
    Result insert(List<LogisticsDeliveryPlan> logisticsDeliveryPlans);

    /**
     * 新增
     * @param logisticsDeliveryPlan
     * @return
     */
    Result update(LogisticsDeliveryPlan logisticsDeliveryPlan);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result delete(String ids);
    /**
    * 关闭任务
     * @param id
    * @return
    */
    Result updateStatus(Integer id,Integer status);


    /**
    * 列表查询
     * @param customer
     * @param productCode
     * @param deliverTime
     * @param status
     * @param outHouseId
     * @param pageNum
     * @param pageSize
    * @return
    */
    Result findList(String customer,
                    String productCode,
                    String deliverTime,
                    Integer status,
                    Integer outHouseId,
                    Integer queryType,
                    Integer pageNum,
                    Integer pageSize);


    Result findByOutHouseId(Integer outHouseId);
}
