package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.InfoBox;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.entity.logistics.LogisticsOutHouseDetail;
import com.fw.entity.logistics.LogisticsOutSubpackage;
import com.fw.web.logistics.service.LogisticsOutHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 出库管理
 * @author gchen
 * @date 2020/11/16
 */
@RestController
@RequestMapping("/logisticsOutHouse")
@Api(value = "LogisticsOutHouseController",description = "出库管理")
public class LogisticsOutHouseController {

    @Resource
    private LogisticsOutHouseService logisticsOutHouseService;


    /**
     * 列表查询
     * @param houseNo houseType status startTime endTime
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "houseNo", required = false) String houseNo,
                           @RequestParam(value = "houseType", required = false) String houseType,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "pickStatus", required = false) Integer pickStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsOutHouseService.findList(houseNo,houseType,status,startTime,endTime,pickStatus,pageNum,pageSize);
    }

    /**
     * 根据物料库存id查询库位详情
     * @param
     */
    @GetMapping("/findByQrCode")
    @ApiOperation("根据物料库存id查询库位详情")
    public Result findByQrCode(@RequestParam(value = "qrCode", required = false) String qrCode,
                               @RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                               @RequestParam(value = "type", required = false) Integer type) {
        return logisticsOutHouseService.findByQrCode(qrCode,outHouseId,type);
    }

    /**
     * 添加出库拆包明细 内部出库
     * @param
     */
    @PostMapping("/saveOutSubPack")
    @ApiOperation("添加出库拆包明细 内部出库")
    public Result saveOutSubPack(@RequestBody InfoBox<LogisticsOutHouseDetail> infoBox) {
        return logisticsOutHouseService.saveOutSubPack(infoBox);
    }

    /**
     * 根据出库对象id查询拆包明细
     * @param
     */
    @GetMapping("/findOutSubpackage")
    @ApiOperation("根据出库对象id查询拆包明细")
    public Result findOutSubpackage(@RequestParam("outHouseId") String outHouseId) {
        return logisticsOutHouseService.findOutSubpackage(outHouseId);
    }
    /**
     * 外部出库
     * @param
     */
    @PostMapping("/saveOutHouseDetail")
    @ApiOperation("外部出库")
    public Result saveOutHouseDetail(@RequestBody InfoBox<LogisticsDeliveryPlan> infoBox) {
        return logisticsOutHouseService.saveOutHouseDetail(infoBox);
    }

    /**
     * 根据生产指令查询出库明细
     * @param
     */
    @GetMapping("/findDetailByProductOrder")
    @ApiOperation("根据生产指令查询出库明细")
    public Result findDetailByProductOrder(@RequestParam("productOrder") String productOrder) {
        return logisticsOutHouseService.findDetailByProductOrder(productOrder);
    }
}
