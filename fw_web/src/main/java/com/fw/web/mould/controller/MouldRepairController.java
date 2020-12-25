package com.fw.web.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.entity.mould.MouldRepair;
import com.fw.web.mould.service.MouldRepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-23 09:09
 **/

@RestController
@RequestMapping(value = "/mouldRepair")
@Api(description = "模具维修", value = "MouldRepairController")
public class MouldRepairController {

    @Autowired
    private MouldRepairService mouldRepairService;

    @ApiOperation("新增/更新")
    @PostMapping(value = "/save")
    public Result save(@RequestBody MouldRepair mouldRepair) {
        return mouldRepairService.save(mouldRepair);
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam("ids") String ids) {
        return mouldRepairService.delete(ids);
    }

    @ApiOperation("获取模具维修单信息")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "id", required = false) Integer id,
                           @RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "mouldId", required = false) String mouldId,
                           @RequestParam(value = "mouldName", required = false) String mouldName,
                           @RequestParam(value = "priority", required = false) String priority,
                           @RequestParam(value = "repairProjectStatus", required = false) Integer repairProjectStatus,
                           @RequestParam(value = "repairExeStatus", required = false) Integer repairExeStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldRepairService.findList(id,code, mouldId, mouldName,priority,repairProjectStatus,repairExeStatus, pageNum, pageSize);
    }

    /**
     * 维修方案制定
     *
     * @param mouldRepair
     * @return
     */
    @ApiOperation("维修方案制定")
    @PostMapping(value = "/makeProject")
    public Result makeProject(@RequestBody MouldRepair mouldRepair) {
        return mouldRepairService.makeProject(mouldRepair);
    }

    /**
     * 执行维修方案
     *
     * @param mouldRepair
     * @return
     */
    @ApiOperation("执行维修方案")
    @PostMapping(value = "/repairTaskExecute")
    public Result repairTaskExecute(@RequestBody MouldRepair mouldRepair) {
        return mouldRepairService.repairTaskExecute(mouldRepair);
    }

    @ApiOperation("任务领取")
    @PostMapping(value = "/getTask")
    Result getTask(@RequestBody MouldRepair mouldRepair){
        return  mouldRepairService.getTask(mouldRepair);
    }
}
