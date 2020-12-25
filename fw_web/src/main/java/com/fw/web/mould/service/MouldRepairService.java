package com.fw.web.mould.service;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.entity.mould.MouldRepair;
import com.fw.web.mould.service.fbk.MouldRepairServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/mouldRepair", fallback = MouldRepairServiceFbk.class)
public interface MouldRepairService {


    @PostMapping(value = "/save")
    Result save(@RequestBody MouldRepair mouldRepair);

    @GetMapping(value = "/delete")
    Result delete(@RequestParam("ids") String ids);

    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "id", required = false) Integer id,
             @RequestParam(value = "code", required = false) String code,
             @RequestParam(value = "mouldId", required = false) String mouldId,
             @RequestParam(value = "mouldName", required = false) String mouldName,
             @RequestParam(value = "priority", required = false) String priority,
             @RequestParam(value = "repairProjectStatus", required = false) Integer repairProjectStatus,
             @RequestParam(value = "repairExeStatus", required = false) Integer repairExeStatus,
             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    @PostMapping(value = "/makeProject")
    Result makeProject(@RequestBody MouldRepair mouldRepair);

    @PostMapping(value = "/repairTaskExecute")
    Result repairTaskExecute(@RequestBody MouldRepair mouldRepair);

    @PostMapping(value = "/getTask")
    Result getTask(@RequestBody MouldRepair mouldRepair);


}
