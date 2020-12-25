package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckItem;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
public interface DevicesCheckItemService {

    /**
     * 新增
     *
     * @param devicesCheckItem
     * @return
     */
    Result save(DevicesCheckItem devicesCheckItem);


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
    Result findList(String name, String devicesClassify, String content, Integer pageNum, Integer pageSize);



}
