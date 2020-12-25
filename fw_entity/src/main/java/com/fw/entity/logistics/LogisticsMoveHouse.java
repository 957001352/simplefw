package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 移库单
 *
 * @author wangqiang
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "LogisticsMoveHouse", description = "移库单")
public class LogisticsMoveHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 移库单号
     */
    @ApiModelProperty(value = "移库单号")
    private String moveHouseNo;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private Integer createUser;

    @ApiModelProperty(value = "操作人名称")
    private String createUserName;


    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private String createTime;

    /**
     * 执行状态
     */
    @ApiModelProperty(value = "状态 0待执行 1已执行")
    private Integer status;


    /**
     * 操作人
     */
    @ApiModelProperty(value = "执行人")
    private Integer executeUser;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "执行时间")
    private String executeTime;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;


    @ApiModelProperty(value = "移库物料明细")
    private List<LogisticsMoveHouseDetail>  logisticsMoveHouseDetailsList;


}
