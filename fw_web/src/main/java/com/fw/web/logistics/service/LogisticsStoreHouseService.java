package com.fw.web.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import com.fw.web.logistics.service.fbk.LogisticsStoreHouseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 入库
 * @Author gchen
 * @Date 2020/11/13
 **/
@FeignClient(value = "fw-service/logisticsStoreHouse", fallback = LogisticsStoreHouseServiceFbk.class)
public interface LogisticsStoreHouseService {
    /**
     * 列表查询
     * @param houseNo houseType status startTime endTime
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "orderNo", required = false) String orderNo,
                    @RequestParam(value = "houseNo", required = false) String houseNo,
                    @RequestParam(value = "houseType", required = false) String houseType,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "startTime", required = false) String startTime,
                    @RequestParam(value = "endTime", required = false) String endTime,
                    @RequestParam(value = "partsType", required = false) String partsType,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
    /**
     * 根据单号查询入库明细
     */
    @GetMapping(value = "/findByOrderNo")
    Result findByOrderNo(@RequestParam(value = "storeHouseId") String storeHouseId);
    /**
     * 入库操作
     * @param storeHouseId
     */
    @GetMapping(value = "/storeHouseIn")
    Result storeHouseIn(@RequestParam(value = "storeHouseId") Integer storeHouseId);
    /**
     * 绑定物料批次
     * @param logisticsStoreHouseDetails
     */
    @PostMapping(value = "/batchBound")
    Result batchBound(@RequestBody List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails);

    /**
     * 新增入库单
     * @param logisticsStoreHouse
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsStoreHouse logisticsStoreHouse);

    /**
     * 删除入库单
     * @param logisticsStoreHouse
     */
    @PostMapping(value = "/delete")
    Result delete(@RequestBody LogisticsStoreHouse logisticsStoreHouse);

    /**
     * 根据入库单查询入库明细
     */
    @GetMapping(value = "/findByStoreHouseId")
    Result findByStoreHouseId(@RequestParam("storeHouseId") Integer storeHouseId);
}
