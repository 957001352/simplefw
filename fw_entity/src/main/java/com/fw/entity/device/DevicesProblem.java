package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: dhlk_fw_plat
 * @description: 设备问题列表
 * @author: wqiang
 * @create: 2020-10-22 09:00
 **/
@Data
@ApiModel(value = "设备问题列表")
public class DevicesProblem {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "问题名称")
    private String name;

    @ApiModelProperty(value = "问题描述")
    private String content;

    @ApiModelProperty(value = "问题状态")
    private Integer status;

    @ApiModelProperty(value = "问题类型")
    private Integer type;



}
