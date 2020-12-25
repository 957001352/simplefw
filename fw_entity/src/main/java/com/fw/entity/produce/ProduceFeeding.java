package com.fw.entity.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 投料
 *
 * @author xkliu
 * @since 2020-12-14
 */
@Data
@ApiModel(value = "produceFeeding", description = "投料")
public class ProduceFeeding {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 领料单号
     */
    @ApiModelProperty(value = "领料单号")
    private String feedNo;

    /**
     * 制单人
     */
    @ApiModelProperty(value = "制单人", hidden = true)
    private Integer createUser;

    /**
     * 制单人名字
     */
    @ApiModelProperty(value = "制单人名字", hidden = true)
    private String createUserName;

    /**
     * 生产指令
     */
    @ApiModelProperty(value = "生产指令")
    private String productOrder;

    /**
     * 生产设备
     */
    @ApiModelProperty(value = "生产设备")
    private Integer productDevicesId;

    /**
     * 生产名称
     */
    @ApiModelProperty(value = "生产名称",hidden = true)
    private String productDevicesCode;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String note;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 投料对象集合
     */
    @ApiModelProperty(value = "投料对象集合")
    private List<ProduceFeedingDetail> produceFeedingDetails;

}
