package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 收货明细
 * @author: wqiang
 * @create: 2020-11-12 14:58
 **/
@Data
@ApiModel(value = "LogisticsTakeOrderDetail",description = "收货明细")
public class LogisticsTakeOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "收货单ID")
    private Integer takeOrderId;

    @ApiModelProperty(value = "物料ID")
    private Integer productId;

    @ApiModelProperty(value = "物料规格")
    private String productSpecs;

    @ApiModelProperty(value = "物料单位")
    private String productUnit;

    @ApiModelProperty(value = "物料名称")
    private String productName;

    @ApiModelProperty(value = "物料编码")
    private String productCode;

    @ApiModelProperty(value = "采购数量")
    private Integer buyCount;

    @ApiModelProperty(value = "实收数量")
    private Integer receiveCount;

    @ApiModelProperty(value = "泛沃批次号")
    private String batchNo;

    @ApiModelProperty(value = "供应商名称")
    private String providerName;

    @ApiModelProperty(value = "备注")
    private String note;
}
