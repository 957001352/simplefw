package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 模具出入库明细
 * @Author: fchai
 * @Date: 2020/10/26 14:34
 */
@Data
public class MouldStorageOutInDetail {
    /**
     * id
     */
    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;

    /**
     * 备品备件id
     */
    @ApiModelProperty(value = "备品备件id")
    private Integer devicesSpareId;

    /**
     * 备品备件名称
     */
    @ApiModelProperty(value = "备品备件名称")
    private String devicesSpareName;

    /**
     * 备品备件编码
     */
    @ApiModelProperty(value = "备品备件编码")
    private String spareCode;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer useCount;

    /**
     * 库位
     */
    @ApiModelProperty(value = "库位")
    private Integer location;

    /**
     * 操作
     * 0：入库   1： 出库
     */
    @ApiModelProperty(value = "操作")
    private Integer operate;

    /**
     * 出入库对象
     */
    @ApiModelProperty(value = "出入库对象")
    private Integer outIn;
}
