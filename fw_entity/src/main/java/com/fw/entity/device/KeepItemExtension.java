package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 保养项目扩展
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Data
@ApiModel(value = "keepItemExtension", description = "保养项目扩展")
public class KeepItemExtension implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 保养项目
     */
    @ApiModelProperty(value = "保养项目", hidden = true)
    private Integer keepItemId;

    /**
     * 表头
     */
    @ApiModelProperty(value = "表头")
    private String columns;

    /**
     * 行数
     */
    @ApiModelProperty(value = "行数")
    private Integer rows;

    /**
     * 删除状态 0正常 1删除
     */
    @ApiModelProperty(value = "删除状态", hidden = true)
    private Integer deleted;
}
