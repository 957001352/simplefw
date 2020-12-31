package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.service.logistics.service.LogisticsDeliveryPlanService;
import com.fw.service.mould.service.impl.MouldDevicesServiceImpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class LogisticsDeliveryPlanController {

    @Autowired
    private LogisticsDeliveryPlanService logisticsDeliveryPlanService;

    /**
     * 新增
     * @param logisticsDeliveryPlans
     * @return
     */
    @PostMapping(value = "/insert")
    @RequiresPermissions("logisticsDeliveryPlan:insert")
    public Result insert(@RequestBody List<LogisticsDeliveryPlan> logisticsDeliveryPlans) {
        return logisticsDeliveryPlanService.insert(logisticsDeliveryPlans);
    }

    /**
     * 盘库
     * @param logisticsDeliveryPlan
     * @return
     */
    @PostMapping(value = "/update")
    @RequiresPermissions("logisticsDeliveryPlan:update")
    public Result update(@RequestBody LogisticsDeliveryPlan logisticsDeliveryPlan) {
        return logisticsDeliveryPlanService.update(logisticsDeliveryPlan);
    }
    /**
     * 批量删除
     * @param ids
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("logisticsDeliveryPlan:delete")
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
    @RequiresAuthentication
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
    @RequiresAuthentication
    public Result findByOutHouseId(@RequestParam(value = "outHouseId", required = false) Integer outHouseId) {
        return logisticsDeliveryPlanService.findByOutHouseId(outHouseId);
    }
}
