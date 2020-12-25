package com.fw.service.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.entity.quality.QualityStoreCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QualityStoreCheckService {


    /**
     * 获取列表
     * @param checkNo
     * @param productCode
     * @param materialCode
     * @param startTime
     * @param stopTime
     * @return
     */
    Result findList(
            String productDevicesCode,
            String checkNo,
            String productCode,
            String materialCode,
            String startTime,
            String stopTime,Integer status,Integer pageNum,Integer pageSize);

    /**
     * 获取入库检外观尺寸检验信息
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
     * @param qualityInspectResultList
     * @return
     */
    Result update(QualityInspectResult qualityInspectResultList);


}
