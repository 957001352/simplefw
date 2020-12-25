package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsDownHouse;
import com.fw.entity.logistics.LogisticsUpHouse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 下架 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-10
 */
@Repository
public interface LogisticsDownHouseDao {

    /**
     * 新增
     * @param logisticsDownHouse
     * @return
     */
    Integer insert(LogisticsDownHouse logisticsDownHouse);


    /**
     * 下架架列表
     * @return
     */
    List<LogisticsDownHouse> findDownList(String code);

    /***
     * 查看下架明细
     * @param id
     * @return
     */
    LogisticsDownHouse getDownHouse(Integer id);
}
