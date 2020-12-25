package com.fw.service.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
public interface LogisticsPickingService {

    Result save(LogisticsPicking logisticsPicking);

    Result findList(Integer outHouseId, Integer pageNum, Integer pageSize);

}
