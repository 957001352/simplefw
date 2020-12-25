package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 盘库明细结果
 * @author lpsong
 * @since 2020-11-12
 */
@Data
@ApiModel(value = "logisticsCheckHouseDetail", description = "盘库明细结果")
public class LogisticsCheckHouseDetail {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 盘库对象
     */
    @ApiModelProperty(value = "盘库对象")
    private Integer checkHouseId;

    @ApiModelProperty(value = "盘点库位")
    private Integer storageId;
    /**
     * 库位名称
     */
    @ApiModelProperty(hidden = true,value = "库位名称")
    private String storageName;
    /**
     * 库位编码
     */
    @ApiModelProperty(hidden = true,value = "库位编码")
    private String storageCode;
    /**
     * 物料对象
     */
    @ApiModelProperty(value = "物料对象")
    private Integer productId;
    /**
     * 物料代码
     */
    @ApiModelProperty(value = "物料", hidden = true)
    private String productCode;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料", hidden = true)
    private String productName;
    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private String storageCount;
    /**
     * 盘点数量
     */
    @ApiModelProperty(value = "盘点数量")
    private String checkCount;
    /**
     * 执行状态 0 未执行 1已执行
     */
    @ApiModelProperty(value = "盘点结果 0 正常 1异常")
    private Integer checkResult;

    @ApiModelProperty(value = "仓库")
    private  String houseName;

}
