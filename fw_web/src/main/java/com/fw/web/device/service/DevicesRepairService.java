package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.web.device.service.fbk.DevicesRepairServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/devicesRepair", fallback = DevicesRepairServiceFbk.class)
public interface DevicesRepairService {

    /**
     * 新增
     * @param devicesRepair
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesRepair devicesRepair);

    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    /**
     * 查询
     * @param code  维修单编码
     * @param productDevicesId  设备编码
     * @param devicesClassify   设备类型
     * @return
     */
    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "productDevicesId", required = false) String productDevicesId,
            @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value = "repairProjectStatus", required = false) Integer repairProjectStatus,
            @RequestParam(value = "repairExeStatus", required = false) Integer repairExeStatus,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 执行维修任务
     * @param devicesRepair
     * @return
     */
    @PostMapping(value = "/makeProject")
    Result makeProject(@RequestBody DevicesRepair devicesRepair);


    @PostMapping(value = "/getTask")
    Result getTask(@RequestBody DevicesRepair devicesRepair);
    /**
     * 制作方案
     * @param devicesRepair
     * @return
     */
    @PostMapping(value = "/repairTaskExecute")
    Result repairTaskExecute(@RequestBody DevicesRepair devicesRepair);
}
