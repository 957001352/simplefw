package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckTeam;
import com.fw.service.device.service.DevicesCheckTeamService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/devicesCheckTeam")
public class DevicesCheckTeamController {


    @Autowired
    private DevicesCheckTeamService devicesCheckTeamService;

    /**
     * 新增/修改设备保养项目组
     *
     * @param devicesCheckTeam
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("devicesCheckTeam:save")
    public Result save(@RequestBody DevicesCheckTeam devicesCheckTeam) {
        return devicesCheckTeamService.save(devicesCheckTeam);
    }



    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("devicesCheckTeam:delete")
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
    @GetMapping("/findList")
    @RequiresAuthentication
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
    @RequiresAuthentication
    public Result findCheckItemListByDevicve(@RequestParam(value = "devicesId") Integer devicesId) {
        return devicesCheckTeamService.findCheckItemListByDevicve(devicesId);
    }
}
