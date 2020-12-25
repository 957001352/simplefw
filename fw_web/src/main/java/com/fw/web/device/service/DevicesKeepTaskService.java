package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.web.device.service.fbk.DevicesKeepTaskServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xkliu
 * @date 2020/10/23
 */
@FeignClient(value = "fw-service/devicesKeepTask", fallback = DevicesKeepTaskServiceFbk.class)
public interface DevicesKeepTaskService {


    @GetMapping("/findList")
    Result findList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                    @RequestParam(value = "devicesCode", required = false) String devicesCode,
                    @RequestParam(value = "taskStatus", required = false) String taskStatus,
                    @RequestParam(value = "auditStatus", required = false) Integer auditStatus,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 延后/关闭
     *
     * @param ids
     * @param carryDay
     * @param type
     * @param ids
     * @return
     */
    @GetMapping(value = "/postPoned")
    Result postPoned(@RequestParam(value = "ids") String ids,
                     @RequestParam(value = "carryDay",required = false) String carryDay,
                     @RequestParam(value = "type") String type,
                     @RequestParam(value = "nonExecution") String nonExecution);


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
    Result findHistoryList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                           @RequestParam(value = "devicesCode", required = false) String devicesCode,
                           @RequestParam(value = "executeUserName", required = false) String executeUserName,
                           @RequestParam(value = "executeTime", required = false) String executeTime,
                           @RequestParam(value = "taskStatus", required = false) String taskStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 查看设备保养任务详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesTask")
    Result getDevicesTask(@RequestParam(value = "id") Integer id);

    /**
     * 执行提交
     *
     * @param devicesKeepTask
     * @return
     */
    @PostMapping(value = "/explainTask")
    Result explainTask(@RequestBody DevicesKeepTask devicesKeepTask);

    /**
     * 查看未完成保养任务
     *
     * @param id
     * @return
     */
    @GetMapping("/getUnfinishedTask")
    Result getUnfinishedTask(@RequestParam(value = "id") Integer id);

    /**
     * 查看设备保养任务执行详情
     *
     * @param id
     * @return
     */
    @ApiOperation("查看设备保养任务执行详情")
    @GetMapping("/getDevicesKeepTask")
    Result getDevicesKeepTask(@RequestParam(value = "id") Integer id);
}

