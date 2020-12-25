package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 模具维修备品备件实体
 * @author: wqiang
 * @create: 2020-10-21 17:08
 **/
@Data
@ApiModel(value = "模具维修备品备件实体")
public class MouldRepairSpare implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "模具维修ID")
    private Integer mouldRepairId;

    @ApiModelProperty(value = "修改方案 0维修 1更换")
    private Integer repairPlan;


    @ApiModelProperty(value = "使用数量")
    private Integer useCount;


    @ApiModelProperty(value = "模具备品备件id")
    private Integer mouldSpareId;

    @ApiModelProperty(value = "模具备品备件名称")
    private String mouldSpareName;

    @ApiModelProperty(value = "模具备品备件名称")
    private String mouldSpareCode;

    @ApiModelProperty(value = "模具备品备件库存数量")
    private Integer storeCount;
}
