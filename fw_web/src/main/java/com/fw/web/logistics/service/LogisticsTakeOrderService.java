package com.fw.web.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsTakeOrder;
import com.fw.web.logistics.service.fbk.LogisticsStorageHouseServiceFbk;
import com.fw.web.logistics.service.fbk.LogisticsTakeOrderServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/logisticsTakeOrder", fallback = LogisticsTakeOrderServiceFbk.class)
public interface LogisticsTakeOrderService {

    @PostMapping(value = "/update")
    Result update(@RequestBody LogisticsTakeOrder logisticsTakeOrder);

    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "taskNo",required = false) String taskNo,
                    @RequestParam(value = "status",required = false) Integer status,
                    @RequestParam(value = "kind",required = false) Integer kind,
                    @RequestParam(value = "startTime",required = false) String startTime,
                    @RequestParam(value = "stopTime",required = false) String stopTime,
                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize);
}
