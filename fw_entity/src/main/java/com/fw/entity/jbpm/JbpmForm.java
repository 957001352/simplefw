package com.fw.entity.jbpm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程审核对象
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "jbpmForm", description = "流程审核对象")
public class JbpmForm implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 审核表单
     */
    @ApiModelProperty(value = "类编码")
    private String code;
    /**
     * 审核人
     */
    @ApiModelProperty(value = "表单名称")
    private String name;

    private String url;

}
