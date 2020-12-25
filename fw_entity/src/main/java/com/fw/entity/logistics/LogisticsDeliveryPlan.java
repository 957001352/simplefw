package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 发货计划
 * @author lpsong
 * @since 2020-12-04
 */
@Data
@ApiModel(value = "logisticsDeliveryPlan", description = "发货计划")
public class LogisticsDeliveryPlan {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 出库对象
     */
    @ApiModelProperty(value = "出库对象", hidden = true)
    private Integer outHouseId;
    /**
     * 客户
     */
    @ApiModelProperty(value = "客户")
    private String customer;
    /**
     * 物料
     */
    @ApiModelProperty(value = "物料")
    private Integer productId;
    /**
     * 发货数量
     */
    @ApiModelProperty(value = "发货数量")
    private Integer partsCount;
    /**
     * 计划数量
     */
    @ApiModelProperty(value = "计划数量")
    private Integer planCount;

    /**
     * 入库时间
     */
    @ApiModelProperty(value = "入库时间")
    private String storeTime;

    /**
     * 到货时间
     */
    @ApiModelProperty(value = "到货时间")
    private String arrivalTime;
    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间",hidden = true)
    private String deliverTime;
    /**
     * 托数
     */
    @ApiModelProperty(value = "托数")
    private Integer joeCount;

    /**
     * 发货状态 0未发货 1已发货
     */
    @ApiModelProperty(value = "发货状态 0未发货 1已发货")
    private Integer status;

    /**
     * 销售类型 0调拨 1销售
     */
    @ApiModelProperty(value = "销售类型 0调拨 1销售")
    private Integer planType;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "库存量",hidden = true)
    private Integer storageCount;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称",hidden = true)
    private String productName;
    /**
     * 物料代码
     */
    @ApiModelProperty(value = "物料代码",hidden = true)
    private String productCode;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位",hidden = true)
    private String unit;

    /**
     * 出库明细
     */
    @ApiModelProperty(value = "出库明细")
    private List<LogisticsOutHouseDetail> logisticsOutHouseDetails;


}
