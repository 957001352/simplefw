package com.fw.web.produce.service;

import com.fw.domain.Result;
import com.fw.web.produce.service.fbk.ProduceMaterialMonitorServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-17 10:11
 **/
@FeignClient(value = "fw-service/produceMaterialMonitor", fallback = ProduceMaterialMonitorServiceFbk.class)
public interface ProduceMaterialMonitorService {

    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "productName", required = false) String productName,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "stopTime", required = false) String stopTime,
                           @RequestParam(value = "timeType", required = false) Integer timeType,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    );
}
