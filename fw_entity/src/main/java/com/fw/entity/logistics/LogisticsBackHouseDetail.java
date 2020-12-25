package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 退库明细
 *
 * @author xkliu
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsBackHouseDetail", description = "退库明细")
public class LogisticsBackHouseDetail {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 退库对象
     */
    @ApiModelProperty(value = "退库对象", hidden = true)
    private Integer backHouseId;

    /**
     * 物料
     */
    @ApiModelProperty(value = "物料", hidden = true)
    private Integer productId;

    /**
     * 实出数量
     */
    @ApiModelProperty(value = "实出数量")
    private Integer storageCount;

    /**
     * 应出数量
     */
    @ApiModelProperty(value = "应出数量")
    private Integer applyCount;

    /**
     * 泛沃批次号
     */
    @ApiModelProperty(value = "泛沃批次号")
    private String fwBatch;

    /**
     * 库位
     */
    @ApiModelProperty(value = "库位",hidden = true)
    private Integer storageLocationId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",hidden = true)
    private String note;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称",hidden = true)
    private String productName;

    /**
     * 物料代码
     */
    @ApiModelProperty(value = "物料代码",hidden = true)
    private String code;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位",hidden = true)
    private String unit;

    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令",hidden = true)
    private String productCode;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格",hidden = true)
    private String specs;

    /**
     * 供应商批次号
     */
    @ApiModelProperty(value = "供应商批次号")
    private String providerBatch;

}
