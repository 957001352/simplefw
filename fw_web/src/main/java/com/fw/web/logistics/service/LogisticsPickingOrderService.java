package com.fw.web.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.logistics.LogisticsPickingOrder;
import com.fw.web.logistics.service.fbk.LogisticsPickingOrderServiceFbk;
import com.fw.web.logistics.service.fbk.LogisticsPickingServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
@FeignClient(value = "fw-service/logisticsPickingOrder", fallback = LogisticsPickingOrderServiceFbk.class)
public interface LogisticsPickingOrderService {

    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsPickingOrder logisticsPickingOrder);


    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                    @RequestParam(value = "productOrder", required = false) String  productOrder);

    /**
     * 根据入库单查询领料明细
     */
    @GetMapping(value = "/findByOutHouseId")
    Result findByOutHouseId(@RequestParam(value = "outHouseId") Integer outHouseId);
}
