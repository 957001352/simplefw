package com.fw.entity.produce;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 生产报工记录
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
@Data
@ApiModel(value = "ProduceMoldingRecord", description = "不良上报")
public class ProduceMoldingRecord implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 注塑排产计划
     */
    @ApiModelProperty(value = "注塑排产计划")
    private Integer planMoldingId;

    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人")
    private Integer createUser;

    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人名称")
    private String createUserName;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private String createTime;

    /**
     * 本箱合格数量
     */
    @ApiModelProperty(value = "本箱合格数量")
    private Integer qualified;

    /**
     * 本箱不合格数量
     */
    @ApiModelProperty(value = "本箱不合格数量")
    private Integer unqualified;

    /**
     * 设备id
     */
    private Integer productDevicesId;

    /**
     * 设备编码
     */
    private String deviceCode;

    /**
     * 模具编号
     */
    private String mouldCode;

    /**
     * 模具名称
     */
    private String mouldName;


    /**
     * 生产指令
     */
    private String ofNo;

    /**
     * 零件代码
     */
    private String productCode;

    /**
     * 0:未入库  1:已入库
     */
    private Integer bankStatus;


}
