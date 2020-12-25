package com.fw.entity.jbpm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程节点定义
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "jbpmDeployprop", description = "流程节点定义")
public class JbpmDeployprop implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 审核表单
     */
    @ApiModelProperty(value = "审核表单")
    private Integer deploymentId;
    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人")
    private String auditUsers;

    @ApiModelProperty(hidden = true,value = "审核人姓名")
    private String auditUserNames;

}
