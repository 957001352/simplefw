package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTask;

/**
 * 设备保养计划下发任务 服务类
 *
 * @author xkliu
 * @since 2020-10-19
 */
public interface DevicesKeepTaskService {

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
    Result findList(String keepOrder, String devicesCode, String taskStatus, Integer auditStatus, Integer pageNum, Integer pageSize);

    /**
     * 延后/关闭
     *
     * @param ids
     * @return
     */
    Result postPoned(String ids, String carryDay, String type, String nonExecution);

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
    Result findHistoryList(String keepOrder, String devicesCode, String executeUserName, String executeTime, String taskStatus, Integer pageNum, Integer pageSize);

    /**
     * 查看设备保养记录详情
     *
     * @param id
     * @return
     */
    Result getDevicesTask(Integer id);

    /**
     * 执行提交
     *
     * @param devicesKeepTask
     * @return
     */
    Result explainTask(DevicesKeepTask devicesKeepTask);

    /**
     * 查看未完成保养任务
     *
     * @param id
     * @return
     */
    Result getUnfinishedTask(Integer id);

    /**
     * 查看设备保养任务执行详情
     *
     * @param id
     * @return
     */
    Result getDevicesKeepTask(Integer id);

    /**
     * 延期审批通过
     *
     * @param id
     * @return
     */
    Result postPonedPass(Integer id);

    /**
     * 强制关闭审批通过
     *
     * @param id
     * @return
     */
    Result closedPass(Integer id);
}
