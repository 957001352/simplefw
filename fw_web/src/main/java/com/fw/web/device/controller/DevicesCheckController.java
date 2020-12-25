package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 设备点检
 * @author lpsong
 * @since 2020-10-21
 */
@RestController
@RequestMapping(value = "/devicesCheck")
@Api(description = "设备点检", value = "DevicesCheckController")
public class DevicesCheckController {

    @Autowired
    private DevicesCheckService devicesCheckService;

    /**
     * 新增/修改设备保养
     *
     * @param devicesCheck
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备点检")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody DevicesCheck devicesCheck, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return devicesCheckService.save(devicesCheck);
        }
        return result;
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
        return devicesCheckService.delete(ids);
    }

    /**
     * 列表查询
     * @param code
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("设备点检列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesCheckService.findList(code, devicesClassify, devicesCode,status,pageNum, pageSize);
    }
    /**
     * 查询需要有点检任务
     * @param executeUser
     * @param productDevicesId
     * @return
     */
    @ApiOperation("查询需要有点检任务")
    @GetMapping("/findCheckTask")
    public Result findCheckTask(@RequestParam(value = "executeUser") Integer executeUser,
                                @RequestParam(value = "productDevicesId") Integer productDevicesId) {
        return devicesCheckService.findCheckTask(executeUser, productDevicesId);
    }

}
