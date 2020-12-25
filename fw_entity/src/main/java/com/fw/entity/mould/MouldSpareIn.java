package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description: 模具备品备件入库
 * @Author: fchai
 * @Date: 2020/10/26 14:31
 */
@Data
@ApiModel(value = "MouldSpareIn")
public class MouldSpareIn {
    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String inNo;

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
     * 制单人
     */
    @ApiModelProperty(value = "制单人名称")
    private String createUserName;

    /**
     * 制单时间
     */
    @ApiModelProperty(value = "制单时间", hidden = true)
    private String createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 入库备品备件列表
     */
    List<MouldStorageOutInDetail> storageOutInDetailList;
}
