package com.fw.entity.jbpm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 待审核任务
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "jbpmTask", description = "待审核任务")
public class JbpmTask implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 流程实例
     */
    @ApiModelProperty(value = "流程实例")
    private Integer executionId;
    /**
     * 待审核流程
     */
    @ApiModelProperty(value = "待审核流程")
    private Integer deploymentId;
    /**
     * 待审核节点
     */
    @ApiModelProperty(value = "待审核节点")
    private Integer deploypropId;
    /**
     * 待审核人
     */
    @ApiModelProperty(value = "待审核人")
    private String auditUser;

    /**
     * 数据对象Id
     */
    @ApiModelProperty(value = "数据对象Id")
    private String dataId;

    private String createTime;


}
