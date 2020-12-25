package com.fw.entity.produce;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 安排员工
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-08
 */
@Data
@ApiModel(value = "produceDuty", description = "安排员工")
public class ProduceDuty implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 操作工人
     */
    @ApiModelProperty(value = "操作工人")
    private Integer operateUser;

    @ApiModelProperty(value = "操作工人名称")
    private String operateUserName;

    /**
     * 指派人
     */
    @ApiModelProperty(value = "指派人")
    private Integer createUser;


    @ApiModelProperty(value = "指派人名称")
    private String createUserName;


    /**
     * 指派时间
     */
    @ApiModelProperty(value = "指派时间")
    private String  createTime;

    /**
     * 注塑排产计划
     */
    @ApiModelProperty(value = "注塑排产计划")
    private Integer planMoldingId;


    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "生产指令")
    private String productOrder;


    @ApiModelProperty(value = "设备ID",required = true)
    private Integer productDeviceId;


    @ApiModelProperty(value = "设备编码",required = true)
    private String productDeviceCode;

}
