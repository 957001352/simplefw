package com.fw.entity.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: dhlk_fw_plat
 * @description: 叫料投料实体
 * @author: wqiang
 * @create: 2020-12-23 10:46
 **/

@Data
@ApiModel(value = "CallOrCastMaterial", description = "叫料投料实体")
public class CallOrCastMaterial implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备编码")
    private String productDeviceCode;

    @ApiModelProperty(value = "叫料时间")
    private String callTime;

    @ApiModelProperty(value = "叫料类型  1叫料 2投料")
    private Integer callType;

    @ApiModelProperty(value = "投料种型")
    private String castType;
}
