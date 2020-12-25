package com.fw.service.mould.dao;

import com.fw.entity.device.DevicesKeepTask;
import com.fw.entity.mould.MouldKeepItem;
import com.fw.entity.mould.MouldKeepTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模具保养计划下发任务 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-27
 */
@Repository
public interface MouldKeepTaskDao {


    /**
     * 新增
     *
     * @param keepTask
     * @return
     */
    Integer insert(MouldKeepTask keepTask);

    /**
     * 修改
     *
     * @param keepTask
     * @return
     */
    Integer update(MouldKeepTask keepTask);

    /**
     * 列表查询
     *
     * @param keepOrder
     * @param mouldCode
     * @param taskStatus
     * @param auditStatus
     * @return
     */
    List<MouldKeepTask> findList(@Param("keepOrder") String keepOrder, @Param("mouldCode") String mouldCode, @Param("taskStatus") String taskStatus, @Param("auditStatus") Integer auditStatus);

    /**
     * 保养项目扩展
     *
     * @param list
     * @return
     */
    Integer batchUpdate(@Param("list") List<DevicesKeepTask> list);

    /**
     * 获取 MouldKeepTask 对象
     *
     * @param id
     * @return
     */
    MouldKeepTask selectById(@Param("id") Integer id);

    /**
     * 历史列表查询
     *
     * @param keepOrder
     * @param mouldCode
     * @param userIds
     * @param executeTime
     * @return
     */
    List<MouldKeepTask> findHistoryList(@Param("keepOrder") String keepOrder, @Param("mouldCode") String mouldCode, @Param("userIds") List<Integer> userIds, @Param("executeTime") String executeTime, @Param("taskStatus") String taskStatus);

    /**
     * 查看设备保养任务详情
     *
     * @param id
     */
    List<MouldKeepItem> getMouldTask(Integer id);

    /**
     * 查看设备保养未完成任务
     *
     * @param id
     * @return
     */
    MouldKeepTask getUnfinishedTask(Integer id);

    /**
     * 保养工单号是否存在
     *
     * @param order
     * @return
     */
    Integer isKeepOrder(String order);

    /**
     * 获取 MouldKeepTask 对象
     *
     * @param id
     * @return
     */
    MouldKeepTask getKeepTask(@Param("id") Integer id);

    /**
     * 判断计划中是否已经有任务存在
     *
     * @param ids
     * @return
     */
    Integer isExistPlan(List<String> ids);

    /**
     * 查询距现在最近的模具保养任务
     */
    MouldKeepTask findByNearNow(@Param("mouldId")Integer mouldId,
                                @Param("nowTime")String nowTime);

    /**
     * 保养工单号查询
     * @param
     * @return
     */
    String findCode(String  code);

}
