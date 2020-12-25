package com.fw.web.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.web.plan.service.fbk.CustomerServiceFbk;
import com.fw.web.produce.service.fbk.ProduceDutyServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/produceDuty", fallback = ProduceDutyServiceFbk.class)
public interface ProduceDutyService {

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceDuty produceDuty);

    @GetMapping(value = "/findList")
    Result findList( @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
