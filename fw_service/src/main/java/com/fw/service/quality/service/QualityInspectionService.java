package com.fw.service.quality.service;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspection;

/**
 * 检验规范 服务类
 *
 * @author xkliu
 * @since 2020-11-24
 */
public interface QualityInspectionService {

    /**
     * 新增检验规范
     *
     * @param qualityInspection
     * @return
     */
    Result save(QualityInspection qualityInspection);

    /**
     * 变更检验规范
     *
     * @param qualityInspection
     * @return
     */
    Result change(QualityInspection qualityInspection);

    /**
     * 列表查询
     *
     * @param code
     * @param productName
     * @param classify
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String code, String preciseCode, String productName, Integer classify, Integer pageNum, Integer pageSize);

    /**
     * 查看检验规范详情
     *
     * @param id
     * @return
     */
    Result getInspectionDetail(Integer id);

    /**
     * 查看检验规范变更记录
     *
     * @param id
     * @return
     */
    Result getInspectionChangeLog(Integer id);

    /**
     * 检验规范变更通过
     *
     * @param id
     * @return
     */
    Result inspectionChangePass(Integer id);
}
