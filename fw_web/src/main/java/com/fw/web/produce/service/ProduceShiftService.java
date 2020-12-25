package com.fw.web.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.entity.produce.ProduceShift;
import com.fw.web.produce.service.fbk.ProduceDutyServiceFbk;
import com.fw.web.produce.service.fbk.ProduceShiftServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/produceShift", fallback = ProduceShiftServiceFbk.class)
public interface ProduceShiftService {

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceShift produceShift);

    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "planMoldingId", required = false) Integer planMoldingId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
