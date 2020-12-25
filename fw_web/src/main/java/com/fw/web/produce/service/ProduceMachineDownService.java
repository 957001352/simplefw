package com.fw.web.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMachineDown;
import com.fw.web.produce.service.fbk.ProduceMachineDownServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/produceMachineDown", fallback =  ProduceMachineDownServiceFbk.class)
public interface ProduceMachineDownService {

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceMachineDown produceMachineDown);

    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "ofNo", required = false) String ofNo,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
