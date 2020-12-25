package com.fw.web.plan.service;

import com.fw.domain.Result;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.PlanSwap;
import com.fw.web.plan.service.fbk.InjectionMoldingServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 排产客户管理
 * @author lpsong
 * @since 2020-11-25
 */
@FeignClient(value = "fw-service/injectionMolding", fallback = InjectionMoldingServiceFbk.class)
public interface InjectionMoldingService {


    /**
     * 新增
     * @param injectionMolding
     * @return
     */
    @PostMapping(value = "/insert")
     Result insert(@RequestBody InjectionMolding injectionMolding);
    /**
     * 修改
     * @param injectionMolding
     * @return
     */
    @PostMapping(value = "/update")
     Result update(@RequestBody InjectionMolding injectionMolding);
    /**
     * 暂停
     * @param injectionStopList
     * @return
     */
    @PostMapping(value = "/stop")
     Result stop(@RequestBody InjectionStopList injectionStopList);
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
     Result findList(@RequestParam(value = "productDevicesId", required = false) Integer productDevicesId,
                        @RequestParam(value = "productDeviceCode", required = false) String productDeviceCode,
                           @RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "partsCode", required = false) String partsCode,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
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
     Result findPlanList(@RequestBody InjectionMolding injectionMolding);

    /**
     * 上移 下移 取消
     * @param planSwap
     */
    @PostMapping(value = "/moveUpDownCancel")
     Result moveUpDownCancel(@RequestBody PlanSwap planSwap);

}
