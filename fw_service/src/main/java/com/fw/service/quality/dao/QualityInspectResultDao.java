package com.fw.service.quality.dao;


import com.fw.entity.quality.QualityInspectResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 检验结果 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@Repository
public interface QualityInspectResultDao{
    Integer save(QualityInspectResult qualityInspectResult);

    Integer update(QualityInspectResult qualityInspectResult);

    /**
     *
     * @param dataId  检验对象
     * @param classify  规范分类 0 来料 1首末检/巡检 2入库 3出库
     * 检验结果分类 0-1 首件外观 0-2首件尺寸 1-1末件外观 1-2 末检尺寸 2-1巡检外观 2-2巡检尺寸  3来料检验 4-0入库外观 4-1入库尺寸 5出库检验
     * @return
     */
    List<QualityInspectResult> findResultByDataIdAndClassify(@Param(value = "dataId") Integer dataId,
                                                             @Param(value = "classify") Integer classify);

    List<String> findTakeDetailIds(@Param(value = "id") Integer id);

    List<QualityInspectResult> findHistoryResultByOfon(@Param(value = "ofNo") String ofNo);
}
