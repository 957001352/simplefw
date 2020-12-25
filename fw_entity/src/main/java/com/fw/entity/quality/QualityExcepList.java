package com.fw.entity.quality;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 异常联络单
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@Data

public class QualityExcepList implements Serializable {




    private static final long serialVersionUID = 1L;


    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 异常单编号
     */
    private String excepNo;

    /**
     * 物料对象
     */
    private String productId;

    /**
     * 来料数量
     */
    private String allCount;

    /**
     * 抽检数量
     */
    private String checkCount;

    /**
     * 不良数量
     */
    private String badCount;

    /**
     * 来料批次号
     */
    private String batch;

    /**
     * 采购订单号
     */
    private String orderNo;

    /**
     * 供应商名称
     */
    private String provider;

    /**
     * 检验员
     */
    private String checkUser;

    /**
     * 异常发生区域
     */
    private Integer excepArea;

    /**
     * 不良描述
     */
    private String badDesc;

    /**
     * 处理方案
     */
    private String dealPlan;

    /**
     * 处理时间
     */
    private String dealTime;

    /**
     * 原因分析及纠正预防措施
     */
    private String preventStep;

    /**
     * 制定日期
     */
    private String preventDate;

    /**
     * 效果确认
     */
    private String confirmResult;

    /**FwQualityInspectResult.java
     * 确认日期
     */
    private String confirmDate;

    /**
     * 规范分类
     */
    private Integer classify;

    /**
     * 检验对象
     */
    private Integer dataId;


    /**
     * 创建时间
     */
    private String createTime;


}
