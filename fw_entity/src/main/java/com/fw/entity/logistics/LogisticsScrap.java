package com.fw.entity.logistics;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

/**
 * <p>
 * 报废管理
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-16
 */
@Data
@ApiModel(value = "报废管理")
public class LogisticsScrap implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;


    /**
     * 物料ID
     */
    @ApiModelProperty(value = "物料ID")
    private Integer productId;

    /**
     * 物料编码
     */
    @ApiModelProperty(value = "物料编码")
    private String productCode;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称")
    private String productName;

    /**
     * 生产设备ID
     */
    @ApiModelProperty(value = "生产设备ID")
    private Integer productDevicesId;

    /**
     * 生产设备编码
     */
    @ApiModelProperty(value = "生产设备编码")
    private String productDevicesCode;

    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productOrder;

    /**
     * 报废数量
     */
    @ApiModelProperty(value = "报废数量")
    private Integer scrapCoun;

    /**
     * 报废原因
     */
    @ApiModelProperty(value = "报废原因")
    private String reason;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人Id")
    private Integer createUser;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createUserName;
    /**
     * 创建时间
     */
    private String createTime;


}
