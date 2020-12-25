package com.fw.service.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldUseRecord;

public interface MouldDevicesService {

    /**
     * 新增
     * @param mouldDevices
     */
    Result save(MouldDevices mouldDevices);

    /**
     * 查询
     * @param code  模具编号
     * @param name  模具名称
     * @param status   使用状态
     * @param turnStatus  阶段状态
     * @param pageNum   当前页
     * @param pageSize   页面显示条数
     */
    Result findList(String code, String name, Integer status, Integer turnStatus,Integer pageNum,Integer pageSize);

    /**
     * 批量删除
     * @param ids
     */
    Result delete(String ids);

    /**
     * 获取所有未绑定库位的模具
     */
    Result findUnBoundMould();


    /**
     * 新增模具使用记录
     */
    Result saveUseRecord(MouldUseRecord mouldUseRecord);

    /**
     * 查询模具使用记录
     */
    Result findListUseRecord(String mouldCode,Integer status,Integer pageNum,Integer pageSize);
}
