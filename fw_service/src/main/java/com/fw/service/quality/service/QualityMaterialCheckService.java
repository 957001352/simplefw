package com.fw.service.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 来料检验管理 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-25
 */
public interface QualityMaterialCheckService {

    Result findList(String checkNo, String startTime, String stopTime, String exeStartTime, String exeStopTime,Integer status,Integer pageNum,Integer pageSize);

    Result getCheckoutMaterialInfoById(@Param(value = "id") Integer id);

    /**
     * 提交质检结果
     * @param qualityInspectResultList
     * @return
     */
    Result submit(List<QualityInspectResult> qualityInspectResultList);


    Result findResultByDataIdAndClassify(Integer dataId);
}
