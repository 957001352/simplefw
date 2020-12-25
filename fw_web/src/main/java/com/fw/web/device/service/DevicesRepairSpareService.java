package com.fw.web.device.service;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepairSpare;
import com.fw.web.device.service.fbk.DevicesRepairServiceFbk;
import com.fw.web.device.service.fbk.DevicesRepairSpareServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/devicesRepairSpare", fallback = DevicesRepairSpareServiceFbk.class)
public interface DevicesRepairSpareService {

    @RequestMapping("/findSpareStoreAndUse")
    Result findSpareStoreAndUse(@RequestParam(value = "code", required = true) String code);
}
