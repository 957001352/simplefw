package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.service.device.service.DevicesKeepPlanService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 设备保养计划定制控制器
 * @date 2020/10/21
 */
@RestController
@RequestMapping(value = "/devicesKeepPlan")
public class DevicesKeepPlanController {

    @Autowired
    private DevicesKeepPlanService devicesKeepPlanService;

    /**
     * 新增/修改设备保养计划定制
     *
     * @param devicesKeepPlan
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("devicesKeepPlan:save")
    public Result save(@RequestBody DevicesKeepPlan devicesKeepPlan) {
        return devicesKeepPlanService.save(devicesKeepPlan);
    }

    /**
     * 查看设备保养计划定制
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesKeepPlan")
    @RequiresAuthentication
    public Result getDevicesKeepPlan(@RequestParam(value = "id") Integer id) {
        return devicesKeepPlanService.getDevicesKeepPlan(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("devicesKeepPlan:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesKeepPlanService.delete(ids);
    }


    /**
     * 获取保养项目组
     *
     * @return
     */
    @GetMapping(value = "/getAllKeepTeam")
    @RequiresAuthentication
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
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "keepTeamName", required = false) String keepTeamName,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepPlanService.findList(name, keepTeamName, devicesCode, pageNum, pageSize);
    }
}
