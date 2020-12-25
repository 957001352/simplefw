package com.fw.web.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.web.logistics.service.fbk.LogisticsPickingServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
@FeignClient(value = "fw-service/logisticsPicking", fallback = LogisticsPickingServiceFbk.class)
public interface LogisticsPickingService {

    @PostMapping(value = "/save")
    Result save(@RequestBody LogisticsPicking logisticsPicking);


    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "outHouseId", required = false) Integer outHouseId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
