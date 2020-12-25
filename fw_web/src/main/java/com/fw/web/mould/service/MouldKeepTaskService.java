package com.fw.web.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTask;
import com.fw.web.mould.service.fbk.MouldKeepTaskServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 模具任务下发Feign接口
 * @author xkliu
 * @date 2020/10/28
 */
@FeignClient(value = "fw-service/mouldKeepTask", fallback = MouldKeepTaskServiceFbk.class)
public interface MouldKeepTaskService {


    @GetMapping("/findList")
    Result findList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                    @RequestParam(value = "mouldCode", required = false) String mouldCode,
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
                     @RequestParam(value = "carryDay", required = false) String carryDay,
                     @RequestParam(value = "type") String type,
                     @RequestParam(value = "nonExecution") String nonExecution);


    /**
     * 历史保养列表查询
     *
     * @param keepOrder
     * @param mouldCode
     * @param executeUserName
     * @param executeTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findHistoryList")
    Result findHistoryList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
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
    @GetMapping("/getMouldTask")
    Result getMouldTask(@RequestParam(value = "id") Integer id);

    /**
     * 执行提交
     *
     * @param MouldKeepTask
     * @return
     */
    @PostMapping(value = "/explainTask")
    Result explainTask(@RequestBody MouldKeepTask MouldKeepTask);

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
    @GetMapping("/getMouldKeepTask")
    Result getMouldKeepTask(@RequestParam(value = "id") Integer id);
}

