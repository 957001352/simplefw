package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTask;
import com.fw.service.mould.service.MouldKeepTaskService;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 模具保养任务执行
 * @date 2020/10/28
 */
@RestController
@RequestMapping(value = "/mouldKeepTask")
public class MouldKeepTaskController {

    @Autowired
    private MouldKeepTaskService mouldKeepTaskService;

    /**
     * 列表查询
     *
     * @param keepOrder
     * @param mouldCode
     * @param taskStatus
     * @param auditStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
                           @RequestParam(value = "taskStatus", required = false) String taskStatus,
                           @RequestParam(value = "auditStatus", required = false) Integer auditStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepTaskService.findList(keepOrder, mouldCode, taskStatus, auditStatus, pageNum, pageSize);
    }


    /**
     * 延后/强制关闭
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/postPoned")
    public Result postPoned(@RequestParam(value = "ids") String ids,
                            @RequestParam(value = "carryDay", required = false) String carryDay,
                            @RequestParam(value = "type") String type,
                            @RequestParam(value = "nonExecution") String nonExecution) {
        return mouldKeepTaskService.postPoned(ids, carryDay, type, nonExecution);
    }

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
    public Result findHistoryList(@RequestParam(value = "keepOrder", required = false) String keepOrder,
                                  @RequestParam(value = "mouldCode", required = false) String mouldCode,
                                  @RequestParam(value = "executeUserName", required = false) String executeUserName,
                                  @RequestParam(value = "executeTime", required = false) String executeTime,
                                  @RequestParam(value = "taskStatus", required = false) String taskStatus,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepTaskService.findHistoryList(keepOrder, mouldCode, executeUserName, executeTime, taskStatus, pageNum, pageSize);
    }

    /**
     * PAD查看未完成保养任务
     *
     * @param id
     * @return
     */
    @GetMapping("/getUnfinishedTask")
    public Result getUnfinishedTask(@RequestParam(value = "id") Integer id) {
        return mouldKeepTaskService.getUnfinishedTask(id);
    }

    /**
     * 查看模具保养记录
     *
     * @param id
     * @return
     */
    @GetMapping("/getMouldTask")
    public Result getDevicesTask(@RequestParam(value = "id") Integer id) {
        return mouldKeepTaskService.getMouldTask(id);
    }

    /**
     * PAD执行提交
     *
     * @param MouldKeepTask
     * @return result
     */
    @PostMapping(value = "/explainTask")
    public Result explainTask(@RequestBody MouldKeepTask MouldKeepTask, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return mouldKeepTaskService.explainTask(MouldKeepTask);
        }
        return result;
    }

    /**
     * 查看设备保养任务执行详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getMouldKeepTask")
    public Result getMouldKeepTask(@RequestParam(value = "id") Integer id) {
        return mouldKeepTaskService.getMouldKeepTask(id);
    }

    /**
     * 延期审批通过
     */
    @GetMapping("/postPonedPass")
    public Result postPonedPass(@RequestParam(value = "id") Integer id) {
        return mouldKeepTaskService.postPonedPass(id);
    }

    /**
     * 强制关闭审批通过
     */
    @GetMapping("/closedPass")
    public Result closedPass(@RequestParam(value = "id") Integer id) {
        return mouldKeepTaskService.closedPass(id);
    }


}
