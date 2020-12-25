package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备保养计划制定
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Data
@ApiModel(value = "devicesKeepPlan", description = "设备保养计划制定")
public class DevicesKeepPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 保养计划名称
     */
    @ApiModelProperty(value = "保养计划名称")
    private String name;

    /**
     * 设备保养项目组
     */
    @ApiModelProperty(value = "设备保养项目组")
    private Integer keepTeamId;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private String productDevicesIds;

    /**
     * 频率
     */
    @ApiModelProperty(value = "频率")
    private Integer keepCycle;

    /**
     * 初次保养时间
     */
    @ApiModelProperty(value = "初次保养时间")
    private String startTime;

    /**
     * 提前通知时间
     */
    @ApiModelProperty(value = "提前通知时间")
    private Integer noticeTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", hidden = true)
    private String devicesName;

    /**
     * 保养项目组名称
     */
    @ApiModelProperty(value = "保养项目组名称", hidden = true)
    private String keepTeamName;

    /**
     * 本次保养时间
     */
    @ApiModelProperty(value = "本次保养时间", hidden = true)
    private String lastTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer status;

    /**
     * 上次保养时间
     */
    @ApiModelProperty(value = "上次保养时间", hidden = true)
    private String previouTime;

    /**
     * 提前通知时间
     */
    @ApiModelProperty(value = "提前通知时间", hidden = true)
    private String advanceTime;


}
