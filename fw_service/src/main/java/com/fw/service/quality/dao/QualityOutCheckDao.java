package com.fw.service.quality.dao;


import com.fw.entity.quality.QualityOutCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 出库检验管理 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-26
 */
@Repository
public interface QualityOutCheckDao {


    Integer save(QualityOutCheck qualityOutCheck);

    List<QualityOutCheck> findList(@Param(value = "checkNo") String checkNo,
                                   @Param(value = "customer") String customer,
                                   @Param(value = "materialCode") String materialCode,
                                   @Param(value = "startTime") String startTime,
                                   @Param(value = "stopTime") String stopTime,
                                   @Param(value = "status") Integer status
    );

    Integer update(QualityOutCheck qualityOutCheck);
}
