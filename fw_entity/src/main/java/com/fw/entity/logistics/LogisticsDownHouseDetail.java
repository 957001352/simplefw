package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 下架明细
 *
 * @author xkliu
 * @since 2020-11-10
 */
@Data
@ApiModel(value = "logisticsDownHouseDetail", description = "下架明细")
public class LogisticsDownHouseDetail {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 物料
     */
    @ApiModelProperty(value = "物料")
    private Integer storageDetailId;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer storageCount;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private String productDate;

    /**
     * 批次
     */
    @ApiModelProperty(value = "批次")
    private String batch;

    /**
     * 库位
     */
    @ApiModelProperty(value = "库位")
    private Integer storageLocationId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 下架对象
     */
    @ApiModelProperty(value = "下架对象")
    private Integer downHouseId;

    /**
     * 物料代码
     */
    @ApiModelProperty(value = "物料代码")
    private String code;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String productName;

    /**
     * 库位名称
     */
    @ApiModelProperty(value = "库位名称")
    private String storageName;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

}
