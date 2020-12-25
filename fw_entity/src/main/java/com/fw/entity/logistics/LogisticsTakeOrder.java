package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: 收货单
 * @author: wqiang
 * @create: 2020-11-12 14:53
 **/
@Data
@ApiModel(value = "LogisticsTakeOrder", description = "收货单")
public class LogisticsTakeOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "收货单号")
    private String taskNo;

    @ApiModelProperty(value = "源单单号")
    private String orderNo;


    @ApiModelProperty(value = "派单类型")
    private Integer kind;


    @ApiModelProperty(value = "状态 0待执行 1已执行 默认0")
    private Integer status;

    @ApiModelProperty(value = "采购日期")
    private String takeDate;

    @ApiModelProperty(value = "执行人")
    private Integer executeUser;

    @ApiModelProperty(value = "执行时间")
    private String executeTime;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "明细")
    private List<LogisticsTakeOrderDetail> TakeOrderDetailList;



}
