package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.logistics.LogisticsPickingOrder;
import com.fw.service.logistics.service.LogisticsPickingOrderService;
import com.fw.service.logistics.service.LogisticsPickingService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
@RestController
@RequestMapping(value = "/logisticsPickingOrder")
public class LogisticsPickingOrderController {

    @Autowired
    private LogisticsPickingOrderService logisticsPickingServiceOrder;

    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsPickingOrder logisticsPickingOrder) {
        return logisticsPickingServiceOrder.save(logisticsPickingOrder);
    }

    @GetMapping(value = "/findList")
    @RequiresAuthentication
    Result findList(@RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                    @RequestParam(value = "productOrder", required = false) String  productOrder) {
        return logisticsPickingServiceOrder.findList(outHouseId,productOrder);
    }
    /**
     * 根据入库单查询领料明细
     */
    @GetMapping(value = "/findByOutHouseId")
    @RequiresAuthentication
    public Result findByOutHouseId(@RequestParam(value = "outHouseId") Integer outHouseId) {
        return logisticsPickingServiceOrder.findByOutHouseId(outHouseId);
    }
}
