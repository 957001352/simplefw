package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 注塑设备列表
 * @Author qwang
 * @Date 2020/11/5
 */
@Data
@ApiModel(value = "CraftModelProcess", description = "注塑设备列表")
public class CraftModelProcess implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "设备ID")
    private Integer devicesId;

    @ApiModelProperty(value = "设备编码")
    private String devicesCode;

    @ApiModelProperty(value = "设备名称")
    private String devicesName;

    @ApiModelProperty(value = "工艺卡片ID")
    private Integer cardId;

    @ApiModelProperty(value = "工艺卡片名称")
    private String cardName;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "注塑工艺模型ID")
    private Integer craftModelId;


}