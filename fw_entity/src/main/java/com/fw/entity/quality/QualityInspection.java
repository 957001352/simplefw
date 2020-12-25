package com.fw.entity.quality;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 检验规范
 * </p>
 *
 * @author xkliu
 * @since 2020-11-24
 */
@Data
@ApiModel(value = "qualityInspection", description = "检验规范")
public class QualityInspection {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 物料对象
     */
    @ApiModelProperty(value = "id")
    private Integer productId;

    /**
     * 图纸编号
     */
    @ApiModelProperty(value = "图纸编号")
    private String drawing;

    /**
     * 客户
     */
    @ApiModelProperty(value = "客户")
    private String customer;

    /**
     * 规范分类 0 外观 1包装 2材质 3尺寸
     */
    @ApiModelProperty(value = "规范分类")
    private Integer classify;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 巡检时间
     */
    @ApiModelProperty(value = "巡检时间")
    private Integer inspection;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private Integer createUser;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人", hidden = true)
    private Integer updateUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;

    /**
     * 版本,默认值 A0
     */
    @ApiModelProperty(value = "版本", hidden = true)
    private String version = "A0";

    /**
     * 零件代码
     */
    @ApiModelProperty(value = "零件代码", hidden = true)
    private String code;

    /**
     * 零件名称
     */
    @ApiModelProperty(value = "零件名称", hidden = true)
    private String productName;

    /**
     * 变更时间
     */
    @ApiModelProperty(value = "变更时间", hidden = true)
    private String changeTime;

    /**
     * 变更原因
     */
    @ApiModelProperty(value = "变更原因", hidden = true)
    private String reason;

    /**
     * 变更内容
     */
    @ApiModelProperty(value = "变更内容", hidden = true)
    private String content;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态", hidden = true)
    private String auditStatus;

    /**
     * 审核完成时间
     */
    @ApiModelProperty(value = "审核完成时间", hidden = true)
    private String endTime;

    /**
     * 审核结论
     */
    @ApiModelProperty(value = "审核结论", hidden = true)
    private Integer auditResult;

    /**
     * 检验规范明细
     */
    @ApiModelProperty(value = "检验规范明细")
    private List<QualityInspectionDetail> qualityInspectionDetails;

    /**
     * 变更记录对象
     */
    @ApiModelProperty(value = "变更记录对象")
    private QualityInspectionChangeLog qualityInspectionChangeLog;

    /**
     * 变更记录集合
     */
    @ApiModelProperty(value = "变更记录集合", hidden = true)
    private List<QualityInspectionChangeLog> qualityInspectionChangeLogs;


}
