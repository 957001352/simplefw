package com.fw.service.quality.dao;

import com.fw.entity.quality.QualityInspection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 检验规范 Dao 接口
 *
 * @author xkliu
 * @since 2020-11-24
 */
@Repository
public interface QualityInspectionDao {

    /**
     * 新增
     *
     * @param qualityInspection
     * @return
     */
    Integer insert(QualityInspection qualityInspection);

    /**
     * 根据id获取 QualityInspection 对象
     *
     * @param id
     * @return
     */
    QualityInspection selectById(@Param("id") Integer id);

    /**
     * 根据物料id获取 QualityInspection 对象
     *
     * @param productId
     * @return
     */
    QualityInspection selectByProductId(@Param("productId") Integer productId);

    /**
     * 修改
     *
     * @param qualityInspection
     * @return
     */
    Integer update(QualityInspection qualityInspection);

    /**
     * 列表查询
     *
     * @param code
     * @param productName
     * @param classify
     * @return
     */
    List<QualityInspection> findList(@Param("code") String code, @Param("preciseCode") String preciseCode, @Param("productName") String productName, @Param("classify") Integer classify);

    /**
     * 查看检验规范详情
     *
     * @param id
     */
    QualityInspection getInspectionDetail(@Param("id") Integer id);

    /**
     * 校验零件是否重复
     *
     * @param productId
     * @return
     */
    Integer verifyCode(@Param("productId") Integer productId, @Param("classify") Integer classify);
}
