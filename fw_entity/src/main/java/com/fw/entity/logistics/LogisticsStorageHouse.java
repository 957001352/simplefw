package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 物流仓库管理
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
@Data
@ApiModel(value = "logisticsStorageHouse", description = "物流仓库管理")
public class LogisticsStorageHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id")
    private Integer id;


    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "名称")
    private String name;


    /**
     * 状态 0 正常 1删除
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;

    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    private String allName;

    /**
     * 代码
     */
    @ApiModelProperty(value = "代码")
    private String code;


}
