package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;
import com.fw.service.device.service.DevicesCheckService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设备点检
 * @author lpsong
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/devicesCheck")
public class DevicesCheckController {

    @Autowired
    private DevicesCheckService devicesCheckService;

    /**
     * 新增/修改设备保养项目
     *
     * @param devicesCheck
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("devicesCheck:save")
    public Result save(@RequestBody DevicesCheck devicesCheck) {
        return devicesCheckService.save(devicesCheck);
    }
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody DevicesCheck devicesCheck) {
        return devicesCheckService.insert(devicesCheck.getExecuteUser(),devicesCheck.getProductDevicesId());
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("devicesCheck:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesCheckService.delete(ids);
    }

    /**
     * 列表查询
     *
     * @param code
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesCheckService.findList(code, devicesClassify,devicesCode,status,pageNum, pageSize);
    }
    /**
    * 查询需要有点检任务
     * @param executeUser
     * @param productDevicesId
    * @return
    */
    @GetMapping("/findCheckTask")
    //@RequiresAuthentication
    public Result findCheckTask(@RequestParam(value = "executeUser") Integer executeUser,
                                @RequestParam(value = "productDevicesId") Integer productDevicesId) {
        return devicesCheckService.findCheckTask(executeUser, productDevicesId);
    }
}
