package com.fw.service.quality.dao;

import com.fw.entity.quality.QualityInspectionChangeLog;
import org.springframework.stereotype.Repository;

/**
 * 变更记录 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-24
 */
@Repository
public interface QualityInspectionChangeLogDao {

    /**
     * 新增
     *
     * @param qualityInspectionChangeLog
     * @return
     */
    Integer insert(QualityInspectionChangeLog qualityInspectionChangeLog);

    /**
     * 查看检验规范变更记录
     *
     * @param id
     * @return
     */
    QualityInspectionChangeLog getInspectionChangeLog(Integer id);
}
