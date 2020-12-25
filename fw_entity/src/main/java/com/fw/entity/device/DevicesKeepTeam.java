package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 保养项目组
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Data
@ApiModel(value = "devicesKeepTeam", description = "保养项目组")
public class DevicesKeepTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 保养项目组名称
     */
    @ApiModelProperty(value = "保养项目组名称")
    private String name;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private String devicesClassify;

    /**
     * 保养项目
     */
    @ApiModelProperty(value = "保养项目")
    private String keepItemIds;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private String productDevicesIds;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 保养项目对象
     */
    @ApiModelProperty(value = "保养项目对象", hidden = true)
    List<DevicesKeepItem> keepItems;

    /**
     * 保养项目名称
     */
    @ApiModelProperty(value = "保养项目名称", hidden = true)
    private String keepItemName;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", hidden = true)
    private String devicesName;


    /**
     * 设备类型名称
     */
    @ApiModelProperty(value = "设备类型名称", hidden = true)
    private String classifyName;

    /**
     * 删除状态 0正常 1删除
     */
    @ApiModelProperty(value = "删除状态", hidden = true)
    private Integer deleted;

    /**
     * 保养周期 0日保养，1周保养，2月保养，3季度保养，4年保养
     */
    @ApiModelProperty(value = "保养周期")
    private Integer cycle;
}
