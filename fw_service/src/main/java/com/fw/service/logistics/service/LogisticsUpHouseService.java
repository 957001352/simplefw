package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDownHouse;
import com.fw.entity.logistics.LogisticsUpHouse;

/**
 * 上架服务类
 *
 * @author xkliu
 * @date 2020/11/10
 */
public interface LogisticsUpHouseService {

    /**
     * 新增上架
     *
     * @param logisticsUpHouse
     * @return
     */
    Result upHouse(LogisticsUpHouse logisticsUpHouse);

    /**
     * 新增下架
     *
     * @param logisticsDownHouse
     * @return
     */
    Result downHouse(LogisticsDownHouse logisticsDownHouse);

    /**
     * 上架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findUpList( String code,Integer pageNum, Integer pageSize);

    /**
     * 下架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findDownList(String code,Integer pageNum, Integer pageSize);

    /**
     * 查看上架明细
     *
     * @param id
     * @return
     */
    Result getUpHouse(Integer id);

    /**
     * 查看下架明细
     *
     * @param id
     * @return
     */
    Result getDownHouse(Integer id);

    /**
     * 上下架记录
     *
     * @param code
     * @param storageName
     * @param startCreateTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findAllList(String code, String storageName, String startCreateTime,String endCreateTime, Integer pageNum, Integer pageSize);
}
