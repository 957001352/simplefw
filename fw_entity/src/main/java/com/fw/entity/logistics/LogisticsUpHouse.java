package com.fw.entity.logistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 上架
 *
 * @author xkliu
 * @since 2020-11-10
 */
@Data
@ApiModel(value = "logisticsUpHouse", description = "上架")
public class LogisticsUpHouse {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 上架库位
     */
    @ApiModelProperty(value = "上架库位")
    private Integer storageLocationId;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private Integer createUser;

    /**
     * 操作人名字
     */
    @ApiModelProperty(value = "操作人名字")
    private String createUserName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private String createTime;

    /**
     * 库位编码
     */
    @ApiModelProperty(value = "库位编码", hidden = true)
    private String code;

    /**
     * 上架明细
     */
    @ApiModelProperty(value = "上架明细")
    List<LogisticsUpHouseDetail> logisticsUpHouseDetails;


}
