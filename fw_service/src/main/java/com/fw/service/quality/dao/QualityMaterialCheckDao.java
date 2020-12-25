package com.fw.service.quality.dao;


import com.fw.entity.logistics.LogisticsTakeOrderDetail;
import com.fw.entity.quality.QualityMaterialCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface QualityMaterialCheckDao {

    Integer save(QualityMaterialCheck qualityMaterialCheck);

    Integer update(QualityMaterialCheck qualityMaterialCheck);

    List<QualityMaterialCheck> findList(@Param(value = "checkNo") String checkNo,
                                        @Param(value = "startTime") String startTime,
                                        @Param(value = "stopTime") String stopTime,
                                        @Param(value = "exeStartTime") String exeStartTime,
                                        @Param(value = "exeStopTime") String exeStopTime,
                                        @Param(value = "status") Integer status);

    List<LinkedHashMap<String, Object>> getCheckoutMaterialInfoById(@Param(value = "id") Integer id);

    String findCode(@Param(value = "code") String code);

    QualityMaterialCheck findCheckoutById(@Param(value = "id") Integer id);


}
