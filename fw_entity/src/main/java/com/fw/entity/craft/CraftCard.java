package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 工艺卡管理
 * @Author gchen
 * @Date 2020/11/6
 */
@Data
@ApiModel(value = "craftCard", description = "工艺卡管理")
public class CraftCard implements Serializable {
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 工艺卡名称
     */
    @ApiModelProperty(value = "工艺卡名称")
    private String name;
    /**
     * 工艺卡编号
     */
    @ApiModelProperty(value = "工艺卡编号")
    private String code;

    /**
     * 工艺卡参数
     */
    @ApiModelProperty(value = "工艺卡参数")
    private String picture;
}