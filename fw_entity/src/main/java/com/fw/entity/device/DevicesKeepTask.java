package com.fw.entity.device;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 设备保养计划下发任务
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Data
@ApiModel(value = "devicesKeepTask", description = "设备保养计划下发任务")
public class DevicesKeepTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间")
    private String executeTime;

    /**
     * 执行人
     */
    @ApiModelProperty(value = "执行人")
    private Integer executeUser;

    /**
     * 执行人名字
     */
    @ApiModelProperty(value = "执行人名字", hidden = true)
    private String executeUserName;

    /**
     * 任务状态 0未执行  1延期  2强制关闭  3已执行 4执行中
     */
    @ApiModelProperty(value = "任务状态")
    private String status;

    /**
     * 保养结论
     */
    @ApiModelProperty(value = "保养结论")
    private String conclusion;

    /**
     * 保养计划
     */
    @ApiModelProperty(value = "保养计划")
    private Integer keepPlanId;

    /**
     * 未执行原因
     */
    @ApiModelProperty(value = "未执行原因")
    private String nonExecution;

    /**
     * 未执行原因描述
     */
    @ApiModelProperty(value = "未执行原因描述")
    private String nonExecutCause;

    /**
     * 保养工单号
     */
    @ApiModelProperty(value = "保养工单号")
    private String keepOrder;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号", hidden = true)
    private String productDevicesIds;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", hidden = true)
    private String devicesName;

    /**
     * 延期天数
     */
    @ApiModelProperty(value = "延期天数", hidden = true)
    private String carryDay;

    /**
     * 表单编码
     */
    @ApiModelProperty(value = "表单编码", hidden = true)
    private String formCode;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态", hidden = true)
    private String auditStatus;

    /**
     * 审核结果
     */
    @ApiModelProperty(value = "审核结果", hidden = true)
    private Integer auditResult;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 预计结束时间
     */
    @ApiModelProperty(value = "预计结束时间")
    private String endTime;

    /**
     * 执行记录
     */
    @ApiModelProperty(value = "执行记录")
    private List<DevicesPlanExecuteRecord> planExecuteRecords;

    /**
     * 保养项目
     */
    @ApiModelProperty(value = "保养项目")
    private List<DevicesKeepItem> devicesKeepItem;

    /**
     * 流程id
     */
    @ApiModelProperty(value = "流程id", hidden = true)
    private Integer executionId;

    /**
     * 审批意见
     */
    @ApiModelProperty(value = "审批意见", hidden = true)
    private String auditIdea;

    /**
     * 审批人
     */
    @ApiModelProperty(value = "审批人", hidden = true)
    private Integer auditUser;

    /**
     * 审批人名字
     */
    @ApiModelProperty(value = "审批人名字", hidden = true)
    private String auditUserName;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间", hidden = true)
    private String auditTime;

    /**
     * 保养表单名称
     */
    @ApiModelProperty(value = "保养表单名称", hidden = true)
    private String keepTeamName;

    /**
     * 操作延后/关闭的时间
     */
    @ApiModelProperty(value = "操作延后/关闭的时间", hidden = true)
    private String delayTime;

    /**
     * 操作延后/关闭的人员
     */
    @ApiModelProperty(value = "操作延后/关闭的人员", hidden = true)
    private String delayUser;

    /**
     * 是否延期
     */
    @ApiModelProperty(value = "是否延期", hidden = true)
    private String isPostpone;

    /**
     * 保养时间
     */
    @ApiModelProperty(value = "保养时间", hidden = true)
    private String keepTime;

    /**
     * 预计开始时间
     */
    @ApiModelProperty(value = "预计开始时间")
    private String startTime;

    /**
     * 保养照片
     */
    @ApiModelProperty(value = "保养照片")
    private String keepPhoto;

    /**
     * 照片路径
     */
    @ApiModelProperty(value = "照片路径",hidden = true)
    private String path;

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id",hidden = true)
    private String devicesId;

    public DevicesKeepTask(String status, Integer keepPlanId, String keepOrder,String keepTime,String devicesId) {
        this.status = status;
        this.keepPlanId = keepPlanId;
        this.keepOrder = keepOrder;
        this.keepTime = keepTime;
        this.devicesId = devicesId;
    }

    public DevicesKeepTask() {
    }
}
