package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 库存操作日志
 *
 * @author xkliu
 * @since 2020-11-03
 */
@Data
@ApiModel(value = "logisticsStorageLog", description = "库存操作日志")
public class LogisticsStorageLog {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 物料库存
     */
    @ApiModelProperty(value = "物料库存", hidden = true)
    private Integer storageDetailId;
    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量", hidden = true)
    private String storageCount;
    /**
     * 操作前数量
     */
    @ApiModelProperty(value = "操作前数量", hidden = true)
    private String beforeCount;
    /**
     * 操作后数量
     */
    @ApiModelProperty(value = "操作后数量", hidden = true)
    private String afterCount;
    /**
     * 操作方式 0入库1出库2退库
     */
    @ApiModelProperty(value = "操作方式 0入库1出库2退库", hidden = true)
    private Integer opreateType;
    /**
     * 操作明细表id
     */
    @ApiModelProperty(value = "操作明细表id", hidden = true)
    private Integer opreateDetailId;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人", hidden = true)
    private Integer opreateUser;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", hidden = true)
    private String opreateTime;


}
