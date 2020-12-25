package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import com.fw.service.logistics.service.LogisticsProductService;
import com.fw.service.logistics.service.LogisticsStoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 入库管理
 * @author gchen
 * @date 2020/11/13
 */
@RestController
@RequestMapping("/logisticsStoreHouse")
public class LogisticsStoreHouseController {

    @Resource
    private LogisticsStoreHouseService logisticsStoreHouseServiceImpl;


    /**
     * 列表查询
     * @param houseNo houseType status startTime endTime
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "orderNo", required = false) String orderNo,
                           @RequestParam(value = "houseNo", required = false) String houseNo,
                           @RequestParam(value = "houseType", required = false) String houseType,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "partsType", required = false) String partsType,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsStoreHouseServiceImpl.findList(orderNo,houseNo,houseType,status,startTime,endTime,partsType,pageNum,pageSize);
    }
    /**
     * 根据单号查询入库明细
     */
    @GetMapping(value = "/findByOrderNo")
    public Result findByOrderNo(@RequestParam(value = "storeHouseId") String storeHouseId) {
        return logisticsStoreHouseServiceImpl.findByOrderNo(storeHouseId);
    }
    /**
     * 生产入库
     * @param storeHouseId
     */
    @GetMapping(value = "/storeHouseIn")
    public Result storeHouseIn(@RequestParam(value = "storeHouseId") Integer storeHouseId) {
        return logisticsStoreHouseServiceImpl.storeHouseIn(storeHouseId);
    }
    /**
     * 绑定物料批次
     * @param logisticsStoreHouseDetails
     */
    @PostMapping(value = "/batchBound")
    public Result batchBound(@RequestBody List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails) {
        return logisticsStoreHouseServiceImpl.batchBound(logisticsStoreHouseDetails);
    }

    /**
     * 新增入库单
     * @param logisticsStoreHouse
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody LogisticsStoreHouse logisticsStoreHouse) {
        return logisticsStoreHouseServiceImpl.save(logisticsStoreHouse);
    }

    /**
     * 删除入库单
     * @param logisticsStoreHouse
     */
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody LogisticsStoreHouse logisticsStoreHouse) {
        return logisticsStoreHouseServiceImpl.delete(logisticsStoreHouse);
    }


    /**
     * 根据入库单查询入库明细
     */
    @GetMapping(value = "/findByStoreHouseId")
    public Result findByStoreHouseId(@RequestParam("storeHouseId") Integer storeHouseId) {
        return logisticsStoreHouseServiceImpl.findByStoreHouseId(storeHouseId);
    }
}
