package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;

/**
 * 保养项目 服务类
 *
 * @author xkliu
 * @since 2020-10-19
 */
public interface DevicesKeepItemService {

    /**
     * 新增
     *
     * @param devicesKeepItem
     * @return
     */
    Result save(DevicesKeepItem devicesKeepItem);

    /**
     * 查看设备保养项目
     *
     * @param id
     * @return
     */
    Result getDevicesKeepItem(Integer id);

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, String devicesClassify, Integer pageNum, Integer pageSize);

    /**
     * 获取设备类型
     *
     * @return
     */
    Result getDevicesClassify();
}
