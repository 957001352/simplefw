package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTeam;

/**
 * 保养项目组 服务类
 *
 * @author xkliu
 * @since 2020-10-19
 */
public interface DevicesKeepTeamService {

    /**
     * 新增设备保养项目组
     *
     * @param devicesKeepTeam
     * @return
     */
    Result save(DevicesKeepTeam devicesKeepTeam);

    /**
     * 查看设备保养项目组
     *
     * @param id
     * @return
     */
    Result getDevicesKeepTeam(Integer id);

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
     * @param devicesClassify
     * @param devicesCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, String devicesClassify, String devicesCode, Integer pageNum, Integer pageSize);

    /**
     * 根据保养周期获取保养项目
     *
     * @param cycle
     * @return
     */
    Result getKeepItemByCycle(Integer cycle);
}
