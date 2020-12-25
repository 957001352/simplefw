package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 模具保养计划制定
 *
 * @author xkliu
 * @since 2020-10-27
 */
@Data
@ApiModel(value = "mouldKeepPlan", description = "模具保养计划制定")
public class MouldKeepPlan implements Serializable {

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
     * 模具保养项目组
     */
    @ApiModelProperty(value = "设备保养项目组")
    private Integer keepTeamId;

    /**
     * 模具id
     */
    @ApiModelProperty(value = "模具id")
    private String mouldId;

    /**
     * 模具编号
     */
    @ApiModelProperty(value = "模具编号")
    private String mouldDevicesIds;

    /**
     * 频率
     */
    @ApiModelProperty(value = "频率")
    private Integer keepCycle;

    /**
     * 保养周期
     */
    @ApiModelProperty(value = "保养周期")
    private Integer keepFre;
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
     * 上次保养时间
     */
    @ApiModelProperty(value = "上次保养时间", hidden = true)
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

    /**
     * 模具编号
     */
    @ApiModelProperty(value = "模具编号", hidden = true)
    private String code;

    /**
     * 模具初始开合模次数
     */
    @ApiModelProperty(value = "模具初始开合模次数", hidden = true)
    private Integer initCount;

    /**
     * 模具初始累计开合模次数
     */
    @ApiModelProperty(value = "模具初始累计开合模次数", hidden = true)
    private Integer totalCount;


}
