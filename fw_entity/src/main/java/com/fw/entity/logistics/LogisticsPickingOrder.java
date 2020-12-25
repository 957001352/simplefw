package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 领料单
 * @author lpsong
 * @since 2020-12-18
 */
@Data
@ApiModel(value = "logisticsPickingOrder", description = "领料单")
public class LogisticsPickingOrder {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String productCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String productName;
    /**
     * 泛沃批次号
     */
    @ApiModelProperty(value = "泛沃批次号", hidden = true)
    private String fwBatch;
    /**
     * 应出数量
     */
    @ApiModelProperty(value = "应出数量")
    private Integer applyCount;
    /**
     * 实发数量
     */
    @ApiModelProperty(value = "实发数量", hidden = true)
    private Integer storageCount;
    /**
     * 制单时间
     */
    @ApiModelProperty(value = "制单时间", hidden = true)
    private String createTime;
    /**
     * 出库对象
     */
    @ApiModelProperty(value = "出库对象")
    private Integer outHouseId;
    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令", hidden = true)
    private String productOrder;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;


}
