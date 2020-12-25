package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;


/**
 * 物流仓库 服务类
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
public interface LogisticsStorageHouseService {

    /**
     * 新增仓库
     *
     * @param logisticsStorageHouse
     * @return
     */
    Result saveStorage(LogisticsStorageHouse logisticsStorageHouse);


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
    Result findStorageList(String name, Integer pageNum, Integer pageSize);


    /**
     * 新增库位
     *
     * @param logisticsStorageLocation
     * @return
     */
    Result saveLocation(LogisticsStorageLocation logisticsStorageLocation);


    /**
     * 删除库位
     *
     * @param ids
     * @return
     */
    Result deleteLocation(String ids);

    /**
     * 库位列表查询
     *
     * @param name
     * @param productCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findLocationList(String name,
                            String productCode,
                            Integer pageNum,
                            Integer pageSize);


    /**
     * 查询库位
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findStorageLocationList(Integer pageNum, Integer pageSize);
}
