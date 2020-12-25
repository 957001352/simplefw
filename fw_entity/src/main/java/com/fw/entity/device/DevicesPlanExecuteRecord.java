package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备保养计划下发任务
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Data
@ApiModel(value = "devicesPlanExecuteRecord", description = "设备保养计划下发任务")
public class DevicesPlanExecuteRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 设备保养任务对象
     */
    @ApiModelProperty(value = "设备保养任务对象")
    private Integer keepTaskId;

    /**
     * 设备保养项目
     */
    @ApiModelProperty(value = "设备保养项目")
    private Integer keepItemId;

    /**
     * 设备保养项目结果
     */
    @ApiModelProperty(value = "设备保养项目结果")
    private String keepItemResult;

    /**
     * 不合格原因
     */
    @ApiModelProperty(value = "不合格原因")
    private String reason;


}
