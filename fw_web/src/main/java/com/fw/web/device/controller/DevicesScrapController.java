package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesScrap;
import com.fw.web.device.service.DevicesScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备报废
 * @author gchen
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/devicesScrap")
@Api(description = "设备报废记录", value = "DevicesScrapController")
public class DevicesScrapController {

    @Resource
    private DevicesScrapService devicesScrapServiceImpl;

    /**
     * 新增/修改
     * @param devicesScrap
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增/修改")
    public Result save(@RequestBody DevicesScrap devicesScrap) {
        return devicesScrapServiceImpl.save(devicesScrap);
    }
    /**
     * 列表查询
     * @param devicesCode devicesClassify status startTime endTime
     */
    @GetMapping("/findList")
    @ApiOperation(value = "列表查询")
    public Result findList(@RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "devicesScrapId", required = false) Integer devicesScrapId,
                           @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize) {
        return devicesScrapServiceImpl.findList(devicesCode, devicesClassify,devicesScrapId,pageNum,pageSize);
    }

    /**
     * 根据id查询报废设备详情
     * @param devicesScrapId
     */
    @GetMapping("/selectById")
    public Result selectById(@RequestParam(value = "devicesScrapId", required = false) Integer devicesScrapId) {
        return devicesScrapServiceImpl.selectById(devicesScrapId);
    }

    /**
     * 根据履历id查询设备报废履历
     * @param deviceExtensionId
     */
    @GetMapping("/findByDeviceExtensionId")
    @ApiOperation(value = "根据履历id查询设备报废履历")
    public Result findByDeviceExtensionId(@RequestParam(value = "deviceExtensionId", required = false) Integer deviceExtensionId) {
        return devicesScrapServiceImpl.findByDeviceExtensionId(deviceExtensionId);
    }
}
