package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPickingOrder;
import com.fw.web.logistics.service.LogisticsPickingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
@RestController
@RequestMapping("/logisticsPickingOrder")
@Api(description = "领料单", value = "LogisticsPickingOrderController")
public class LogisticsPickingOrderController {

    @Autowired
    private LogisticsPickingOrderService logisticsPickingOrderService;


    @ApiOperation(value = "领料")
    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsPickingOrder logisticsPickingOrder) {
        return logisticsPickingOrderService.save(logisticsPickingOrder);
    }

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                    @RequestParam(value = "productOrder", required = false) String  productOrder) {
        return logisticsPickingOrderService.findList(outHouseId,productOrder);
    }


    /**
     * 根据入库单查询领料明细
     */
    @ApiOperation(value = "根据入库单查询领料明细")
    @GetMapping(value = "/findByOutHouseId")
    public Result findByOutHouseId(@RequestParam(value = "outHouseId") Integer outHouseId) {
        return logisticsPickingOrderService.findByOutHouseId(outHouseId);
    }
}
