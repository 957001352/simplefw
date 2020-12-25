package com.fw.service.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.entity.quality.QualityOutCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 出库检验管理 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-26
 */


public interface QualityOutCheckService  {


    /**
     * 获取列表
     * @param checkNo
     * @param customer
     * @param materialCode
     * @param startTime
     * @param stopTime
     * @return
     */
    Result findList(String checkNo,
                    String customer,
                    String materialCode,
                    String startTime,
                    String stopTime,Integer status,Integer pageNum,Integer pageSize);

    /**
     * 提交质检结果
     * @param qualityInspectResultList
     * @return
     */
    Result submit(List<QualityInspectResult> qualityInspectResultList);


    Result findResultByDataIdAndClassify(Integer dataId);


    /**
     * 更新质检结果
     * @param qualityInspectResultList
     * @return
     */

    public Result update(QualityInspectResult qualityInspectResultList);
}
