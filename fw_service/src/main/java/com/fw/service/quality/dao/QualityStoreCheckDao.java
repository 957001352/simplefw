package com.fw.service.quality.dao;

import com.fw.entity.quality.QualityStoreCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 入库检验管理 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-26
 */
@Repository
public interface QualityStoreCheckDao {

    List<QualityStoreCheck> findList(
                                     @Param(value = "checkNo") String checkNo,
                                     @Param(value = "productCode") String productCode,
                                     @Param(value = "materialCode") String materialCode,
                                     @Param(value = "startTime") String startTime,
                                     @Param(value = "stopTime") String stopTime,
                                     @Param(value = "status") Integer status);


    Integer save(QualityStoreCheck qualityStoreCheck);


    Integer udpate(QualityStoreCheck qualityStoreCheck);

    /**
     * 单号查询
     *
     * @param
     * @return
     */
    String findCode(String code);

}
