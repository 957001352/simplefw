package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;

/**
 * 设备保养计划制定 服务类
 *
 * @author xkliu
 * @since 2020-10-19
 */
public interface DevicesKeepPlanService {

    /**
     * 新增/修改设备保养计划定制
     *
     * @param devicesKeepPlan
     * @return
     */
    Result save(DevicesKeepPlan devicesKeepPlan);

    /**
     * 查看设备保养计划定制
     *
     * @param id
     * @return
     */
    Result getDevicesKeepPlan(Integer id);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 获取保养项目组
     *
     * @return
     */
    Result getAllKeepTeam();

    /**
     * 列表查询
     *
     * @param name
     * @param keepTeamName
     * @param devicesCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, String keepTeamName, String devicesCode, Integer pageNum, Integer pageSize);
}
