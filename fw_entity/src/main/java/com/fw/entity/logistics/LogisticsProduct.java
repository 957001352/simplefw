package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 物料管理
 *
 * @author xkliu
 * @since 2020-11-03
 */
@Data
@ApiModel(value = "logisticsProduct", description = "物料管理")
public class LogisticsProduct {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String name;

    /**
     * 物料长编码
     */
    @ApiModelProperty(value = "物料长编码")
    private String code;

    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    private String specs;

    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型")
    private String category;

    /**
     * 最大库存
     */
    @ApiModelProperty(value = "最大库存", hidden = true)
    private Integer maxStore;

    /**
     * 最小库存
     */
    @ApiModelProperty(value = "最小库存", hidden = true)
    private Integer minStore;

    /**
     * 保质期
     */
    @ApiModelProperty(value = "保质期", hidden = true)
    private Integer warnTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 入库时间
     */
    @ApiModelProperty(value = "入库时间")
    private String storageDate;

    /**
     * 存放天数
     */
    @ApiModelProperty(value = "存放天数", hidden = true)
    private Long day;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量", hidden = true)
    private Integer storageCount;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 安全库存
     */
    @ApiModelProperty(value = "安全库存", hidden = true)
    private Integer safetyStore;

    /**
     * 库存量
     */
    @ApiModelProperty(value = "库存量", hidden = true)
    private Integer storeCount;

    /**
     * 物料属性
     */
    @ApiModelProperty(value = "物料属性")
    private String property;


    /**
     * 是否进行保质期管理
     */
    @ApiModelProperty(value = "是否进行保质期管理", hidden = true)
    private String isSafety;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private String updateTime;

    /**
     * 泛沃批次
     */
    @ApiModelProperty(value = "泛沃批次", hidden = true)
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
     * 库位代码
     */
    @ApiModelProperty(value = "库位代码", hidden = true)
    private String storageCode;

    /**
     * 库位ID
     */
    @ApiModelProperty(value = "库位ID", hidden = true)
    private Integer storageId;

    /**
     * 物料库存ID
     */
    @ApiModelProperty(value = "物料库存ID", hidden = true)
    private Integer storageDetailId;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", hidden = true)
    private String productDate;

}
