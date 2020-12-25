package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesExtension;
import com.fw.service.device.service.DevicesExtensionService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备履历
 * @author gchen
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/devicesExtension")
public class DevicesExtensionController {

    @Resource
    private DevicesExtensionService devicesExtensionServiceImpl;

    /**
     * 新增/修改
     * @param devicesExtension
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("devicesExtension:save")
    public Result save(@RequestBody DevicesExtension devicesExtension) {
        return devicesExtensionServiceImpl.save(devicesExtension);
    }
    /**
     * 列表查询
     * @param devicesCode devicesClassify status startTime endTime
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime) {
        return devicesExtensionServiceImpl.findList(devicesCode, devicesClassify,status, startTime, endTime);
    }
}
