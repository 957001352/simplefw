package com.fw.service.logistics.dao;


import com.fw.entity.logistics.LogisticsScrap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 报废管理 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-16
 */
@Repository
public interface LogisticsScrapDao  {

    Integer insert(LogisticsScrap logisticsScrap);

    Integer update(LogisticsScrap logisticsScrap);

    Integer delete(List<String> ids);

    List<LogisticsScrap> findList(@Param(value = "productCode") String productCode,
                                  @Param(value = "productName") String productName,
                                  @Param(value = "productOrder") String productOrder
    );

    Integer findById(@Param(value = "id") Integer id);
}
