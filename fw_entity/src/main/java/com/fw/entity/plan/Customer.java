package com.fw.entity.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description排产客户管理
 * @Author lpsong
 * @Date 2020/11/26
 */
@Data
@ApiModel(value = "customer", description = "排产客户管理")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String name;
    /**
     * 状态 0 正常 1删除
     */
    @ApiModelProperty(value = "状态 0 正常 1删除", hidden = true)
    private Integer status;
}