package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 商品库存
 *
 * @author xkliu
 * @since 2020-11-03
 */
@Data
@ApiModel(value = "logisticsStorageDetail", description = "商品库存")
public class LogisticsStorageDetail {

    private static final long serialVersionUID = 1L;
    public LogisticsStorageDetail(){

     }
    public LogisticsStorageDetail(Integer opreateDetailId,Integer storageId, Integer productId, Integer storageCount, String fwBatch, String providerName, String providerBatch,String orderDetailId) {
        this.opreateDetailId=opreateDetailId;
        this.storageId = storageId;
        this.productId = productId;
        this.storageCount = storageCount;
        this.fwBatch = fwBatch;
        this.providerName = providerName;
        this.providerBatch = providerBatch;
        this.orderDetailId=orderDetailId;
    }
    public LogisticsStorageDetail(Integer opreateDetailId,Integer storageId, Integer productId, Integer storageCount, String fwBatch) {
        this.opreateDetailId=opreateDetailId;
        this.storageId = storageId;
        this.productId = productId;
        this.storageCount = storageCount;
        this.fwBatch = fwBatch;
    }
    public LogisticsStorageDetail(Integer id, Integer storageId) {
        this.id = id;
        this.storageId = storageId;
    }

    public LogisticsStorageDetail(Integer id, Integer beforeCount,Integer storageCount) {
        this.id = id;
        this.storageCount = storageCount;
        this.beforeCount = beforeCount;
    }

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 操作明细表id
     */
    @ApiModelProperty(value = "操作明细表id", hidden = true)
    private Integer opreateDetailId;
    /**
     * 库位对象
     */
    @ApiModelProperty(value = "库位对象", hidden = true)
    private Integer storageId;

    @ApiModelProperty(value = "库位对象名称", hidden = true)
    private String storageName;
    /**
     * 物料
     */
    @ApiModelProperty(value = "物料", hidden = true)
    private Integer productId;
    /**
     * 物料代码
     */
    @ApiModelProperty(value = "物料", hidden = true)
    private String productCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料", hidden = true)
    private String productName;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", hidden = true)
    private Integer storageCount;
    /**
     * 操作前数量
     */
    @ApiModelProperty(value = "操作前数量", hidden = true)
    private Integer beforeCount=0;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", hidden = true)
    private String productDate;

    /**
     * 泛沃批次号
     */
    @ApiModelProperty(value = "泛沃批次号", hidden = true)
    private String fwBatch;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商", hidden = true)
    private String providerName;

    /**
     * 供应商批次号
     */
    @ApiModelProperty(value = "供应商批次号", hidden = true)
    private String providerBatch;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期", hidden = true)
    private String storageDate;
    /**
     * 库位编号
     */
    @ApiModelProperty(value = "库位编号")
    private String storageCode;
    /**
     * 物料单位
     */
    @ApiModelProperty(value = "物料单位")
    private String unit;
    /**
     * 应出数量
     */
    @ApiModelProperty(value = "应出数量")
    private String applyCount;

    /**
     * 出库明细id
     */
    @ApiModelProperty(value = "出库明细id")
    private Integer outDetailId;

    /**
     * 发货计划id
     */
    @ApiModelProperty(value = "发货计划id")
    private Integer deliveryPlanId;
    /**
     * 收货明细Id
     */
    @ApiModelProperty(value = "收货明细Id",hidden = true)
    private String orderDetailId;
}
