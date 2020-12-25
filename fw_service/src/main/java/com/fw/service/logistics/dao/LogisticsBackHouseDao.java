package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsBackHouse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退库 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-12
 */
@Repository
public interface LogisticsBackHouseDao {

    /**
     * 列表查询
     *
     * @return
     */
    List<LogisticsBackHouse> findList(@Param("status") Integer status, @Param("houseNo") String houseNo, @Param("productOrder") String productOrder);

    /**
     * 修改退库状态
     *
     * @param logisticsBackHouse
     * @return
     */
    Integer updateStatus(LogisticsBackHouse logisticsBackHouse);

    /**
     * 历史退库列表
     *
     * @return
     */
    List<LogisticsBackHouse> findHistoryList(@Param("houseNo") String houseNo, @Param("code") String code, @Param("productCode") String productCode, @Param("startExecuteTime") String startExecuteTime, @Param("endExecuteTime") String endExecuteTime);

    /**
     * 获取退库详情
     *
     * @param id
     * @return
     */
    LogisticsBackHouse getBackHouse(Integer id);


    /**
     * 新增
     *
     * @param logisticsBackHouse
     * @return
     */
    Integer insert(LogisticsBackHouse logisticsBackHouse);

    /**
     * 退库单号查询
     *
     * @param
     * @return
     */
    String findCode(String code);

    /**
     * 修改
     *
     * @param logisticsBackHouse
     * @return
     */
    Integer update(LogisticsBackHouse logisticsBackHouse);

    /**
     * 根据id获取LogisticsBackHouse对象
     *
     * @param id
     * @return
     */
    LogisticsBackHouse selectById(Integer id);


    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);
}
