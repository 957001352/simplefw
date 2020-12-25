package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsDeliveryPlan;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 发货计划
 * @author lpsong
 * @since 2020-12-04
 */
@Repository
public interface LogisticsDeliveryPlanDao {

    /**
     * 新增
     * @param logisticsDeliveryPlans
     * @return
     */
    Integer insert(List<LogisticsDeliveryPlan> logisticsDeliveryPlans);

    /**
     * 修改
     * @param logisticsDeliveryPlan
     * @return
     */
    Integer update(LogisticsDeliveryPlan logisticsDeliveryPlan);

    /**
     * 关闭任务
     * @param id
     * @param status
     * @return
     */
    Integer updateStatus(@Param("id") Integer id, @Param("status") Integer status);


    /**
    * 根据出库单关闭任务
     * @param outHouseId
    * @return
    */
    Integer updateStatusByOutHouseId(@Param("outHouseId") Integer outHouseId);

    /**
     * 删除
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);
    /**
     * 根据id获取发货计划信息
     * @param list
     * @return
     */
    List<LogisticsDeliveryPlan> selectById(List<String> list);


    /**
     * 根据id获取发货计划信息
     * @param id
     * @return
     */
    LogisticsDeliveryPlan selectPlanById(Integer id);
    /**
    * 列表查询
     * @param customer
     * @param productCode
     * @param deliverTime
     * @param status
     * @param outHouseId
    * @return
    */
    List<LogisticsDeliveryPlan> findList(@Param("customer") String customer,
                                         @Param("productCode") String productCode,
                                         @Param("deliverTime") String deliverTime,
                                         @Param("status") Integer status,
                                         @Param("outHouseId") Integer outHouseId,
                                         @Param("queryType") Integer  queryType);
    /**
    * 根据出库单id查询该出库单下是否还有发货计划
     * @param outHouseId
    * @return
    */
    Integer  findListByOutHouseId(@Param("outHouseId") Integer outHouseId);
    /**
    * 根据客户查询出库单id
     * @param customer
    * @return
    */
    Integer selectOutHouseIdByCustomer(@Param("customer") String customer);

    /**
     * 批量修改
     * @param logisticsDeliveryPlans
     * @return
     */
    Integer updateList(List<LogisticsDeliveryPlan> logisticsDeliveryPlans);

    /**
     * 根据出库单查询
     */
    List<LogisticsDeliveryPlan> findByOutHouseId(Integer outHouseId);
}
