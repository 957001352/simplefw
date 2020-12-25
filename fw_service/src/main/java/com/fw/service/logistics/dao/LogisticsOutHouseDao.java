package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsOutHouse;
import com.fw.entity.logistics.LogisticsStorageDetail;
import com.fw.entity.logistics.LogisticsStoreHouse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 出库Dao接口
 * @author gchen
 * @since 2020-11-16
 */
@Repository
public interface LogisticsOutHouseDao {
    /**
     * 新增
     * @param logisticsOutHouses
     */
    Integer insert(@Param("logisticsOutHouses") List<LogisticsOutHouse> logisticsOutHouses);


    /**
     * 通过计划增加出库任务
     * @param logisticsOutHouse
     */
    Integer planInsert(LogisticsOutHouse logisticsOutHouse);
    /**
     * 查询
     */
    List<LogisticsOutHouse> findList(@Param("houseNo") String houseNo,
                                       @Param("houseTypes") String[] houseTypes,
                                       @Param("status") String[] status,
                                       @Param("startTime") String startTime,
                                       @Param("endTime") String endTime);
    /**
     * 修改
     */
    Integer update(LogisticsOutHouse logisticsOutHouse);

    /**
     * 根据物料编号查询物料库存详情
     */
    LogisticsStorageDetail findById(@Param("id") Integer id,@Param("outHouseId") Integer outHouseId);
    /**
     * 编码查询
     * @param
     * @return
     */
    String findCode(String  code);


    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 关闭前一天任务
     * @param
     * @return
     */
    Integer updateStatus();

    /**
     * 外部出库扫描二维码
     */
    LogisticsStorageDetail outScanQrCode(Integer id, Integer outHouseId);
}
