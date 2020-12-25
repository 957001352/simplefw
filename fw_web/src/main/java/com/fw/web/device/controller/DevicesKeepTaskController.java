package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 设备保养任务执行
 * @date 2020/10/23
 */
@RestController
@RequestMapping(value = "/devicesKeepTask")
@Api(description = "设备保养任务执行", value = "DevicesKeepTaskController")
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
    @ApiOperation("列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "taskStatus", required = false) String taskStatus,
                           @RequestParam(value = "auditStatus", required = false) Integer auditStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepTaskService.findList(keepOrder, devicesCode, taskStatus, auditStatus, pageNum, pageSize);
    }


    /**
     * 延后/强制关闭
     *
     * @param ids
     * @return result
     */
    @ApiOperation("延后/强制关闭")
    @GetMapping(value = "/postPoned")
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
    @ApiOperation("历史保养列表查询")
    @GetMapping("/findHistoryList")
    public Result findHistoryList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                                  @RequestParam(value = "devicesCode", required = false) String devicesCode,
                                  @RequestParam(value = "executeUserName", required = false) String executeUserName,
                                  @RequestParam(value = "executeTime", required = false) String executeTime,
                                  @RequestParam(value = "taskStatus", required = false) String taskStatus,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepTaskService.findHistoryList(keepOrder, devicesCode, executeUserName, executeTime, taskStatus, pageNum, pageSize);
    }

    /**
     * PAD查看未完成保养任务
     *
     * @param id
     * @return
     */
    @ApiOperation("PAD查看未完成保养任务")
    @GetMapping("/getUnfinishedTask")
    public Result getUnfinishedTask(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.getUnfinishedTask(id);
    }

    /**
     * 查看设备保养记录
     *
     * @param id
     * @return
     */
    @ApiOperation("查看设备保养记录")
    @GetMapping("/getDevicesTask")
    public Result getDevicesTask(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.getDevicesTask(id);
    }

    /**
     * PAD执行提交
     *
     * @param devicesKeepTask
     * @return result
     */
    @ApiOperation("PAD执行提交")
    @PostMapping(value = "/explainTask")
    public Result explainTask(@RequestBody DevicesKeepTask devicesKeepTask, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return devicesKeepTaskService.explainTask(devicesKeepTask);
        }
        return result;
    }

    /**
     * 查看设备保养任务执行详情
     *
     * @param id
     * @return
     */
    @ApiOperation("查看设备保养任务执行详情")
    @GetMapping("/getDevicesKeepTask")
    public Result getDevicesKeepTask(@RequestParam(value = "id") Integer id) {
        return devicesKeepTaskService.getDevicesKeepTask(id);
    }

}
