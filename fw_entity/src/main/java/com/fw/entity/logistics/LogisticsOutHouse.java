package com.fw.entity.logistics;

import com.fw.entity.e2c.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 出库
 * @author gchen
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsOutHouse", description = "出库")
public class LogisticsOutHouse {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
    private String houseNo;
    /**
     * 出库类型
     */
    @ApiModelProperty(value = "出库类型 0-销售出库 1-生产出库 2-委外加工出库 3-其他入库")
    private String houseType;
    /**
     * 销售单号
     */
    @ApiModelProperty(value = "销售单号")
    private String orderNo;
    /**
     * 制单时间
     */
    @ApiModelProperty(value = "制单时间")
    private String createTime;

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
     * 执行状态 0 未执行1已执行
     */
    @ApiModelProperty(value = "执行状态 0 未执行1已执行")
    private Integer status;
    /**
     * 出库日期
     */
    @ApiModelProperty(value = "出库日期")
    private String storeDate;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
    /**
     * 出库明细
     */
    @ApiModelProperty(hidden = true,value = "出库明细")
    private List<LogisticsOutHouseDetail> logisticsOutHouseDetailList;

    /**
     * 用户信息
     */
    @ApiModelProperty(hidden = true,value = "用户信息")
    private User user;

}
