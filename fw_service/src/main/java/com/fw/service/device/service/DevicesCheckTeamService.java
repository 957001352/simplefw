package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckTeam;
import org.apache.ibatis.annotations.Param;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
public interface DevicesCheckTeamService {

    /**
     * 新增设备点检项目组
     *
     * @param devicesCheckTeam
     * @return
     */
    Result save(DevicesCheckTeam devicesCheckTeam);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);


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
    Result findList(String name,String devicesClassify,String devicesCode,String checkItem, Integer pageNum, Integer pageSize);

    /**
    * 根据设备查询保养项目
     * @param devicesId
    * @return
    */
    Result findCheckItemListByDevicve(Integer devicesId);
}
