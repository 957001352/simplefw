package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.service.device.service.DevicesRepairService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description: 设备维修控制层
 * @author: wqiang
 * @create: 2020-10-22 09:39
 **/
@RestController
@RequestMapping("/devicesRepair")
public class DevicesRepairController {

    @Autowired
    private DevicesRepairService devicesRepairService;

    @PostMapping(value = "/save")
    @RequiresPermissions(value = "devicesRepair:save")
    public Result save(@RequestBody DevicesRepair devicesRepair) {
        return devicesRepairService.save(devicesRepair);
    }

    @GetMapping(value = "/delete")
    @RequiresPermissions(value = "devicesRepair:delete")
    public Result delete(@RequestParam("ids") String ids) {
        return devicesRepairService.delete(ids);
    }

    @GetMapping(value = "/findList")
    @RequiresPermissions(value = "devicesRepair:findList")
    public Result findList(
                           @RequestParam(value = "id", required = false) Integer id,
                           @RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "productDevicesId", required = false) String productDevicesId,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "priority", required = false) String priority,
                           @RequestParam(value = "repairProjectStatus", required = false) Integer repairProjectStatus,
                           @RequestParam(value = "repairExeStatus", required = false) Integer repairExeStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesRepairService.findList(id,code, productDevicesId, devicesClassify,priority,repairProjectStatus,repairExeStatus,pageNum, pageSize);
    }

    /**
     * 维修方案制定
     * @param devicesRepair
     * @return
     */
    @PostMapping(value = "/makeProject")
    @RequiresPermissions(value = "devicesRepair:makeProject")
    public Result makeProject(@RequestBody DevicesRepair devicesRepair) {
        return devicesRepairService.makeProject(devicesRepair);
    }

    @PostMapping(value = "/getTask")
    @RequiresPermissions(value = "devicesRepair:getTask")
    public Result getTask(@RequestBody DevicesRepair devicesRepair){
        return devicesRepairService.getTask(devicesRepair);
    }
    /**
     * 执行维修方案
     * @param devicesRepair
     * @return
     */
    @PostMapping(value = "/repairTaskExecute")
    @RequiresPermissions(value = "devicesRepair:repairTaskExecute")
    public Result repairTaskExecute(@RequestBody DevicesRepair devicesRepair) {
        return devicesRepairService.repairTaskExecute(devicesRepair);
    }


}

