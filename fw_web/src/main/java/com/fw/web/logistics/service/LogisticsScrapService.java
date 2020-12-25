package com.fw.web.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsScrap;
import com.fw.web.logistics.service.fbk.LogisticsBackHouseServiceFbk;
import com.fw.web.logistics.service.fbk.LogisticsScrapServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/logisticsScrap", fallback = LogisticsScrapServiceFbk.class)
public interface LogisticsScrapService {

    @PostMapping(value = "save")
    public Result save(@RequestBody LogisticsScrap logisticsScrap);

    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "productCode",required = false) String productCode,
                           @RequestParam(value = "productName",required = false) String productName,
                           @RequestParam(value = "productOrder",required = false) String productOrder,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize

    );

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids);
}
