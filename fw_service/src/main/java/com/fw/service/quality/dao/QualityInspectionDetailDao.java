package com.fw.service.quality.dao;

import com.fw.entity.quality.QualityInspectionDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 来料检验规范明细 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-24
 */
@Repository
public interface QualityInspectionDetailDao {

    /**
     * 批量插入
     * @param details
     * @return
     */
    Integer batchInsert(@Param("details") List<QualityInspectionDetail> details);

    /**
     * 根据检验规范id删除明细
     * @param inspectionId
     * @return
     */
    Integer deleteByInspectionId(@Param("inspectionId") Integer inspectionId);

}
