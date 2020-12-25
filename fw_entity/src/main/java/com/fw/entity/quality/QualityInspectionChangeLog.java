package com.fw.entity.quality;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 变更记录
 * </p>
 *
 * @author xkliu
 * @since 2020-11-24
 */
@Data
@ApiModel(value = "qualityInspectionChangeLog", description = "变更记录")
public class QualityInspectionChangeLog {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 变更原因
     */
    @ApiModelProperty(value = "变更原因")
    private String reason;

    /**
     * 变更内容
     */
    @ApiModelProperty(value = "变更内容")
    private String content;

    /**
     * 变更人
     */
    @ApiModelProperty(value = "变更人", hidden = true)
    private Integer createUser;

    /**
     * 变更人名字
     */
    @ApiModelProperty(value = "变更人名字", hidden = true)
    private String createUserName;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", hidden = true)
    private String version;

    /**
     * 检验规范
     */
    @ApiModelProperty(value = "检验规范", hidden = true)
    private Integer inspectionId;

    /**
     * 变更时间
     */
    @ApiModelProperty(value = "变更时间", hidden = true)
    private String createTime;

    /**
     * 检验规范内容
     */
    @ApiModelProperty(value = "检验规范内容")
    private String inspectionContent;

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


}
