package com.fw.service.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTask;

/**
 * 模具保养任务下发 服务类
 *
 * @author xkliu
 * @date 2020/10/28
 */
public interface MouldKeepTaskService {

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
    Result findList(String keepOrder, String mouldCode, String taskStatus, Integer auditStatus, Integer pageNum, Integer pageSize);

    /**
     * 延后/强制关闭
     *
     * @param ids
     * @param carryDay
     * @param type
     * @param nonExecution
     * @return
     */
    Result postPoned(String ids, String carryDay, String type, String nonExecution);

    /**
     * 历史保养列表查询
     *
     * @param keepOrder
     * @param mouldCode
     * @param executeUserName
     * @param executeTime
     * @param taskStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findHistoryList(String keepOrder, String mouldCode, String executeUserName, String executeTime, String taskStatus, Integer pageNum, Integer pageSize);

    /**
     * PAD查看未完成保养任务
     *
     * @param id
     * @return
     */
    Result getUnfinishedTask(Integer id);

    /**
     * 查看模具保养记录
     *
     * @param id
     * @return
     */
    Result getMouldTask(Integer id);

    /**
     * PAD执行提交
     *
     * @param mouldKeepTask
     * @return
     */
    Result explainTask(MouldKeepTask mouldKeepTask);

    /**
     * 查看设备保养任务执行详情
     *
     * @param id
     * @return
     */
    Result getMouldKeepTask(Integer id);

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

    /**
     * 下发产前保养任务,keepTpye为2是产前保养
     *
     * @param keepTpye
     * @return
     */
    Integer issuedTask(Integer keepTpye,Integer mouldId,Integer injectionMoldingId);
}
