package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 移库物料明细
 * @author: wqiang
 * @create: 2020-11-12 10:11
 **/

@Data
@ApiModel(value = "LogisticsMoveHouseDetail", description = "移库物料明细")
public class LogisticsMoveHouseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;


    @ApiModelProperty(value = "移库对象")
    private Integer moveHouseId;

    @ApiModelProperty(value = "物料明细对象")
    private Integer storageDetailId;

    @ApiModelProperty(value = "物料名称")
    private String productName;

    @ApiModelProperty(value = "物料编码")
    private String productCode;

    @ApiModelProperty(value = "物料规格")
    private String productSpecs;

    @ApiModelProperty(value = "泛沃批次")
    private String fwBatch;

    @ApiModelProperty(value = "原库位 ")
    private Integer oldLoaction;

    @ApiModelProperty(value = "原库位名称 ")
    private String oldLoactionName;

    @ApiModelProperty(value = "原库位编码 ")
    private String oldLoactionCode;

    @ApiModelProperty(value = "新库位")
    private Integer newLoaction;

    @ApiModelProperty(value = "新库位")
    private String newLoactionName;

    @ApiModelProperty(value = "新库位编码")
    private String newLoactionCode;

    @ApiModelProperty(value = "移库数量")
    private Integer storageCount;

}
