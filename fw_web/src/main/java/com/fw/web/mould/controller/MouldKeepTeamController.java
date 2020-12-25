package com.fw.web.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTeam;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldKeepTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xkliu
 * @des 模具保养项目组
 * @date 2020/10/27
 */
@RestController
@RequestMapping(value = "/mouldKeepTeam")
@Api(description = "模具保养项目组", value = "MouldKeepTeamController")
public class MouldKeepTeamController {

    @Autowired
    private MouldKeepTeamService mouldKeepTeamService;

    /**
     * 新增/修改模具保养项目组
     *
     * @param mouldKeepTeam
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备保养项目组")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody MouldKeepTeam mouldKeepTeam, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return mouldKeepTeamService.save(mouldKeepTeam);
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
        return mouldKeepTeamService.delete(ids);
    }


    /**
     * 获取模具保养项目
     *
     * @return result
     */
    @ApiOperation("获取模具保养项目")
    @GetMapping(value = "/getAllKeepItem")
    public Result getAllKeepItem() {
        return mouldKeepTeamService.getAllKeepItem();
    }


    /**
     * 列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepTeamService.findList(name, mouldCode, pageNum, pageSize);
    }


    /**
     * 获取所有模具编号
     *
     * @return result
     */
    @ApiOperation("获取所有模具编号")
    @GetMapping(value = "/getMouldCode")
    public Result getMouldCode() {
        return mouldKeepTeamService.getMouldCode();
    }

    /**
     * 查看模具保养项目组
     *
     * @param id
     * @return
     */
    @ApiOperation("查看模具保养项目组")
    @GetMapping("/getMouldKeepTeam")
    public Result getMouldKeepTeam(@RequestParam(value = "id") Integer id) {
        return mouldKeepTeamService.getMouldKeepTeam(id);
    }

    /**
     * 根据保养周期获取保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getKeepItemByCycle")
    @ApiOperation("根据保养周期获取保养项目")
    public Result getKeepItemByCycle(@RequestParam(value = "cycle") Integer cycle) {
        return mouldKeepTeamService.getKeepItemByCycle(cycle);
    }
}
