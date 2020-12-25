package com.fw.web.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBake;
import com.fw.web.produce.service.fbk.BarcodeQueryServiceFbk;
import com.fw.web.produce.service.fbk.ProduceBakeServiceFbk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 生产过程监控
 *
 * @author lpsong
 * @since 2020-12-17
 */
@FeignClient(value = "fw-service/barcodeQuery", fallback = BarcodeQueryServiceFbk.class)
public interface BarcodeQueryService {


    /**
     * 生产过程监控查询
     * @param
     * @return
     */
    @GetMapping(value = "/findProductProcessList")
    Result findProductProcessList();

    /**
     * 半成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode    零件代码
     * @param partsName    零件名称
     * @return
     */
    @GetMapping(value = "/findMoldingInjectionList")
    Result findMoldingInjectionList(@RequestParam(value = "productOrder", required = false) String productOrder,
                                    @RequestParam(value = "partsCode", required = false) String partsCode,
                                    @RequestParam(value = "partsName", required = false) String partsName,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 生产工况
     * @param productDevicesId 生产设备ID
     * @return
     */
    @GetMapping(value = "/findNowProductOrder")
    Result findNowProductOrder(@RequestParam(value = "productDevicesId") Integer productDevicesId);


    /**
     * 设备状态监控
     * @param productDevicesCode 设备编码
     * @return
     */
    @GetMapping(value = "/findProductStatusList")
    Result findProductStatusList(@RequestParam(value = "productDevicesCode") String productDevicesCode);

    /**
     * 产品次品分布
     * @param planMoldingId
     * @return
     */
    @GetMapping(value = "/findBadProductList")
    Result findBadProductList(@RequestParam(value = "planMoldingId") Integer planMoldingId);

    /**
     * 投料明细查询
     * @param productOrder
     * @return
     */
    @GetMapping(value = "/findFeedingDetailList")
    Result findFeedingDetailList(@RequestParam(value = "productOrder") String productOrder);

    /**
     * 查询注塑过程
     * @param planMoldingId
     * @return
     */
    @GetMapping(value = "/findMoldingMonitorList")
    Result findMoldingMonitorList(@RequestParam(value = "planMoldingId", required = false) Integer planMoldingId,
                                  @RequestParam(value = "productOrder", required = false) String productOrder,
                                  @RequestParam(value = "status", required = false) String status);

    /**
     * 成品条码追溯
     * @param productOrder 生产指令
     * @param partsCode    零件代码
     * @param partsName    零件名称
     * @return
     */
    @GetMapping(value = "/findReworkInjectionList")
    Result findReworkInjectionList(@RequestParam(value = "productOrder", required = false) String productOrder,
                                   @RequestParam(value = "partsCode", required = false) String partsCode,
                                   @RequestParam(value = "partsName", required = false) String partsName,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
    /**
     *  根据设备code查询设备在线状态
     */
    @GetMapping(value = "/findDevicesStateByCode")
    Result findDevicesStateByCode(@RequestParam(value = "devicesCode") String devicesCode);
}