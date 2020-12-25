package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.entity.mould.MouldRepair;
import com.fw.service.device.service.DevicesRepairService;
import com.fw.service.mould.service.MouldRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description: 模具维修控制层
 * @author: wqiang
 * @create: 2020-10-22 09:39
 **/
@RestController
@RequestMapping("/mouldRepair")
public class MouldRepairController {

    @Autowired
    private MouldRepairService mouldRepairService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody MouldRepair mouldRepair) {
        return mouldRepairService.save(mouldRepair);
    }

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam("ids") String ids) {
        return mouldRepairService.delete(ids);
    }

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
        return mouldRepairService.findList(id,code, mouldId,mouldName, priority,repairProjectStatus,repairExeStatus,pageNum, pageSize);
    }

    /**
     * 维修方案制定
     * @param mouldRepair
     * @return
     */
    @PostMapping(value = "/makeProject")
    public Result makeProject(@RequestBody MouldRepair mouldRepair) {
        return mouldRepairService.makeProject(mouldRepair);
    }

    /**
     * 执行维修方案
     * @param mouldRepair
     * @return
     */
    @PostMapping(value = "/repairTaskExecute")
    public Result repairTaskExecute(@RequestBody MouldRepair mouldRepair) {
        return mouldRepairService.repairTaskExecute(mouldRepair);
    }

    @PostMapping(value = "/getTask")
    public Result getTask(@RequestBody MouldRepair mouldRepair){
        return mouldRepairService.getTask(mouldRepair);
    }

    @GetMapping(value = "/auditPass")
    public Result auditPass(@RequestParam(value = "id") Integer id){
        return mouldRepairService.auditPass(id);
    }
}

