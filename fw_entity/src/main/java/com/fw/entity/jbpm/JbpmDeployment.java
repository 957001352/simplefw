package com.fw.entity.jbpm;

import com.fw.entity.device.DevicesCheckDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 流程定义
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "jbpmDeployment", description = "流程定义")
public class JbpmDeployment implements Serializable {

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
    private Integer formId;


    @ApiModelProperty(hidden = true)
    private String formName;
    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称")
    private String name;
    /**
     * 添加时间
     */
    @ApiModelProperty(hidden = true,value = "添加时间")
    private String createTime;
    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人")
    private Integer createUser;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private String updateTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Integer updateUser;
    /**
     * 页面通知
     */
    @ApiModelProperty(value = "页面通知")
    private Integer messNotice;

    /**
     * 邮件通知
     */
    @ApiModelProperty(value = "邮件通知")
    private Integer emailNotice;
    /**
     * 修改人
     */
    @ApiModelProperty(hidden = true,value = "修改人")
    private String updateUserName;

    /**
     * 添加人
     */
    @ApiModelProperty(hidden = true,value = "添加人")
    private String createUserName;

    @ApiModelProperty(value = "流程节点")
    private List<JbpmDeployprop> noteList;



    @ApiModelProperty(hidden = true)
    private String formUrl;
}
