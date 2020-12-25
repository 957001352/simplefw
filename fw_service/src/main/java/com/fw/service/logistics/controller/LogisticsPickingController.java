package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.service.logistics.service.LogisticsPickingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
@RestController
@RequestMapping(value = "/logisticsPicking")
public class LogisticsPickingController {

    @Autowired
    private LogisticsPickingService logisticsPickingService;

    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsPicking logisticsPicking) {
        return logisticsPickingService.save(logisticsPicking);
    }

    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsPickingService.findList(outHouseId, pageNum, pageSize);
    }
}
