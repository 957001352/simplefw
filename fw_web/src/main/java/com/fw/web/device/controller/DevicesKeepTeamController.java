package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTeam;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xkliu
 * @des 设备保养项目组
 * @date 2020/10/21
 */
@RestController
@RequestMapping(value = "/devicesKeepTeam")
@Api(description = "设备保养项目组", value = "DevicesKeepTeamController")
public class DevicesKeepTeamController {

    @Autowired
    private DevicesKeepTeamService devicesKeepTeamService;

    /**
     * 新增/修改设备保养项目组
     *
     * @param devicesKeepTeam
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备保养项目组")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody DevicesKeepTeam devicesKeepTeam, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return devicesKeepTeamService.save(devicesKeepTeam);
        }
        return result;
    }

    /**
     * 查看设备保养项目组
     *
     * @param id
     * @return
     */
    @ApiOperation("查看设备保养项目组")
    @GetMapping("/getDevicesKeepTeam")
    public Result getDevicesKeepTeam(@RequestParam(value = "id") Integer id) {
        return devicesKeepTeamService.getDevicesKeepTeam(id);
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
        return devicesKeepTeamService.delete(ids);
    }


    /**
     * 获取保养项目
     *
     * @return result
     */
    @ApiOperation("获取保养项目")
    @GetMapping(value = "/getAllKeepItem")
    public Result getAllKeepItem() {
        return devicesKeepTeamService.getAllKeepItem();
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
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepTeamService.findList(name, devicesClassify,devicesCode, pageNum, pageSize);
    }

    /**
     * 根据保养周期获取保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getKeepItemByCycle")
    @ApiOperation("根据保养周期获取保养项目")
    public Result getKeepItemByCycle(@RequestParam(value = "cycle") Integer cycle) {
        return devicesKeepTeamService.getKeepItemByCycle(cycle);
    }
}
