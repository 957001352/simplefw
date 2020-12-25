package com.fw.web.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.entity.mould.MouldKeepPlan;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepPlanService;
import com.fw.web.mould.service.MouldKeepPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xkliu
 * @des 模具保养计划定制
 * @date 2020/10/27
 */
@RestController
@RequestMapping(value = "/mouldKeepPlan")
@Api(description = "模具保养计划定制", value = "MouldKeepPlanController")
public class MouldKeepPlanController {

    @Autowired
    private MouldKeepPlanService mouldKeepPlanService;

    /**
     * 新增/修改设备保养计划定制
     *
     * @param MouldKeepPlan
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备保养计划定制")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody MouldKeepPlan MouldKeepPlan, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return mouldKeepPlanService.save(MouldKeepPlan);
        }
        return result;
    }


    /**
     * 查看模具保养计划定制
     *
     * @param id
     * @return
     */
    @ApiOperation("查看模具保养计划定制")
    @GetMapping("/getMouldKeepPlan")
    public Result getMouldKeepPlan(@RequestParam(value = "id") Integer id) {
        return mouldKeepPlanService.getMouldKeepPlan(id);
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
        return mouldKeepPlanService.delete(ids);
    }


    /**
     * 获取模具保养表单
     *
     * @return
     */
    @ApiOperation("获取模具保养表单")
    @GetMapping(value = "/getAllKeepTeam")
    public Result getAllKeepTeam() {
        return mouldKeepPlanService.getAllKeepTeam();
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
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepPlanService.findList(name, keepTeamName, mouldCode, pageNum, pageSize);
    }
}
