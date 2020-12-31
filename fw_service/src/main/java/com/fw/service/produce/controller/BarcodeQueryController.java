package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.service.produce.service.BarcodeQueryService;
import com.fw.service.produce.service.ProduceDutyService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 生产过程监控
 * @author lpsong
 * @since 2020-12-17
 */
@RequestMapping(value = "/barcodeQuery")
@RestController
public class BarcodeQueryController {

    @Autowired
    private BarcodeQueryService barcodeQueryService;
    /**
     * 生产过程监控查询
     * @param
     * @return
     */
    @GetMapping(value = "/findProductProcessList")
    @RequiresAuthentication
    public Result findProductProcessList(){
        return barcodeQueryService.findProductProcessList();
    }
    /**
     *  半成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode 零件代码
     * @param partsName 零件名称
     * @return
     */
    @GetMapping(value = "/findMoldingInjectionList")
    @RequiresAuthentication
    public Result findMoldingInjectionList(@RequestParam(value = "productOrder", required = false) String productOrder,
                                           @RequestParam(value = "partsCode", required = false) String partsCode,
                                           @RequestParam(value = "partsName", required = false) String partsName,
                                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return barcodeQueryService.findMoldingInjectionList(productOrder, partsCode, partsName, pageNum, pageSize);
    }

    /**
     * 生产工况
     * @param productDevicesId 生产设备ID
     * @return
     */
    @GetMapping(value = "/findNowProductOrder")
    @RequiresAuthentication
    public Result findNowProductOrder(@RequestParam(value = "productDevicesId") Integer productDevicesId){
        return barcodeQueryService.findNowProductOrder(productDevicesId);
    }


    /**
     * 设备状态监控
     * @param productDevicesCode 设备编码
     * @return
     */
    @GetMapping(value = "/findProductStatusList")
    @RequiresAuthentication
    public Result  findProductStatusList(@RequestParam(value = "productDevicesCode") String productDevicesCode){
        return barcodeQueryService.findProductStatusList(productDevicesCode);
    }

    /**
     * 产品次品分布
     * @param planMoldingId
     * @return
     */
    @GetMapping(value = "/findBadProductList")
    @RequiresAuthentication
    public Result findBadProductList(@RequestParam(value = "planMoldingId") Integer planMoldingId){
        return barcodeQueryService.findBadProductList(planMoldingId);
    }

    /**
     * 投料明细查询
     * @param productOrder
     * @return
     */
    @GetMapping(value = "/findFeedingDetailList")
    @RequiresAuthentication
    public Result findFeedingDetailList(@RequestParam(value = "productOrder") String productOrder){
        return barcodeQueryService.findFeedingDetailList(productOrder);
    }

    /**
     * 查询注塑过程
     * @param planMoldingId
     * @return
     */
    @GetMapping(value = "/findMoldingMonitorList")
    @RequiresAuthentication
    public Result findMoldingMonitorList(@RequestParam(value = "planMoldingId", required = false) Integer planMoldingId,
                                         @RequestParam(value = "productOrder", required = false) String productOrder,
                                         @RequestParam(value = "status", required = false) String status) {
        return barcodeQueryService.findMoldingMonitorList(planMoldingId, productOrder,status);
    }
    /**
     *  成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode 零件代码
     * @param partsName 零件名称
     * @return
     */
    @GetMapping(value = "/findReworkInjectionList")
    @RequiresAuthentication
    public Result findReworkInjectionList(@RequestParam(value = "productOrder", required = false) String productOrder,
                                          @RequestParam(value = "partsCode", required = false) String partsCode,
                                          @RequestParam(value = "partsName", required = false) String partsName,
                                          @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return barcodeQueryService.findReworkInjectionList(productOrder, partsCode, partsName, pageNum, pageSize);
    }

    /**
     *  根据设备code查询设备在线状态
     */
    @GetMapping(value = "/findDevicesStateByCode")
    @RequiresAuthentication
    public Result findDevicesStateByCode(@RequestParam(value = "devicesCode") String devicesCode) {
        return barcodeQueryService.findDevicesStateByCode(devicesCode);
    }
}
