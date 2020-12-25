package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.web.device.service.DevicesRepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:设备维修单
 * @author: wqiang
 * @create: 2020-10-22 10:06
 **/

@RestController
@RequestMapping(value = "/devicesRepai")
@Api(description = "设备维修", value = "DevicesRepairController")
public class DevicesRepairController {

    @Autowired
    private DevicesRepairService devicesRepairService;

    @ApiOperation("新增/修改设备维修")
    @PostMapping(value = "/save")
    public Result save(@RequestBody DevicesRepair devicesRepair) {
        return devicesRepairService.save(devicesRepair);
    }

    @ApiOperation("删除设备维修")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam("ids") String ids) {
        return devicesRepairService.delete(ids);
    }

    @ApiOperation("获取设备维修列表")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "id", required = false) Integer id,
                           @RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "productDevicesId", required = false) String productDevicesId,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "priority", required = false) String priority,
                           @RequestParam(value = "repairProjectStatus", required = false) Integer repairProjectStatus,
                           @RequestParam(value = "repairExeStatus", required = false) Integer repairExeStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesRepairService.findList(id,code, productDevicesId, devicesClassify,priority, repairProjectStatus,repairExeStatus,pageNum, pageSize);
    }

    /**
     * 维修方案制定
     * @param devicesRepair
     * @return
     */
    @ApiOperation("维修方案制定")
    @PostMapping(value = "/makeProject")
    public Result makeProject(@RequestBody DevicesRepair devicesRepair) {
        return devicesRepairService.makeProject(devicesRepair);
    }

    @ApiOperation("任务领取")
    @PostMapping(value = "/getTask")
    Result getTask(@RequestBody DevicesRepair devicesRepair){
        return  devicesRepairService.getTask(devicesRepair);
    }

    /**
     * 执行维修方案
     * @param devicesRepair
     * @return
     */
    @ApiOperation("执行维修方案")
    @PostMapping(value = "/repairTaskExecute")
    public Result repairTaskExecute(@RequestBody DevicesRepair devicesRepair) {
        return devicesRepairService.repairTaskExecute(devicesRepair);
    }
}
