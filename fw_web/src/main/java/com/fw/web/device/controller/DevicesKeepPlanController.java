package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xkliu
 * @des 设备保养计划定制
 * @date 2020/10/21
 */
@RestController
@RequestMapping(value = "/devicesKeepPlan")
@Api(description = "设备保养计划定制", value = "DevicesKeepPlanController")
public class DevicesKeepPlanController {

    @Autowired
    private DevicesKeepPlanService devicesKeepPlanService;

    /**
     * 新增/修改设备保养计划定制
     *
     * @param devicesKeepPlan
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备保养计划定制")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody DevicesKeepPlan devicesKeepPlan, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return devicesKeepPlanService.save(devicesKeepPlan);
        }
        return result;
    }


    /**
     * 查看设备保养计划定制
     *
     * @param id
     * @return
     */
    @ApiOperation("查看设备保养计划定制")
    @GetMapping("/getDevicesKeepPlan")
    public Result getDevicesKeepPlan(@RequestParam(value = "id") Integer id) {
        return devicesKeepPlanService.getDevicesKeepPlan(id);
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
        return devicesKeepPlanService.delete(ids);
    }


    /**
     * 获取保养项目组
     *
     * @return
     */
    @ApiOperation("获取保养项目组")
    @GetMapping(value = "/getAllKeepTeam")
    public Result getAllKeepTeam() {
        return devicesKeepPlanService.getAllKeepTeam();
    }


    /**
     * 列表查询
     *
     * @param name
     * @param keepTeamName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "keepTeamName", required = false) String keepTeamName,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepPlanService.findList(name, keepTeamName, devicesCode, pageNum, pageSize);
    }
}
