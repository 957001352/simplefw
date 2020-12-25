package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.service.device.service.DevicesKeepTaskService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 设备保养任务执行
 * @date 2020/10/23
 */
@RestController
@RequestMapping(value = "/devicesKeepTask")
public class DevicesKeepTaskController {

    @Autowired
    private DevicesKeepTaskService devicesKeepTaskService;

    /**
     * 列表查询
     *
     * @param keepOrder
     * @param devicesCode
     * @param taskStatus
     * @param auditStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "taskStatus", required = false) String taskStatus,
                           @RequestParam(value = "auditStatus", required = false) Integer auditStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepTaskService.findList(keepOrder, devicesCode, taskStatus, auditStatus, pageNum, pageSize);
    }

    /**
     * 延后/关闭
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/postPoned")
    @RequiresPermissions("devicesKeepTask:postPoned")
    public Result postPoned(@RequestParam(value = "ids") String ids,
                            @RequestParam(value = "carryDay", required = false) String carryDay,
                            @RequestParam(value = "type") String type,
                            @RequestParam(value = "nonExecution") String nonExecution) {
        return devicesKeepTaskService.postPoned(ids, carryDay, type, nonExecution);
    }

    /**
     * 历史保养列表查询
     *
     * @param keepOrder
     * @param devicesCode
     * @param executeUserName
     * @param executeTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findHistoryList")
    @RequiresAuthentication
    public Result findHistoryList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                                  @RequestParam(value = "devicesCode", required = false) String devicesCode,
                                  @RequestParam(value = "executeUserName", required = false) String executeUserName,
                                  @RequestParam(value = "executeTime", required = false) String executeTime,
                                  @RequestParam(value = "taskStatus", required = false) String taskStatus,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepTaskService.findHistoryList(keepOrder, devicesCode, executeUserName, executeTime, taskStatus,pageNum, pageSize);
    }


    /**
     * 查看设备保养记录详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesTask")
    @RequiresAuthentication
    public Result getDevicesTask(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.getDevicesTask(id);
    }


    /**
     * 查看未完成保养任务
     *
     * @param id
     * @return
     */
    @GetMapping("/getUnfinishedTask")
    @RequiresAuthentication
    public Result getUnfinishedTask(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.getUnfinishedTask(id);
    }


    /**
     * 执行提交
     *
     * @param devicesKeepTask
     * @return result
     */
    @PostMapping(value = "/explainTask")
    @RequiresAuthentication
    public Result explainTask(@RequestBody DevicesKeepTask devicesKeepTask) {
        return devicesKeepTaskService.explainTask(devicesKeepTask);
    }

    /**
     * 查看设备保养任务执行详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesKeepTask")
    @RequiresAuthentication
    public Result getDevicesKeepTask(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.getDevicesKeepTask(id);
    }

    /**
     * 延期审批通过
     */
    @GetMapping("/postPonedPass")
    public Result postPonedPass(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.postPonedPass(id);
    }

    /**
     * 强制关闭审批通过
     */
    @GetMapping("/closedPass")
    public Result closedPass(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.closedPass(id);
    }
}
