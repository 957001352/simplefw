package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepairItem;
import com.fw.web.device.service.DevicesRepairItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-21 09:59
 **/

@RestController
@RequestMapping(value = "/devicesRepairItem")
@Api(description = "设备维修项目", value = "DevicesRepairItemController")
public class DevicesRepairItemController {

    @Autowired
    private DevicesRepairItemService devicesRepairItemService;

    @ApiOperation("新增/修改设备维修项目")
    @PostMapping("/save")
    public Result save(@RequestBody DevicesRepairItem devicesRepairItem){
        return devicesRepairItemService.save(devicesRepairItem);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesRepairItemService.delete(ids);
    }

    /**
     * 列表查询
     * @param name
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesRepairItemService.findList(name, devicesClassify, pageNum, pageSize);
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    @ApiOperation("获取设备类型")
    @GetMapping("/findDecicesList")
    public Result findDecicesList() {
        return devicesRepairItemService.findDecicesList();
    }
}
