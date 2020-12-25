package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;

/**
 * 设备点检
 * @author lpsong
 * @since 2020-10-21
 */
public interface DevicesCheckService {

    /**
     * 执行点检任务
     *
     * @param devicesCheck
     * @return
     */
    Result save(DevicesCheck devicesCheck);


    /**
    *  新增点检任务
     * @param executeUser
     * @param productDevicesId
    * @return
    */
    Result insert(Integer executeUser,Integer productDevicesId);
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
     * @param code
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String code, String devicesClassify, String devicesCode, Integer status,Integer pageNum, Integer pageSize);

    /**
    * 查询需要点检任务
     * @param executeUser
     * @param productDevicesId
    * @return
    */
    Result findCheckTask(Integer executeUser,Integer productDevicesId);
}
