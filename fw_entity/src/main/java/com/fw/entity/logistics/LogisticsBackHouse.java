package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.*;


/**
 * 退库
 *
 * @author xkliu
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsBackHouse", description = "退库")
public class LogisticsBackHouse {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 退库单号
     */
    @ApiModelProperty(value = "退库单号")
    private String houseNo;

    /**
     * 制单时间
     */
    @ApiModelProperty(value = "制单时间", hidden = true)
    private String createTime;

    /**
     * 执行状态
     */
    @ApiModelProperty(value = "执行状态")
    private Integer status;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间")
    private String executeTime;

    /**
     * 执行人
     */
    @ApiModelProperty(value = "执行人")
    private Integer executeUser;

    /**
     * 执行人名字
     */
    @ApiModelProperty(value = "执行人名字",hidden = true)
    private String executeUserName;

    /**
     * 退库日期
     */
    @ApiModelProperty(value = "退库日期", hidden = true)
    private String sotreDate;

    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productCode;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 物料代码
     */
    @ApiModelProperty(value = "物料代码")
    private String code;

    /**
     * 生产设备ID
     */
    @ApiModelProperty(value = "生产设备ID")
    private Integer productDevicesId;

    /**
     * 生产设备名称
     */
    @ApiModelProperty(value = "生产设备名称")
    private String productDevicesName;

    /**
     * 退库明细
     */
    @ApiModelProperty(value = "退库明细")
    private List<LogisticsBackHouseDetail>  logisticsBackHouseDetails;

}
