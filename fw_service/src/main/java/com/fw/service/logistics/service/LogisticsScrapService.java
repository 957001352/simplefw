package com.fw.service.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsScrap;

/**
 * <p>
 * 报废管理 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-16
 */

public interface LogisticsScrapService {

    Result save(LogisticsScrap logisticsScrap);

    Result delete(String ids);

    Result findList(String productCode,String productName,String productOrder,Integer pageNum, Integer pageSize);

}
