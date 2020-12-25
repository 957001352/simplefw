package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.InfoBox;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.entity.logistics.LogisticsOutHouseDetail;
import com.fw.entity.logistics.LogisticsOutSubpackage;
import com.fw.service.logistics.service.LogisticsOutHouseService;
import org.apache.ibatis.annotations.Param;
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
public class LogisticsOutHouseController {

    @Resource
    private LogisticsOutHouseService logisticsOutHouseServiceImpl;


    /**
     * 列表查询
     * @param houseNo houseType status startTime endTime
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "houseNo", required = false) String houseNo,
                           @RequestParam(value = "houseType", required = false) String houseType,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "pickStatus", required = false) Integer pickStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsOutHouseServiceImpl.findList(houseNo,houseType,status,startTime,endTime,pickStatus,pageNum,pageSize);
    }

    /**
     * 根据物料库存id查询库位详情
     * @param type 出库类型 0-内部出库 1-外部出库
     */
    @GetMapping("/findByQrCode")
    public Result findByQrCode(@RequestParam(value = "qrCode", required = false) String qrCode,
                               @RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                               @RequestParam(value = "type", required = false) Integer type) {
        return logisticsOutHouseServiceImpl.findByQrCode(qrCode,outHouseId,type);
    }

    /**
     * 添加出库拆包明细  内部出库
     * @param
     */
    @PostMapping("/saveOutSubPack")
    public Result saveOutSubPack(@RequestBody InfoBox<LogisticsOutHouseDetail> infoBox) {
        return logisticsOutHouseServiceImpl.saveOutSubPack(infoBox);
    }

    /**
     * 根据出库对象id查询拆包明细
     * @param
     */
    @GetMapping("/findOutSubpackage")
    public Result findOutSubpackage(@RequestParam("outHouseId") String outHouseId) {
        return logisticsOutHouseServiceImpl.findOutSubpackage(outHouseId);
    }
    /**
     * 外部出库
     * @param
     */
    @PostMapping("/saveOutHouseDetail")
    public Result saveOutHouseDetail(@RequestBody InfoBox<LogisticsDeliveryPlan> infoBox) {
        return logisticsOutHouseServiceImpl.saveOutHouseDetail(infoBox);
    }

    /**
     * 根据生产指令查询出库明细
     * @param
     */
    @GetMapping("/findDetailByProductOrder")
    public Result findDetailByProductOrder(@RequestParam("productOrder") String productOrder) {
        return logisticsOutHouseServiceImpl.findDetailByProductOrder(productOrder);
    }

}
