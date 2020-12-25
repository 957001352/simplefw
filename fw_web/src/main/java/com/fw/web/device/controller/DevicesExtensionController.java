package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesExtension;
import com.fw.web.device.service.DevicesExtensionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备履历
 * @author gchen
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/devicesExtension")
@Api(description = "设备履历", value = "DevicesExtensionController")
public class DevicesExtensionController {

    @Resource
    private DevicesExtensionService devicesExtensionServiceImpl;

    /**
     * 新增/修改
     * @param devicesExtension
     */
    @PostMapping(value = "/save")
    @ApiOperation("新增/修改")
    public Result save(@RequestBody DevicesExtension devicesExtension) {
        return devicesExtensionServiceImpl.save(devicesExtension);
    }
    /**
     * 列表查询
     * @param devicesCode devicesClassify status startTime endTime
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime) {
        return devicesExtensionServiceImpl.findList(devicesCode, devicesClassify,status, startTime, endTime);
    }
}
