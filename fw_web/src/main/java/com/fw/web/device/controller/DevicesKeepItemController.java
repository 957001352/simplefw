package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xkliu
 * @des 设备保养项目
 * @date 2020/10/20
 */
@RestController
@RequestMapping(value = "/devicesKeepItem")
@Api(description = "设备保养项目", value = "DevicesKeepItemController")
public class DevicesKeepItemController {

    @Autowired
    private DevicesKeepItemService devicesKeepItemService;

    /**
     * 新增/修改设备保养项目
     *
     * @param devicesKeepItem
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备保养项目")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody DevicesKeepItem devicesKeepItem, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return devicesKeepItemService.save(devicesKeepItem);
        }
        return result;
    }


    /**
     * 查看设备保养项目
     *
     * @param id
     * @return
     */
    @ApiOperation("查看设备保养项目")
    @GetMapping("/getDevicesKeepItem")
    public Result getDevicesKeepItem(@RequestParam(value = "id") Integer id) {
        return devicesKeepItemService.getDevicesKeepItem(id);
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
        return devicesKeepItemService.delete(ids);
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
        return devicesKeepItemService.findList(name, devicesClassify, pageNum, pageSize);
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    @ApiOperation("获取设备类型")
    @GetMapping("/getDevicesClassify")
    public Result getDevicesClassify() {
        return devicesKeepItemService.getDevicesClassify();
    }

}
