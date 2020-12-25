package com.fw.web.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBake;
import com.fw.entity.produce.ProduceDuty;
import com.fw.web.produce.service.fbk.ProduceBakeServiceFbk;
import com.fw.web.produce.service.fbk.ProduceDutyServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/produceBake", fallback = ProduceBakeServiceFbk.class)
public interface ProduceBakeService {

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceBake produceBake);

    @GetMapping(value = "/findList")
    Result findList( @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
