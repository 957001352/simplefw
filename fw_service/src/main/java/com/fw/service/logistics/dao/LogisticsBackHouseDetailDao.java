package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsBackHouseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退库明细 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-12
 */
@Repository
public interface LogisticsBackHouseDetailDao {

    /**
     * 根据退库id获取明细
     *
     * @param id
     * @return
     */
    List<LogisticsBackHouseDetail> getBackHouseDetail(Integer id);

    /**
     * 批量修改退库明细数量
     *
     * @param logisticsBackHouseDetails
     * @return
     */
    Integer cancellingStocks(List<LogisticsBackHouseDetail> logisticsBackHouseDetails);

    /**
     * 批量插入
     *
     * @param logisticsBackHouseDetail
     * @return
     */
    Integer batchInsert(@Param("logisticsBackHouseDetail") List<LogisticsBackHouseDetail> logisticsBackHouseDetail);

    /**
     * 批量修改
     *
     * @param list
     * @return
     */
    Integer batchUpdate(@Param("list") List<LogisticsBackHouseDetail> list);

    /**
     * 根据退库对象ID删除明细数据
     *
     * @param houseId
     * @return
     */
    Integer delteByHouseId(Integer houseId);
}
