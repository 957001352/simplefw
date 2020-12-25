package com.fw.service.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.MouldRepairItem;

public interface MouldRepairItemService {
    /**
     * 新增
     *
     * @param mouldRepairItem
     * @return
     */
    Result save(MouldRepairItem mouldRepairItem);

    /**
     * 获取列表
     */

    Result findList(String name,Integer pageNum, Integer pageSize);


    Result delete(String ids);
}
