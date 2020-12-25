package com.fw.entity.audit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 分层审核计划制定实体
 * @author: wqiang
 * @create: 2020-10-29 10:13
 **/
@Data
@ApiModel(value = "AuditPlan", description = "分层审核计划制定实体")
public class AuditPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "计划名称")
    private String name;

    @ApiModelProperty(value = "审核表单ID")
    private Integer auditTeamId;

    @ApiModelProperty(value = "审核表单名称")
    private String auditTeamName;

    @ApiModelProperty(value = "审核周期  0每天 1每周 2每月 3每季度 4每天")
    private String keepCycle;

    @ApiModelProperty(value = "cron表达式")
    private String cron;

    @ApiModelProperty(value = "审核人")
    private String auditUsers;

    @ApiModelProperty(value = "审核人名称")
    private String auditUsersName;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "状态 默认0 0可用 1禁用")
    private Integer status;

}
