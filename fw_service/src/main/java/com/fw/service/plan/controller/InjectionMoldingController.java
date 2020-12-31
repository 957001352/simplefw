package com.fw.service.plan.controller;

import com.fw.domain.Result;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.PlanSwap;
import com.fw.service.plan.service.InjectionMoldingService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注塑排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/injectionMolding")
public class InjectionMoldingController {

    @Autowired
    private InjectionMoldingService injectionMoldingService;

    /**
     * 新增
     * @param injectionMolding
     * @return
     */
    @PostMapping(value = "/insert")
    @RequiresPermissions("injectionMolding:insert")
    public Result insert(@RequestBody InjectionMolding injectionMolding) {
        return injectionMoldingService.insert(injectionMolding);
    }
    /**
     * 编辑  传入计划生产参数和设备id
     */
    @PostMapping(value = "/update")
    @RequiresPermissions("injectionMolding:update")
    public Result update(@RequestBody InjectionMolding injectionMolding) {
        return injectionMoldingService.update(injectionMolding);
    }
    /**
     * 暂停
     * @param injectionStopList
     * @return
     */
    @PostMapping(value = "/stop")
    @RequiresPermissions("injectionMolding:stop")
    public Result stop(@RequestBody InjectionStopList injectionStopList) {
        return injectionMoldingService.stop(injectionStopList);
    }
    /**
     * 列表查询
     * @param productDevicesId
     * @param productCode
     * @param partsCode
     * @param startTime
     * @param endTime
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "productDevicesId", required = false) Integer productDevicesId,
                           @RequestParam(value = "productDeviceCode", required = false) String productDeviceCode,
                           @RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "partsCode", required = false) String partsCode,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return injectionMoldingService.findList(productDevicesId, productDeviceCode,productCode, partsCode, startTime, endTime, status, pageNum, pageSize);
    }
    /**
     * 预排产列表
     * {
     *   "craftMode": {
     *     "firstCheck": 10,
     *     "firstDebug": 20
     *   },
     *   "mouldId": 19,
     *   "planCount": 0,
     *   "productIds": "string"
     * }
     */
    @PostMapping("/findPlanList")
    @RequiresAuthentication
    public Result findPlanList(@RequestBody InjectionMolding injectionMolding) {
        return injectionMoldingService.findPlanList(injectionMolding);
    }

    /**
     * 上移 下移 取消
     * @param planSwap
     */
    @PostMapping(value = "/moveUpDownCancel")
    @RequiresPermissions("injectionMolding:moveUpDownCancel")
    public Result moveUpDownCancel(@RequestBody PlanSwap planSwap) {
        return injectionMoldingService.moveUpDownCancel(planSwap);
    }
}
