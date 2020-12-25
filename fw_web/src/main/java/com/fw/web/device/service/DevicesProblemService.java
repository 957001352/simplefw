package com.fw.web.device.service;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesProblem;
import com.fw.web.device.service.fbk.DevicesProblemServiceFbk;
import com.fw.web.device.service.fbk.DevicesRepairItemServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/devicesProblem", fallback = DevicesProblemServiceFbk.class)
public interface DevicesProblemService {

    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesProblem DevicesProblem);

    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "content", required = false) String content,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "type", required = false) Integer type,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
