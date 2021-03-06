package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import com.fw.web.logistics.service.LogisticsStoreHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库管理
 * @author gchen
 * @date 2020/11/13
 */
@RestController
@RequestMapping("/logisticsStoreHouse")
@Api(value = "logisticsStoreHouseController",description = "入库管理")
public class LogisticsStoreHouseController {

    @Autowired
    private LogisticsStoreHouseService logisticsStoreHouseService;


    /**
     * 列表查询
     * @param houseNo houseType status startTime endTime
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "orderNo", required = false) String orderNo,
                           @RequestParam(value = "houseNo", required = false) String houseNo,
                           @RequestParam(value = "houseType", required = false) String houseType,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "partsType", required = false) String partsType,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsStoreHouseService.findList(orderNo,houseNo,houseType,status,startTime,endTime,partsType,pageNum,pageSize);
    }
    /**
     * 根据单号查询入库明细
     */
    @GetMapping(value = "/findByOrderNo")
    @ApiOperation("根据单号查询入库明细")
    public Result findByOrderNo(@RequestParam(value = "storeHouseId") String storeHouseId) {
        return logisticsStoreHouseService.findByOrderNo(storeHouseId);
    }
    /**
     * 入库操作
     * @param storeHouseId
     */
    @GetMapping(value = "/storeHouseIn")
    @ApiOperation("入库操作")
    public Result storeHouseIn(@RequestParam(value = "storeHouseId") Integer storeHouseId) {
        return logisticsStoreHouseService.storeHouseIn(storeHouseId);
    }
    /**
     * 绑定物料批次
     * @param logisticsStoreHouseDetails
     */
    @PostMapping(value = "/batchBound")
    @ApiOperation("绑定物料批次")
    public Result batchBound(@RequestBody List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails) {
        return logisticsStoreHouseService.batchBound(logisticsStoreHouseDetails);
    }
    /**
     * 新增入库单
     * @param logisticsStoreHouse
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody LogisticsStoreHouse logisticsStoreHouse) {
        return logisticsStoreHouseService.save(logisticsStoreHouse);
    }

    /**
     * 删除入库单
     * @param logisticsStoreHouse
     */
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody LogisticsStoreHouse logisticsStoreHouse) {
        return logisticsStoreHouseService.delete(logisticsStoreHouse);
    }
    /**
     * 根据入库单查询入库明细
     */
    @GetMapping(value = "/findByStoreHouseId")
    public Result findByStoreHouseId(@RequestParam("storeHouseId") Integer storeHouseId) {
        return logisticsStoreHouseService.findByStoreHouseId(storeHouseId);
    }
}
