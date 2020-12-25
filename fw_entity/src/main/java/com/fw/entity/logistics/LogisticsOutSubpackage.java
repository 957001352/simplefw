package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 出库拆包明细
 * @author gchen
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsOutSubpackage", description = "出库拆包明细")
public class LogisticsOutSubpackage {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 出库明细
     */
    @ApiModelProperty(value = "出库明细")
    private Integer outDetailId;

    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号")
    private String batch;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private String outCount;
    /**
     * 二维码
     */
    @ApiModelProperty(value = "二维码")
    private String qrCode;

    /**
     * 物料ID
     */
    @ApiModelProperty(value = "物料ID")
    private Integer productId;
    /**
     * 物料编号
     */
    @ApiModelProperty(value = "物料编号")
    private String productCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String productName;
    /**
     * 物料单位
     */
    @ApiModelProperty(value = "物料单位")
    private String unit;
}
