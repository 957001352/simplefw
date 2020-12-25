package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description: 备品备件
 */

@Data
@ApiModel(value = "ProductDevicesSpare")
public class ProductDevicesSpare {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 物料代码
     */
    @ApiModelProperty(value = "物料代码")
    private String code;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String name;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 最大安全库存
     */
    @ApiModelProperty(value = "最大安全库存")
    private Integer maxStore;

    /**
     * 最小安全库存
     */
    @ApiModelProperty(value = "最小安全库存")
    private Integer minStore;

    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人")
    private Integer createUser;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间", hidden = true)
    private String createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;

    /**
     * 库位
     */
    @ApiModelProperty(value = "库位")
    private String location;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private Integer storeCount;

    /**
     * 出入库明细
     */
    @ApiModelProperty(value = "出入库明细")
    private DevicesOutinDetail devicesOutinDetail;

}
