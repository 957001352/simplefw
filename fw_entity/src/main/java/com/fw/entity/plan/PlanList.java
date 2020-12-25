package com.fw.entity.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @Description:    排产列表
* @Author:         gchen
* @CreateDate:     2020/11/25 14:39
*/
@Data
@ApiModel(value = "planList", description = "排产列表")
public class PlanList {
    @ApiModelProperty(value = "设备类型 0:模具 1:设备")
    private Integer deviceType;
    @ApiModelProperty(value = "设备id")
    private Integer deviceId;
    @ApiModelProperty(value = "设备编号")
    private String deviceCode;
    @ApiModelProperty(value = "最早可用时间")
    private String useTime;
    @ApiModelProperty(value = "最早结束时间")
    private String endTime;
    @ApiModelProperty(value = "生产指令的生产时间")
    private Integer productTime;
}
