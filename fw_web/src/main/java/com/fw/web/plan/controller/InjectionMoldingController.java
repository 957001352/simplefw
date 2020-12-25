package com.fw.web.plan.controller;

import com.fw.domain.Result;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.PlanSwap;
import com.fw.web.plan.service.InjectionMoldingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注塑排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/injectionMolding")
@Api(description = "注塑排产计划",value = "InjectionMoldingController")
public class InjectionMoldingController {

    @Autowired
    private InjectionMoldingService injectionMoldingService;

    /**
     * 新增
     * @param injectionMolding
     * @return
     */
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增")
    public Result insert(@RequestBody InjectionMolding injectionMolding) {
        return injectionMoldingService.insert(injectionMolding);
    }
    /**
     * 编辑
     * @param injectionMolding
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation(value ="编辑")
    public Result update(@RequestBody InjectionMolding injectionMolding) {
        return injectionMoldingService.update(injectionMolding);
    }
    /**
     * 暂停
     * @param injectionStopList
     * @return
     */
    @PostMapping(value = "/stop")
    @ApiOperation(value ="暂停")
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
    @ApiOperation(value ="列表查询")
    public Result findList(@RequestParam(value = "productDevicesId", required = false) Integer productDevicesId,
                           @RequestParam(value = "productDeviceCode", required = false) String productDeviceCode,
                           @RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "partsCode", required = false) String partsCode,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return injectionMoldingService.findList(productDevicesId,productDeviceCode,productCode, partsCode, startTime, endTime, status, pageNum, pageSize);
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
    @ApiOperation(value ="预排产列表")
    public Result findPlanList(@RequestBody InjectionMolding injectionMolding) {
        return injectionMoldingService.findPlanList(injectionMolding);
    }

    /**
     * 上移 下移 取消
     * @param planSwap
     */
    @PostMapping(value = "/moveUpDownCancel")
    @ApiOperation(value ="上移 下移 取消")
    public Result moveUpDownCancel(@RequestBody PlanSwap planSwap) {
        return injectionMoldingService.moveUpDownCancel(planSwap);
    }
}
