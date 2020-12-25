package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckTeam;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesCheckTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-22
 */
@RestController
@RequestMapping(value = "/devicesCheckTeam")
@Api(description = "设备点检项目组", value = "DevicesCheckTeamController")
public class DevicesCheckTeamController {

    @Autowired
    private DevicesCheckTeamService devicesCheckTeamService;

    /**
     * 新增/修改设备点检项目
     *
     * @param devicesCheckTeam
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备点检项目")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody DevicesCheckTeam devicesCheckTeam, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return devicesCheckTeamService.save(devicesCheckTeam);
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
        return devicesCheckTeamService.delete(ids);
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
                           @RequestParam(value = "checkItem", required = false) String checkItem,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesCheckTeamService.findList(name, devicesClassify,devicesCode,checkItem, pageNum, pageSize);
    }
    /**
     * 根据设备查询保养项目
     * @param devicesId
     * @return
     */
    @GetMapping("/findCheckItemListByDevicve")
    @ApiOperation("根据设备查询保养项目")
    public Result findCheckItemListByDevicve(@RequestParam(value = "devicesId") Integer devicesId) {
        return devicesCheckTeamService.findCheckItemListByDevicve(devicesId);
    }
}
