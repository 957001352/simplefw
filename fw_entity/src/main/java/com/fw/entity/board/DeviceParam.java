package com.fw.entity.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 生产设备管理
 */
@Data
@ApiModel(value="DeviceParam",description="设备参数信息")
public class DeviceParam implements Serializable {

    @ApiModelProperty(value = "设备运行状态")
    private Integer runStatus;// 状态  0:正在生产 1:待机 2:正常停机 3:在线保养 4:维修 5:停机保养 6:异常待处理
    @ApiModelProperty(value = "设备运行参数")
    private ProductParam productParam;
}
