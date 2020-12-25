package com.fw.entity.produce;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 物料过程监控
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-15
 */
@Data
@ApiModel(value = "物料过程监控实体")
public class ProduceMaterialMonitor  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id 收货明细ID", hidden = true)
    private String id;

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
     * 收货人员
     */
    @ApiModelProperty(value = "收货人员")
    private String takeUser;

    /**
     * 收货时间
     */
    @ApiModelProperty(value = "收货时间")
    private String takeTime;

    /**
     * 检验人员
     */
    @ApiModelProperty(value = "检验人员")
    private String checkUser;

    /**
     * 检验时间
     */
    @ApiModelProperty(value = "检验时间")
    private String checkTime;

    /**
     * 入库人员
     */
    @ApiModelProperty(value = "入库人员")
    private String storeUser;

    /**
     * 入库时间
     */
    @ApiModelProperty(value = "入库时间")
    private String storeTime;


}
