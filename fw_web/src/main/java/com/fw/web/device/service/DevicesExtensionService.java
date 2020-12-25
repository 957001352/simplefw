package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesExtension;
import com.fw.web.device.service.fbk.DevicesCheckTeamServiceFbk;
import com.fw.web.device.service.fbk.DevicesExtensionServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 设备履历
 * @author gchen
 * @since 2020-10-23
 */
@FeignClient(value = "fw-service/devicesExtension", fallback = DevicesExtensionServiceFbk.class)
public interface DevicesExtensionService {

    /**
     * 新增/修改
     * @param devicesExtension
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesExtension devicesExtension);


    /**
     * 列表查询
     * @param devicesCode devicesClassify status startTime endTime
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime);

}
