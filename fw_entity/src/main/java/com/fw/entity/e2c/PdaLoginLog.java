package com.fw.entity.e2c;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * PDA登录日志
 */
@Data
@ApiModel(value="PdaLoginLog",description="PDA登录日志实体")
public class PdaLoginLog implements Serializable {
    @ApiModelProperty(value="新增为空/修改传值")
    private Integer id;
    /** 用户 */
    @ApiModelProperty(value="用户")
    private Integer userId;

    /** 登陆时间 */
    @ApiModelProperty(value="登陆时间")
    private String loginTime;

    /** 退出时间 */
    @ApiModelProperty(value="退出时间")
    private String exitTime;

    /**
     * 登陆设备id
     */
    @ApiModelProperty(value = "登陆设备id",hidden = true)
    private Integer productDevicesId;

    /**
     * 登陆设备编号
     */
    @ApiModelProperty(value = "登陆设备编号",hidden = true)
    private String productDeviceCode;

}


