package com.fw.service.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;

import java.util.List;

/**
 * <p>
 * 首末检验管理 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
public interface QualityFirstendCheckService {


    /**
     * 列表查询
     * @param productCode
     * @param checkType
     * @param startTime
     * @param stopTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(Integer id,String productCode,String productDevicesCode,Integer checkType,String startTime,String stopTime,Integer status, Integer pageNum,Integer pageSize);

    /**
     * 外观检验
     * @param id
     * @return
     */
    Result findAppearanceOrSizeInspectInfo(Integer id);

    /**
     * 提交质检结果
     * @param qualityInspectResultList
     * @return
     */
    Result submit(List<QualityInspectResult> qualityInspectResultList);


    /**
     * 更新质检结果
     * @param qualityInspectResult
     * @return
     */
    Result update(QualityInspectResult qualityInspectResult);

    Result coerceClose(QualityFirstendCheck qualityFirstendCheck);

    Result postponeExe(QualityFirstendCheck qualityFirstendCheck);

    Result updateCoerceCloseStatus(Integer id);
    Result updatepostponeExeStatus(Integer id);

}
