package com.fw.service.logistics.dao;


import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.produce.ProduceReworkRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
@Repository
public interface LogisticsPickingDao {


    Integer insert(LogisticsPicking logisticsPicking);


    List<LogisticsPicking> findList(@Param(value = "outHouseId") Integer outHouseId);


    List<LinkedHashMap<String,Object>>  findDetailListByOutHouseId(@Param(value = "outHouseId") Integer outHouseId);

    LogisticsPicking getLogisticsPicking(String  productOrder);
 }
