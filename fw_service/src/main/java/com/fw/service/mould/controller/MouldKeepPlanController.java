package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.entity.mould.MouldKeepPlan;
import com.fw.service.device.service.DevicesKeepPlanService;
import com.fw.service.mould.service.MouldKeepPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 设备保养计划定制控制器
 * @date 2020/10/21
 */
@RestController
@RequestMapping(value = "/mouldKeepPlan")
public class MouldKeepPlanController {

    @Autowired
    private MouldKeepPlanService mouldKeepPlanService;

    /**
     * 新增/修改设备保养计划定制
     *
     * @param mouldKeepPlan
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody MouldKeepPlan mouldKeepPlan) {
        return mouldKeepPlanService.save(mouldKeepPlan);
    }

    /**
     * 查看模具保养计划定制
     *
     * @param id
     * @return
     */
    @GetMapping("/getMouldKeepPlan")
    public Result getDevicesKeepPlan(@RequestParam(value = "id") Integer id) {
        return mouldKeepPlanService.getMouldKeepPlan(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return mouldKeepPlanService.delete(ids);
    }


    /**
     * 获取模具保养表单
     *
     * @return
     */
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
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "keepTeamName", required = false) String keepTeamName,
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepPlanService.findList(name, keepTeamName, mouldCode, pageNum, pageSize);
    }
}
