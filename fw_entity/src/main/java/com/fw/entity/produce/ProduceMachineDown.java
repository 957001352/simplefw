package com.fw.entity.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 生产停机记录
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
@Data
@ApiModel(value = "生产停机记录")
public class ProduceMachineDown implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 注塑排产计划
     */

    @ApiModelProperty(value = "注塑排产计划")
    private Integer planMoldingId;

    /**
     * 生产设备
     */
    @ApiModelProperty(value = "生产设备")
    private Integer productDevicesId;


    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String ofNo;
    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码")
    private String productDevicesCode;

    /**
     * 设备编码
     */
    @ApiModelProperty(value = "采集设备编码")
    private String realProductDevicesCode;

    /**
     * 状态 0停机
     */
    @ApiModelProperty(value = "状态 0停机")
    private Integer status;

    /**
     * 停机原因
     */
    @ApiModelProperty(value = "停机原因")
    private String reason;

    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人")
    private Integer createUser;


    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人名称")
    private String createUserName;


    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private String createTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;


}
