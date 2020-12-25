package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesExtension;
import com.fw.entity.device.DevicesScrap;

/**
 * 设备报废
 * @author gchen
 * @since 2020-10-23
 */
public interface DevicesScrapService {

    /**
     * 新增
     * @param devicesScrap
     */
    Result save(DevicesScrap devicesScrap);


    /**
     * 列表查询
     *
     * @param devicesCode devicesClassify status startTime endTime
     */
    Result findList(String devicesCode, String devicesClassify,Integer devicesScrapId,Integer pageNum,Integer pageSize);

    /**
     * 根据id查询报废详情
     *
     * @param devicesScrapId
     */
    Result findById(Integer devicesScrapId);

    /**
     * 根据id查询报废详情
     *
     * @param deviceExtensionId
     */
    Result findByDeviceExtensionId(Integer deviceExtensionId);

    /**
     * 报废审核通过
     *
     * @param id
     */
    Result devicesScrapPass(Integer id);
}
