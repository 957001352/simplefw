package com.fw.entity.mould;

import com.fw.entity.e2c.DevicesItemVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 模具使用实体
 * @Author: gchen
 * @CreateDate: 2020/10/27 16:47
 */
@Data
@ApiModel(value = "mouldUseRecord", description = "模具使用实体")
public class MouldUseRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "设备")
    private Integer productDevicesId;                       //'设备'
    @ApiModelProperty(value = "模具")
    private Integer mouldDevicesId;    	                    //'模具'
    @ApiModelProperty(value = "生产指令")
    private String productCode;   		               //'生产指令'
    @ApiModelProperty(value = "创建时间")
    private String createTime;  		                 //'创建时间'
    @ApiModelProperty(value = "操作 0 上模 1下模")
    private Integer opreate;           	                    //'操作 0 上模 1下模'
    @ApiModelProperty(value = "尾模照片")
    private String endFile;           	               //'尾模照片'
    @ApiModelProperty(value = "任务状态 0 待上模 1 待下模 2 上模完成 3 下模完成")
    private Integer taskStatus;           	                    //'任务状态 0 待上模 1 待下模 2 上模完成 3 下模完成'
    @ApiModelProperty(value = "状态 0 未完成 1 已完成")
    private Integer status;           	                    //'状态 0 未完成 1 已完成'

    @ApiModelProperty(value = "模具编号")
    private String mouldCode;           	                    //模具编号
    @ApiModelProperty(value = "模具名称")
    private String mouldName;           	                    //模具名称

    @ApiModelProperty(value = "设备信息")
    private String devicesCode;

    @ApiModelProperty(value = "模具id")
    private Integer mouldId;

    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "生产指令id")
    private Integer injectionMoldingId;
}
