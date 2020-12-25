package com.fw.service.mould.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.entity.mould.MouldKeepPlan;

/**
 * 模具保养计划制定 服务类
 *
 * @author xkliu
 * @since 2020-10-27
 */
public interface MouldKeepPlanService {

    /**
     * 新增/修改模具保养计划定制
     *
     * @param mouldKeepPlan
     * @return
     */
    Result save(MouldKeepPlan mouldKeepPlan);

    /**
     * 查看设备保养计划定制
     *
     * @param id
     * @return
     */
    Result getMouldKeepPlan(Integer id);

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
     * @param mouldCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, String keepTeamName, String mouldCode, Integer pageNum, Integer pageSize);

    /**
     * 获取开合模次数保养,生产指令完成后调用
     * @return
     */
    Integer findNonOpening();
}
