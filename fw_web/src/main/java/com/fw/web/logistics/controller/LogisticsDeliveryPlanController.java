package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.web.logistics.service.LogisticsDeliveryPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 发货计划
 * @author lpsong
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/logisticsDeliveryPlan")
@Api(description = "发货计划", value = "LogisticsDeliveryPlanController")
public class LogisticsDeliveryPlanController {

    @Autowired
    private LogisticsDeliveryPlanService logisticsDeliveryPlanService;

    /**
     * 新增
     * @param logisticsDeliveryPlans
     * @return
     */
    @PostMapping(value = "/insert")
    @ApiOperation("新增")
    public Result insert(@RequestBody List<LogisticsDeliveryPlan> logisticsDeliveryPlans) {
        return logisticsDeliveryPlanService.insert(logisticsDeliveryPlans);
    }

    /**
     * 修改
     * @param logisticsDeliveryPlan
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public Result update(@RequestBody LogisticsDeliveryPlan logisticsDeliveryPlan) {
        return logisticsDeliveryPlanService.update(logisticsDeliveryPlan);
    }
    /**
     * 批量删除
     * @param ids
     */
    @GetMapping(value = "/delete")
    @ApiOperation("删除")
    public Result delete(@RequestParam String ids) {
        return logisticsDeliveryPlanService.delete(ids);
    }
    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "customer", required = false) String customer,
                           @RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "deliverTime", required = false) String deliverTime,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                           @RequestParam(value = "queryType", required = false) Integer queryType,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsDeliveryPlanService.findList(customer, productCode,deliverTime,status,outHouseId,queryType,pageNum, pageSize);
    }

    /**
     * 根据出库单查询发货
     */
    @GetMapping("/findByOutHouseId")
    //@RequiresAuthentication
    @ApiOperation("根据出库单查询发货")
    public Result findByOutHouseId(@RequestParam(value = "outHouseId", required = false) Integer outHouseId) {
        return logisticsDeliveryPlanService.findByOutHouseId(outHouseId);
    }
}
