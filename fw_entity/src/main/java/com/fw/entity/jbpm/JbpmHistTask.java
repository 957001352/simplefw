package com.fw.entity.jbpm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 已审核流程节点
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "jbpmHistTask", description = "已审核流程节点")
public class JbpmHistTask implements Serializable {

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

    @ApiModelProperty(value = "审核结论")
    private Integer auditResult;
    /**
     * 待审核节点
     */
    @ApiModelProperty(value = "待审核节点")
    private String auditNode;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String auditIdea;
    /**
     * 待审核人
     */
    @ApiModelProperty(value = "待审核人")
    private Integer auditUser;
    /**
     * 审核时间
     */
    @ApiModelProperty(hidden = true,value = "审核时间")
    private String auditTime;
    /**
     * 待审核任务下发时间
     */
    @ApiModelProperty(hidden = true,value = "待审核任务下发时间")
    private String createTime;


    @ApiModelProperty(hidden = true,value = "待审核人")
    private String auditUserName;




}
