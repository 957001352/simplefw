package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备点检结果明细
 * @author lpsong
 * @since 2020-10-21
 */
@Data
@ApiModel(value = "devicesCheckDetail", description = "设备点检结果明细")
public class DevicesCheckDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 设备保养任务对象
     */
    @ApiModelProperty(value = "设备点检对象")
    private Integer checkRecordId;
    /**
     * 设备保养项目
     */
    @ApiModelProperty(value = "设备点检表单",hidden = true)
    private String checkTeamId;
    /**
     * 设备保养项目
     */
    @ApiModelProperty(value = "设备点检项目")
    private Integer checkItemId;

    /**
     * 设备保养项目结果
     */
    @ApiModelProperty(value = "设备点检项目结果")
    private Integer checkItemResult;

    @ApiModelProperty(value = "点检不合格原因")
    private String reason;

    @ApiModelProperty(hidden = true,value = "设备点检项目")
    private String checkItemName;


}
