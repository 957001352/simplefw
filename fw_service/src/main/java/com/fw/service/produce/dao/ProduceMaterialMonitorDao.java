package com.fw.service.produce.dao;


import com.fw.entity.produce.ProduceMaterialMonitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 物料过程监控 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-15
 */
@Repository
public interface ProduceMaterialMonitorDao {

    Integer insert(List<ProduceMaterialMonitor> ProduceMaterialMonitorList);

    Integer update(List<ProduceMaterialMonitor> ProduceMaterialMonitorList);

    List<ProduceMaterialMonitor> findList(@Param(value = "productCode") String productCode,
                                          @Param(value = "productName") String productName,
                                          @Param(value = "startTime") String startTime,
                                          @Param(value = "stopTime") String stopTime,
                                          @Param(value = "timeType") Integer timeType);

    List<LinkedHashMap<String,Object>> findPlantUseMaterialList(@Param(value = "productCode") String productCode,
                                                @Param(value = "startTime") String startTime,
                                                @Param(value = "stopTime") String stopTime);

}
