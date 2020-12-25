package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsCheckHouse;
import com.fw.entity.mould.MouldHouse;
import org.apache.ibatis.annotations.Param;

/**
 * 盘库
 * @author lpsong
 * @since 2020-11-12
 */
public interface LogisticsCheckHouseService {

    /**
     * 新增
     *
     * @param logisticsCheckHouse
     * @return
     */
    Result insert(LogisticsCheckHouse logisticsCheckHouse);

    /**
     * 新增
     *
     * @param logisticsCheckHouse
     * @return
     */
    Result update(LogisticsCheckHouse logisticsCheckHouse);


    /**
    * 关闭任务
     * @param id
    * @return
    */
    Result updateStatus(Integer id);


    /**
     * 列表查询
     * @param houseNo
     * @param checkTime
     * @param status
     * @param checkUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String houseNo,
                    String checkTime,
                    Integer status,
                    Integer checkResult,
                    Integer checkUser,
                    Integer pageNum,
                    Integer pageSize);

    /**
    *  盘库明细列表查询
     * @param checkHouseId
    * @return
    */
    Result findDetailList(Integer checkHouseId);

    /**
    * 根据库位查询要盘库物料
     * @param locationId
    * @return
    */
    Result findStoragePorductList(Integer locationId);

    /**
    * 仓库列表查询
     * @param name
    * @return
    */
    Result findTreeList(String name);

}
