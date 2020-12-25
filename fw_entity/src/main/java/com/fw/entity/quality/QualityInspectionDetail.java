package com.fw.entity.quality;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 来料检验规范明细
 * </p>
 *
 * @author xkliu
 * @since 2020-11-24
 */
@Data
@ApiModel(value = "qualityInspectionDetail", description = "来料检验规范明细")
public class QualityInspectionDetail {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private String id;

    /**
     * 产品特性
     */
    @ApiModelProperty(value = "产品特性")
    private String feature;

    /**
     * 特殊特性分类
     */
    @ApiModelProperty(value = "特殊特性分类")
    private String special;

    /**
     * 工艺（过程）特性
     */
    @ApiModelProperty(value = "工艺（过程）特性")
    private String process;

    /**
     * 产品/过程规范/公差
     */
    @ApiModelProperty(value = "产品/过程规范/公差")
    private String standard;

    /**
     * 评价/测量技术
     */
    @ApiModelProperty(value = "评价/测量技术")
    private String evaluate;

    /**
     * 大小
     */
    @ApiModelProperty(value = "大小")
    private String measure;

    /**
     * 额度
     */
    @ApiModelProperty(value = "额度")
    private String quota;

    /**
     * 控制方法
     */
    @ApiModelProperty(value = "控制方法")
    private String control;

    /**
     * 检验规范
     */
    @ApiModelProperty(value = "检验规范", hidden = true)
    private Integer inspectionId;

    /**
     * 规范分类 0 外观 1包装 2材质 3尺寸
     */
    @ApiModelProperty(value = "规范分类")
    private Integer classify;

    /**
     * 附件路径
     */
    @ApiModelProperty(value = "附件路径", hidden = true)
    private String webPath;

    /**
     * 附件路径
     */
    @ApiModelProperty(value = "附件路径", hidden = true)
    private String path;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称", hidden = true)
    private String  name;

    /**
     * 附件后缀
     */
    @ApiModelProperty(value = "附件后缀", hidden = true)
    private String  suffix;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String remark;

    /**
     * 巡检时间
     */
    @ApiModelProperty(value = "巡检时间", hidden = true)
    private Integer inspection;


}
