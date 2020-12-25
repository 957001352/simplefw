package com.fw.entity.jbpm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 流程执行实例
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "jbpmExecution", description = "流程执行实例")
public class JbpmExecution implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 审核流程对象
     */
    @ApiModelProperty(value = "审核流程对象")
    private Integer deploymentId;

    @ApiModelProperty(hidden = true,value = "审核流程名称")
    private String deploymentName;
    /**
     * 审核单号
     */
    @ApiModelProperty(value = "审核单号")
    private String jbpmNo;
    /**
     * 审核数据
     */
    @ApiModelProperty(value = "审核数据")
    private String dataId;
    /**
     * 审核状态  审核完成时为xxx审核通过，未完成时为待xxx审核
     */
    @ApiModelProperty(hidden = true,value = "审核状态")
    private String auditStatus;


    @ApiModelProperty(hidden = true,value = "审核完成时间")
    private String endTime;

    /**
     * 添加时间
     */
    @ApiModelProperty(hidden = true,value = "添加时间")
    private String createTime;
    /**
     * 添加人
     */
    @ApiModelProperty(hidden = true,value = "添加人")
    private Integer createUser;
    @ApiModelProperty(hidden = true,value = "添加人姓名")
    private String createUserName;

    @ApiModelProperty(hidden = true,value = "已办任务列表")
    private List<JbpmHistTask> histTaskList;
    /**
    * 待审核流程节点
    */
    @ApiModelProperty(value = "待审核流程节点")
    private Integer deploypropId;

    /**
     * 审核结论  0待审核，1审核通过，2审核不通过
     */
    @ApiModelProperty(value = "审核结论")
    private Integer auditResult;

    @ApiModelProperty(value = "审核意见")
    private String aduitIdea;


    @ApiModelProperty(value = "审核对象")
    private String formCode;

    @ApiModelProperty(value = "任务分配时间")
    private String taskCreateTime;

    @ApiModelProperty(hidden = true,value = "表单地址")
    private String formUrl;

    public JbpmExecution() {

    }
    public JbpmExecution(Integer id, String dataId, Integer createUser, String formCode) {
        this.id = id;
        this.dataId = dataId;
        this.createUser = createUser;
        this.formCode = formCode;
    }
}
