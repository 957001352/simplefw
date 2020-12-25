package com.fw.service.device.dao;

import com.fw.entity.device.DevicesKeepItem;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.entity.mould.MouldKeepTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备保养计划下发任务 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Repository
public interface DevicesKeepTaskDao {


    /**
     * 新增
     *
     * @param devicesKeepTask
     * @return
     */
    Integer insert(DevicesKeepTask devicesKeepTask);

    /**
     * 修改
     *
     * @param devicesKeepTask
     * @return
     */
    Integer update(DevicesKeepTask devicesKeepTask);

    /**
     * 列表查询
     *
     * @param keepOrder
     * @param productDevicesIds
     * @param taskStatus
     * @param auditStatus
     * @return
     */
    List<DevicesKeepTask> findList(@Param("keepOrder") String keepOrder, @Param("productDevicesIds") List<String> productDevicesIds, @Param("taskStatus") String taskStatus, @Param("auditStatus") Integer auditStatus);

    /**
     * 批量修改保养项目扩展
     *
     * @param list
     * @return
     */
    Integer batchUpdate(@Param("list") List<DevicesKeepTask> list);

    /**
     * 获取 DevicesKeepTask 对象
     *
     * @param id
     * @return
     */
    DevicesKeepTask selectById(@Param("id") Integer id);

    /**
     * 历史列表查询
     *
     * @param keepOrder
     * @param productDevicesIds
     * @param userIds
     * @param executeTime
     * @return
     */
    List<DevicesKeepTask> findHistoryList(@Param("keepOrder") String keepOrder, @Param("productDevicesIds") List<String> productDevicesIds, @Param("userIds") List<Integer> userIds, @Param("executeTime") String executeTime, @Param("taskStatus") String taskStatus);

    /**
     * 查看设备保养任务详情
     *
     * @param id
     */
    List<DevicesKeepItem> getDevicesTask(Integer id);

    /**
     * 查看设备保养未完成任务
     *
     * @param id
     * @return
     */
    DevicesKeepTask getUnfinishedTask(Integer id);

    /**
     * 保养工单号是否存在
     *
     * @param order
     * @return
     */
    Integer isKeepOrder(String order);

    /**
     * 获取 DevicesKeepTask 对象
     *
     * @param id
     * @return
     */
    DevicesKeepTask getKeepTask(@Param("id") Integer id);

    /**
     * 判断计划中是否已经有任务存在
     *
     * @param ids
     * @return
     */
    Integer isExistPlan(List<String> ids);

    /**
     * 查询距现在最近的设备保养任务
     */
    DevicesKeepTask findByNearNow(@Param("productDeviceId")Integer productDeviceId,
                                  @Param("nowTime")String nowTime);

    /**
     * 保养工单号查询
     * @param
     * @return
     */
    String findCode(String  code);
}
