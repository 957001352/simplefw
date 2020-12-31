package com.fw.service.quality.dao;


import com.fw.entity.quality.QualityFirstendCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.SunHints;

import java.util.List;

/**
 * <p>
 * 首末检验管理 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@Repository
public interface QualityFirstendCheckDao  {

    List<QualityFirstendCheck> findList(
                                        @Param(value = "id") Integer id,
                                        @Param(value = "productCode") String productCode,
                                        @Param(value = "checkType") Integer checkType,
                                        @Param(value = "startTime") String startTime,
                                        @Param(value = "stopTime") String stopTime,
                                        @Param(value = "status")Integer status
    );

    Integer update(QualityFirstendCheck qualityFirstendCheck);


    QualityFirstendCheck findFirstendCheckByid(@Param(value = "id") Integer id);


    Integer save(QualityFirstendCheck qualityFirstendCheck);


    /**
     * 单号查询
     *
     * @param
     * @return
     */
    String findCode(String code);


    QualityFirstendCheck findCheckByProductOrder(@Param(value = "productCode") String productCode);



}
