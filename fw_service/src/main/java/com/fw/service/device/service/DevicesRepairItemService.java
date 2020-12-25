package com.fw.service.device.service;




import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepairItem;


public interface DevicesRepairItemService {

    /**
     * 新增
     *
     * @param devicesRepairItem
     * @return
     */
    Result save(DevicesRepairItem devicesRepairItem);

    /**
     * 获取列表
     */

    Result findList(String name, String devicesClassify, Integer pageNum, Integer pageSize);

    /**
     * 获取设备类型
     */

    Result findDecicesList();

    Result delete(String ids);
}
