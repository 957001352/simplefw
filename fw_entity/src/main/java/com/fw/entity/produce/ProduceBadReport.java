package com.fw.entity.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 不良上报
 *
 * @author xkliu
 * @since 2020-12-08
 */
@Data
@ApiModel(value = "produceBadReport", description = "不良上报")
public class ProduceBadReport {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 注塑排产计划
     */
    @ApiModelProperty(value = "注塑排产计划")
    private Integer planMoldingId;

    /**
     * 充模不全
     */
    @ApiModelProperty(value = "充模不全")
    private String mold;

    /**
     * 多料
     */
    @ApiModelProperty(value = "多料")
    private String material;

    /**
     * Pin高低不齐
     */
    @ApiModelProperty(value = "Pin高低不齐")
    private String jagged;

    /**
     * 露铜
     */
    @ApiModelProperty(value = "露铜")
    private String copper;

    /**
     * 漏插簧片
     */
    @ApiModelProperty(value = "id", hidden = true)
    private String leakage;

    /**
     * 簧片覆盖
     */
    @ApiModelProperty(value = "漏插簧片")
    private String cover;

    /**
     * 压伤
     */
    @ApiModelProperty(value = "压伤")
    private String crush;

    /**
     * 开裂
     */
    @ApiModelProperty(value = "开裂")
    private String craze;

    /**
     * 烧焦
     */
    @ApiModelProperty(value = "烧焦")
    private String burning;

    /**
     * 油斑
     */
    @ApiModelProperty(value = "油斑")
    private String oil;

    /**
     * 其他
     */
    @ApiModelProperty(value = "其他")
    private String note;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
