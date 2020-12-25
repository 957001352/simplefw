package com.fw.web.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.InfoBox;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.entity.logistics.LogisticsOutHouseDetail;
import com.fw.entity.logistics.LogisticsOutSubpackage;
import com.fw.web.logistics.service.fbk.LogisticsOutHouseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 入库
 *
 * @Author gchen
 * @Date 2020/11/13
 **/
@FeignClient(value = "fw-service/logisticsOutHouse", fallback = LogisticsOutHouseServiceFbk.class)
public interface LogisticsOutHouseService {
    /**
     * 列表查询
     *
     * @param houseNo houseType status startTime endTime
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "houseNo", required = false) String houseNo,
                    @RequestParam(value = "houseType", required = false) String houseType,
                    @RequestParam(value = "status", required = false) String status,
                    @RequestParam(value = "startTime", required = false) String startTime,
                    @RequestParam(value = "endTime", required = false) String endTime,
                    @RequestParam(value = "pickStatus", required = false) Integer pickStatus,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 根据物料库存id查询库位详情
     *
     * @param
     */
    @GetMapping("/findByQrCode")
    Result findByQrCode(@RequestParam(value = "qrCode", required = false) String qrCode,
                        @RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                        @RequestParam(value = "type", required = false) Integer type);

    /**
     * 添加出库拆包明细
     *
     * @param
     */
    @PostMapping("/saveOutSubPack")
    Result saveOutSubPack(@RequestBody InfoBox<LogisticsOutHouseDetail> infoBox);

    /**
     * 根据出库对象id查询拆包明细
     *
     * @param
     */
    @GetMapping("/findOutSubpackage")
    Result findOutSubpackage(@RequestParam("outHouseId") String outHouseId);

    /**
     * 外部出库
     *
     * @param
     */
    @PostMapping("/saveOutHouseDetail")
    Result saveOutHouseDetail(@RequestBody InfoBox<LogisticsDeliveryPlan> infoBox);

    /**
     * 根据生产指令查询出库明细
     *
     * @param
     */
    @GetMapping("/findDetailByProductOrder")
    Result findDetailByProductOrder(@RequestParam("productOrder") String productOrder);

}
