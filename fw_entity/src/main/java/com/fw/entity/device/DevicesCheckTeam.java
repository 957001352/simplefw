package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备点检项目组
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "devicesCheckTeam", description = "设备点检项目组")
public class DevicesCheckTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 保养项目组名称
     */
    @ApiModelProperty(value = "点检项目组名称")
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
    private String checkItemIds;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private String productDevicesIds;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    /**
     * 设备类型名称
     */
    @ApiModelProperty(hidden = true,value = "设备类型名称")
    private String classifyName;

    /**
     * 点检项目名称
     */
    @ApiModelProperty(hidden = true,value = "点检项目名称")
    private String checkItemNames;
    /**
     * 设备名称
     */
    @ApiModelProperty(hidden = true,value = "设备名称")
    private String productDevices;

    /**
     * 删除状态
     */
    @ApiModelProperty(hidden = true,value = "删除状态")
    private Integer deleted;
}
