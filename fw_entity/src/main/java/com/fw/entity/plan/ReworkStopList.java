package com.fw.entity.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 二次加工排产暂停列表
 * @author lpsong
 * @since 2020-11-25
 */
@Data
@ApiModel(value = "reworkStopList", description = "二次加工排产暂停列表")
public class ReworkStopList implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 注塑排产计划对象
     */
    @ApiModelProperty(value = "二次加工排产计划对象")
    private Integer injectionId;
    /**
     * 暂停时长
     */
    @ApiModelProperty(value = "暂停时长")
    private Double stopTime;
    /**
     * 暂停原因
     */
    @ApiModelProperty(value = "暂停原因")
    private String stopReason;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人", hidden = true)
    private Integer createUser;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", hidden = true)
    private String createTime;

}
