package com.fw.entity.mould;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模具保养项目组
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Data
@ApiModel(value = "mouldKeepTeam", description = "模具保养项目组")
public class MouldKeepTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 保养项目组名称
     */
    @ApiModelProperty(value = "保养项目组名称")
    private String name;

    /**
     * 保养项目
     */
    @ApiModelProperty(value = "保养项目")
    private String keepItemIds;

    /**
     * 模具编号
     */
    @ApiModelProperty(value = "模具编号")
    private String mouldDevicesIds;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 保养项目对象
     */
    @ApiModelProperty(value = "保养项目对象", hidden = true)
    List<MouldKeepItem> keepItems;

    /**
     * 保养项目名称
     */
    @ApiModelProperty(value = "保养项目名称", hidden = true)
    private String keepItemName;

    /**
     * 模具编号
     */
    @ApiModelProperty(value = "模具编号", hidden = true)
    private String code;

    /**
     * 删除状态 0正常 1删除
     */
    @ApiModelProperty(value = "删除状态", hidden = true)
    private Integer deleted;

    /**
     * 保养类型
     */
    @ApiModelProperty(value = "保养类型")
    private Integer keepType;

    /**
     * 保养周期 0日保养，1周保养，2月保养，3季度保养，4年保养
     */
    @ApiModelProperty(value = "保养周期")
    private Integer cycle;

}
