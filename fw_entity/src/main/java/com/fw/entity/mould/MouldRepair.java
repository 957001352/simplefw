package com.fw.entity.mould;

import com.fw.domain.BaseFile;
import com.fw.entity.device.DevicesRepairSpare;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: 模具维修单实体
 * @author: wqiang
 * @create: 2020-10-22 16:41
 **/
@Data
@ApiModel(value = "mouldRepair", description = "模具维修实体")
public class MouldRepair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "维修方案编码")
    private String code;

    @ApiModelProperty(value = "模具ID")
    private String mouldId;

    @ApiModelProperty(value = "模具编码")
    private String mouldCode;

    @ApiModelProperty(value = "模具名称")
    private String mouldName;

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "问题名称")
    private String questionName;

    @ApiModelProperty(value = "问题描述")
    private String questionDesc;

    @ApiModelProperty(value = "优先级")
    private String priority;

    @ApiModelProperty(value = "维修前照片")
    private String beforeFile;

    @ApiModelProperty(value = "申请人")
    private Integer createUser;

    @ApiModelProperty(value = "申请人姓名")
    private String createUserName;

    @ApiModelProperty(value = "申请时间")
    private String createTime;

    @ApiModelProperty(value = "设备维修项目")
    private String repairTeamIds;

    @ApiModelProperty(value = "设备维修项目名称")
    private String repairTeamName;

    @ApiModelProperty(value = "方案补充")
    private String addPlan;

    @ApiModelProperty(value = "方案补充附件")
    private String planFile;

    @ApiModelProperty(value = "'预计维修耗时'")
    private Double planHour;

    @ApiModelProperty(value = "维修后是否评审 0不评审 1要评审")
    private Integer isReview;

    @ApiModelProperty(value = "方案制定人")
    private Integer projectCreateUser;

    @ApiModelProperty(value = "方案制定人姓名")
    private String projectCreateUserName;

    @ApiModelProperty(value = "方案制定时间")
    private String projectCreateTime;

    @ApiModelProperty(value = "维修后照片")
    private String afterFile;

    @ApiModelProperty(value = "来源0PC端 1PAD端")
    private Integer source;

    @ApiModelProperty(value = "维修结果")
    private Integer status;

    @ApiModelProperty(value = "维修结果描述")
    private String result;

    @ApiModelProperty(value = "维修人员")
    private Integer repairUser;

    @ApiModelProperty(value = "维修人员姓名")
    private String repairUserName;


    @ApiModelProperty(value = "维修时间")
    private String repairTime;

    @ApiModelProperty(value = "维修开始时间")
    private String startTime;

    @ApiModelProperty(value = "维修结束时间")
    private String finishTime;

    @ApiModelProperty(value = "维修方案制定状态 0未制定 1已制定")
    private Integer repairProjectStatus;

    @ApiModelProperty(value = "维修任务执行状态 0未执行 1已执行")
    private Integer repairTaskExeStatus;

    @ApiModelProperty(value = "任务是否已接收 默认0 0未接收 1已接收")
    private Integer taskReceived;

    @ApiModelProperty(value = "型腔数")
    private Integer cavityNumber;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "模具维修使用备品备件")
    private List<MouldRepairSpare> mouldRepairSpareList;

    @ApiModelProperty(value = "维修方案附件")
    private List<BaseFile> planFileList;

    @ApiModelProperty(value = "预计维修完成时间 维修单创建时间+计划耗时")
    private String predictTime;
}
