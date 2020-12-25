package com.fw.entity.craft;

import com.fw.entity.e2c.DevicesItemVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 工艺卡参数
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "cardParams", description = "工艺卡参数")
public class CardParams implements Serializable {
    /**
     * Id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 工艺卡
     */
    @ApiModelProperty(value = "工艺卡")
    private Integer cardId;
    /**
     * 工艺卡编号
     */
    @ApiModelProperty(value = "工艺卡编号")
    private String code;
    /**
     * 零件编码
     */
    @ApiModelProperty(value = "零件编码")
    private String partCode;
    /**
     * 零件名称
     */
    @ApiModelProperty(value = "零件名称")
    private String partName;
    /**
     * 文档编号
     */
    @ApiModelProperty(value = "文档编号")
    private String documentNo;
    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private Integer productId;

    /**
     * 工艺参数
     */
    @ApiModelProperty(value = "工艺参数")
    private String craftData;

    /**
     * 添加人
     */
    @ApiModelProperty(hidden = true,value = "添加人")
    private Integer createUser;
    /**
     * 添加时间
     */
    @ApiModelProperty(hidden = true,value = "添加时间")
    private String createTime;
    /**
     * 变更时间
     */
    @ApiModelProperty(hidden = true,value = "变更时间")
    private String updateTime;
    /**
     * 变更人
     */
    @ApiModelProperty(hidden = true,value = "变更人")
    private Integer updateUser;
    /**
     * 变更类型  0 临时变更 1永久变更
     */
    @ApiModelProperty(value = "变更类型")
    private Integer updateType;
    /**
     * 核准时间
     */
    @ApiModelProperty(hidden = true,value = "核准时间")
    private String  approveTime;
    /**
     * 核准人
     */
    @ApiModelProperty(hidden = true,value = "核准人")
    private Integer approveUser;

    /**
     * 审核人
     */
    @ApiModelProperty(hidden = true,value = "审核人")
    private String auditorUser;
    /**
     * 状态  0 可用 1禁用
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 模具id
     */
    @ApiModelProperty(value = "模具id")
    private Integer mouldId;

    @ApiModelProperty(value = "设备")
    private DevicesItemVo devicesItemVo;

    @ApiModelProperty(value = "变更审核状态 0-未审核 1-正在审核中")
    private Integer auditStatus;

    @ApiModelProperty(value = "图片")
    private String picture;
}