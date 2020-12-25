package com.fw.service.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTeam;

/**
 * 模具保养项目组 服务类
 *
 * @author xkliu
 * @date 2020/10/27
 */
public interface MouldKeepTeamService {

    /**
     * 新增模具保养项目组
     *
     * @param mouldKeepTeam
     * @return
     */
    Result save(MouldKeepTeam mouldKeepTeam);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 获取保养项目
     *
     * @return
     */
    Result getAllKeepItem();

    /**
     * 列表查询
     *
     * @param name
     * @param mouldCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, String mouldCode, Integer pageNum, Integer pageSize);


    /**
     * 获取所有模具编号
     *
     * @return
     */
    Result getMouldCode();

    /**
     * 查看模具保养项目组
     *
     * @param id
     * @return
     */
    Result getMouldKeepTeam(Integer id);

    /**
     * 根据保养周期获取保养项目
     *
     * @param cycle
     * @return
     */
    Result getKeepItemByCycle(Integer cycle);
}
