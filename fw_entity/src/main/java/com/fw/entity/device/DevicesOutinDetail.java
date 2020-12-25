package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description: 出入库明细
 */
@Data
@ApiModel(value = "DevicesOutinDetail")
public class DevicesOutinDetail {

    /**
     * id
     */
    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;

    /**
     * 备品备件id
     */
    @ApiModelProperty(value = "备品备件id",hidden = true)
    private Integer devicesSpareId;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer useCount;

    /**
     * 库位
     */
    @ApiModelProperty(value = "库位",hidden = true)
    private String location;

    /**
     * 操作
     * 0：入库   1： 出库
     */
    @ApiModelProperty(value = "操作")
    private Integer operate;

    /**
     * 出入库对象
     */
    @ApiModelProperty(value = "出入库对象",hidden = true)
    private Integer outIn;


}
