package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 出库明细
 * @author gchen
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsOutHouseDetail", description = "出库明细")
public class LogisticsOutHouseDetail {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 出库对象
     */
    @ApiModelProperty(value = "出库对象")
    private Integer outHouseId;
    /**
     * 物料库存
     */
    @ApiModelProperty(value = "物料库存")
    private Integer storageDetailId;
    /**
     * 实出数量
     */
    @ApiModelProperty(value = "实出数量")
    private Integer storageCount;
    /**
     * 应出数量
     */
    @ApiModelProperty(value = "应出数量")
    private String applyCount;
    /**
     * 泛沃批次号
     */
    @ApiModelProperty(value = "泛沃批次号")
    private String fwBatch;
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
     * 物料对象
     */
    @ApiModelProperty(value = "物料对象")
    private LogisticsProduct logisticsProduct;

    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productOrder;

    /**
     * 拆包明细
     */
    @ApiModelProperty(value = "拆包明细")
    private List<LogisticsOutSubpackage> logisticsOutSubpackages;

    /**
     * 投料数量
     */
    @ApiModelProperty(value = "投料数量",hidden = true)
    private Integer feedCount;

    /**
     *物料名称
     */
    @ApiModelProperty(value = "物料名称",hidden = true)
    private String productName;

    /**
     *物料代码
     *
     */
    @ApiModelProperty(value = "物料代码",hidden = true)
    private String productCode;

    /**
     *发货id
     *
     */
    @ApiModelProperty(value = "发货id",hidden = true)
    private Integer pickingOrderId;

}
