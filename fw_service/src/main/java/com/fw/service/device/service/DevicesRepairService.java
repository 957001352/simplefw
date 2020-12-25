package com.fw.service.device.service;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;

import java.util.List;

public interface DevicesRepairService {

    /**
     * 新增
     * @param devicesRepair
     * @return
     */
    Result save(DevicesRepair devicesRepair);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 查询
     * @param code 维修单ID
     * @param code  维修单编码
     * @param productDevicesId  设备编码
     * @param devicesClassify   设备类型
     * @return
     */
    Result findList(Integer id,String code, String productDevicesId, String devicesClassify,String priority, Integer repairProjectStatus, Integer repairExeStatus,Integer pageNum, Integer pageSize);


    /**
     * 执行维修任务
     * @param devicesRepair
     * @return
     */
    Result repairTaskExecute(DevicesRepair devicesRepair);

    /**
     * 制作方案
     * @param devicesRepair
     * @return
     */
    Result makeProject(DevicesRepair devicesRepair);


    /**
     * 任务领取
     * @param devicesRepair
     * @return
     */
    Result getTask(DevicesRepair devicesRepair);

}
