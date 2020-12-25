package com.fw.service.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldStorageHouse;
import com.fw.entity.mould.MouldStorageLocation;
import com.fw.entity.mould.MouldUseRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 模具仓库管理
 * @author lpsong
 * @since 2020-10-26
 */
public interface MouldStorageHouseService {

    /**
     * 新增仓库
     *
     * @param mouldStorageHouse
     * @return
     */
    Result saveStorage(MouldStorageHouse mouldStorageHouse);


    /**
     * 删除仓库
     *
     * @param ids
     * @return
     */
    Result deleteStorage(String ids);

    /**
     * 仓库列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findStorageList(String name,Integer pageNum, Integer pageSize);


    /**
     * 新增库位
     * @param mouldStorageLocation
     * @return
     */
    Result saveLocation(MouldStorageLocation mouldStorageLocation);


    /**
     * 删除库位
     *
     * @param ids
     * @return
     */
    Result deleteLocation(String ids);

    /**
     * 库位列表查询
     * @param storageHouseId 仓库
     * @param location 库位名称
     * @param mould  模具名称
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findLocationList(Integer storageHouseId,
                            String location,
                            String mould,
                            String mouldCode,
                            Integer status,
                            Integer isBand,
                            Integer pageNum,
                            Integer pageSize);

    /**
     * 模具绑定库位/关联模具
     */
    Result boundMould(MouldDevices mouldDevices);

    /**
     * 模具解除绑定库位
     */
    Result unbundMoule(MouldDevices mouldDevices);

    /**
    * 空库位查询
     * @param 
    * @return 
    */
    Result findEmptyLocationList(String name);
}
