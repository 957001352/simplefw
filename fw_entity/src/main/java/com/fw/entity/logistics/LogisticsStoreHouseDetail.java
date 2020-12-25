package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 入库明细
 * @author gchen
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsStoreHouseDetail", description = "入库明细")
public class LogisticsStoreHouseDetail {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 入库对象
     */
    @ApiModelProperty(value = "入库对象")
    private Integer storeHouseId;

    /**
     * 物料
     */
    @ApiModelProperty(value = "物料")
    private Integer productId;

    /**
     * 实收数量
     */
    @ApiModelProperty(value = "实收数量")
    private Integer storageCount;

    /**
     * 物料数量
     */
    @ApiModelProperty(value = "物料数量")
    private String materialCount;

    /**
     * 泛沃批次号
     */
    @ApiModelProperty(value = "泛沃批次号")
    private String fwBatch;

    /**
     * 供应商批次号
     */
    @ApiModelProperty(value = "供应商批次号")
    private String providerBatch;
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
     * 物料信息
     */
    @ApiModelProperty(value = "物料信息")
    private LogisticsProduct logisticsProduct;
    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String orderNo;
    /**
     * 库位code
     */
    @ApiModelProperty(value = "库位code")
    private String storageCode;
    /**
     * 收货明细Id
     */
    @ApiModelProperty(value = "收货明细Id",hidden = true)
    private String orderDetailId;

    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productCode;

    /**
     * 生产设备ID
     */
    @ApiModelProperty(value = "生产设备ID")
    private Integer productDevicesId;

}
