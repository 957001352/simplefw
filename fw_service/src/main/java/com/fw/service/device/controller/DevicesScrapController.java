package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesScrap;
import com.fw.service.device.service.DevicesScrapService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备报废
 * @author gchen
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/devicesScrap")
public class DevicesScrapController {

    @Resource
    private DevicesScrapService devicesScrapServiceImpl;

    /**
     * 新增/修改设备报废
     * @param devicesScrap
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("devicesScrap:save")
    public Result save(@RequestBody DevicesScrap devicesScrap) {
        return devicesScrapServiceImpl.save(devicesScrap);
    }
    /**
     * 列表查询
     * @param devicesCode devicesClassify status startTime endTime
     */
    @GetMapping("/findList")
    @RequiresAuthentication
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
    @RequiresAuthentication
    public Result selectById(@RequestParam(value = "devicesScrapId", required = false) Integer devicesScrapId) {
        return devicesScrapServiceImpl.findById(devicesScrapId);
    }

    /**
     * 根据履历id查询设备报废履历
     * @param deviceExtensionId
     */
    @GetMapping("/findByDeviceExtensionId")
    @RequiresAuthentication
    public Result findByDeviceExtensionId(@RequestParam(value = "deviceExtensionId", required = false) Integer deviceExtensionId) {
        return devicesScrapServiceImpl.findByDeviceExtensionId(deviceExtensionId);
    }

    /**
     * 报废审核通过
     */
    @GetMapping("/devicesScrapPass")
    @RequiresAuthentication
    public Result devicesScrapPass(@RequestParam(value = "id", required = false) Integer id) {
        return devicesScrapServiceImpl.devicesScrapPass(id);
    }
}
