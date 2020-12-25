package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 设备点检
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "devicesCheck", description = "设备点检")
public class DevicesCheck implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 检点工单号
     */
    @ApiModelProperty(value = "检点工单号")
    private String code;
    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productCode;
    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private Integer productDevicesId;
    /**
     * 点检项目组
     */
    @ApiModelProperty(value = "点检项目组")
    private String checkTeamIds;
    /**
     * 点检照片
     */
    @ApiModelProperty(value = "点检照片")
    private String checkFile;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true,value = "创建时间")
    private String createTime;
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
     * 执行状态
     */
    @ApiModelProperty(value = "执行状态")
    private Integer status;

    /**
     * 设备类型名称
     */
    @ApiModelProperty(hidden = true, value = "设备类型名称")
    private String classifyName;

    /**
     * 点检表单名称
     */
    @ApiModelProperty(hidden = true, value = "点检项目组名称")
    private String checkTeamNames;

    private String note;
    /**
     * 设备名称
     */
    @ApiModelProperty(hidden = true, value = "设备名称")
    private String productDevices;

    @ApiModelProperty(value = "点检项目")
    private List<DevicesCheckDetail> checkDetailList;
    @ApiModelProperty(hidden = true, value = "执行人姓名")
    private String executeUserName;

    @ApiModelProperty(hidden = true, value = "图片路径")
    private String imagePath;


}
