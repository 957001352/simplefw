package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description: 备品备件出库
 */
@Data
@ApiModel(value = "DevicesSpareOut")
public class DevicesSpareOut {

    /**
     * id
     */
    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "出库单号")
    private String outNo;

    /**
     * 关联订单号
     */
    @ApiModelProperty(value = "关联订单号")
    private String orderNo;

    /**
     * 制单人
     */
    @ApiModelProperty(value = "制单人")
    private Integer createUser;

    /**
     * 制单时间
     */
    @ApiModelProperty(value = "制单时间",hidden = true)
    private String createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "备品备件加出入库明细列表")
    List<ProductDevicesSpare> productDevicesSparesAndOutInDetailList;

}
