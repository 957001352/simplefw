package com.fw.web.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.web.logistics.service.fbk.LogisticsDeliveryPlanServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 发货计划
 * @author lpsong
 * @since 2020-12-04
 */
@FeignClient(value = "fw-service/logisticsDeliveryPlan", fallback = LogisticsDeliveryPlanServiceFbk.class)
public interface LogisticsDeliveryPlanService {

    /**
     * 新增
     *
     * @param logisticsDeliveryPlans
     * @return
     */
    @PostMapping(value = "/insert")
    Result insert(@RequestBody List<LogisticsDeliveryPlan> logisticsDeliveryPlans);

    /**
     * 修改
     *
     * @param logisticsDeliveryPlan
     * @return
     */
    @PostMapping(value = "/update")
    Result update(@RequestBody LogisticsDeliveryPlan logisticsDeliveryPlan);

    /**
     * 批量删除
     * @param ids
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam String ids);
    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "customer", required = false) String customer,
                    @RequestParam(value = "productCode", required = false) String productCode,
                    @RequestParam(value = "deliverTime", required = false) String deliverTime,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                    @RequestParam(value = "queryType", required = false) Integer queryType,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
    /**
     * 根据出库单查询发货
     */
    @GetMapping("/findByOutHouseId")
    Result findByOutHouseId(@RequestParam(value = "outHouseId", required = false) Integer outHouseId);

}
