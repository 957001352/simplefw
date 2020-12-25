package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsUpHouse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 上架 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-10
 */
@Repository
public interface LogisticsUpHouseDao {

    /**
     * 新增
     * @param logisticsUpHouse
     * @return
     */
    Integer insert(LogisticsUpHouse logisticsUpHouse);

    /**
     * 上架列表
     * @return
     */
    List<LogisticsUpHouse> findUpList(String code);

    /**
     * 获取上架明细
     * @return
     */
    LogisticsUpHouse getUpHouse(Integer id);
}
