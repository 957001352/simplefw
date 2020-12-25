package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTeam;
import com.fw.service.device.service.DevicesKeepTeamService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 设备保养项目组控制器
 * @date 2020/10/21
 */
@RestController
@RequestMapping("/devicesKeepTeam")
public class DevicesKeepTeamController {


    @Autowired
    private DevicesKeepTeamService devicesKeepTeamService;

    /**
     * 新增/修改设备保养项目组
     *
     * @param devicesKeepTeam
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("devicesKeepItem:save")
    public Result save(@RequestBody DevicesKeepTeam devicesKeepTeam) {
        return devicesKeepTeamService.save(devicesKeepTeam);
    }

    /**
     * 查看设备保养项目组
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesKeepTeam")
    @RequiresAuthentication
    public Result getDevicesKeepTeam(@RequestParam(value = "id") Integer id) {
        return devicesKeepTeamService.getDevicesKeepTeam(id);
    }


    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("devicesKeepItem:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesKeepTeamService.delete(ids);
    }


    /**
     * 获取保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getAllKeepItem")
    @RequiresAuthentication
    public Result getAllKeepItem() {
        return devicesKeepTeamService.getAllKeepItem();
    }

    /**
     * 列表查询
     *
     * @param name
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepTeamService.findList(name, devicesClassify, devicesCode, pageNum, pageSize);
    }

    /**
     * 根据保养周期获取保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getKeepItemByCycle")
    @RequiresAuthentication
    public Result getKeepItemByCycle(@RequestParam(value = "cycle") Integer cycle) {
        return devicesKeepTeamService.getKeepItemByCycle(cycle);
    }
}
