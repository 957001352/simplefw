package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsDownHouseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 下架明细 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-10
 */
@Repository
public interface LogisticsDownHouseDetailDao {

    /**
     * 批量插入
     *
     * @param logisticsDownHouseDetail
     * @return
     */
    Integer batchInsert(@Param("logisticsDownHouseDetail") List<LogisticsDownHouseDetail> logisticsDownHouseDetail);
}
