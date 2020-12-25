package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;
import com.fw.entity.device.DevicesExtension;

/**
 * 设备履历
 * @author gchen
 * @since 2020-10-23
 */
public interface DevicesExtensionService {

    /**
     * 新增
     * @param DevicesExtension
     */
    Result save(DevicesExtension DevicesExtension);


    /**
     * 列表查询
     *
     * @param devicesCode devicesClassify status startTime endTime
     */
    Result findList(String devicesCode, String devicesClassify,Integer status, String startTime, String endTime);

}
