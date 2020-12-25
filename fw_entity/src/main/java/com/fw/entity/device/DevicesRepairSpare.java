package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 维修备品备件实体
 * @author: wqiang
 * @create: 2020-10-21 17:08
 **/
@Data
@ApiModel(value = "维修备品备件实体")
public class DevicesRepairSpare implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "设备维修ID")
    private Integer devicesRepairId;

    @ApiModelProperty(value = "修改方案 0维修 1更换")
    private Integer repairPlan;


    @ApiModelProperty(value = "使用数量")
    private Integer useCount;


    @ApiModelProperty(value = "备品备件id")
    private Integer devicesSpareId;

    @ApiModelProperty(value = "备品备件名称")
    private String devicesSpareName;

    @ApiModelProperty(value = "备品备件编码")
    private String devicesSpareCode;

    @ApiModelProperty(value = "备品备件库存数量")
    private String devicesSpareStoreCount;
}
