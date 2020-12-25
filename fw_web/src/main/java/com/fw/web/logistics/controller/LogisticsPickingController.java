package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.web.logistics.service.LogisticsPickingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
@RestController
@RequestMapping("/logisticsPicking")
@Api(description = "领料", value = "LogisticsPickingController")
public class LogisticsPickingController {

    @Autowired
    private LogisticsPickingService logisticsPickingService;


    @ApiOperation(value = "领料")
    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsPicking logisticsPicking) {
        return logisticsPickingService.save(logisticsPicking);
    }

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsPickingService.findList(outHouseId, pageNum, pageSize);
    }
}
