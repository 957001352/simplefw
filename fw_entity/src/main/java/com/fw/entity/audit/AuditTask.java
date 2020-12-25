package com.fw.entity.audit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: 分层审核任务实体
 * @author: wqiang
 * @create: 2020-10-29 10:13
 **/
@Data
@ApiModel(value = "AuditTask", description = "分层审核任务实体")
public class AuditTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "任务单号")
    private String taskNo;

    @ApiModelProperty(value = "执行时间")
    private String executeTime;

    @ApiModelProperty(value = "执行人")
    private Integer executeUser;

    @ApiModelProperty(value = "执行人")
    private Integer executeUserName;

    @ApiModelProperty(value = "执行状态 默认0 0未执行 1已执行")
    private Integer status;

    @ApiModelProperty(value = "审核计划ID")
    private Integer auditPlanId;

    @ApiModelProperty(value = "审核表单ID")
    private Integer auditTeamId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "备注")
    private String note;

    private List<AuditItemDetail> auditItemDetailList;
}
