package com.fw.entity.logistics;

import com.fw.entity.e2c.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 入库
 * @author gchen
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsStoreHouse", description = "入库")
public class LogisticsStoreHouse {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String houseNo;

    /**
     * 入库类型 0-采购入库 1-委外生产入库 2-生产入库 3-其他入库
     */
    @ApiModelProperty(value = "入库类型 0-采购入库 1-委外生产入库 2-生产入库 3-其他入库")
    private String houseType;

    /**
     * 关联订单号
     */
    @ApiModelProperty(value = "关联订单号")
    private String orderNo;

    /**
     * 供应商名称
     */
    @ApiModelProperty(value = "供应商名称")
    private String providerName;

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
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private String storeDate;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;


    /**
     * 入库明细
     */
    @ApiModelProperty(value = "入库明细")
    private List<LogisticsStoreHouseDetail> logisticsStoreHouseDetailList;

    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private User user;

    /**
     * 零件类型 0 成品 1半成品 2 原材料
     */
    @ApiModelProperty(value = "零件类型")
    private Integer partsType;

    /**
     * 报工单id
     */
    @ApiModelProperty(value = "报工单id")
    private String moldingRecordIds;
}
