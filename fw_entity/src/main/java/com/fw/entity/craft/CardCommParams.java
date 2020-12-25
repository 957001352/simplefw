package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 工艺卡左边公共参数
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "cardCommParams", description = "工艺卡左边公共参数")
public class CardCommParams implements Serializable {
    /**
     * 零件净重
     */
    @ApiModelProperty(value = "零件净重")
    private String netWeight;
    /**
     * 料头重量
     */
    @ApiModelProperty(value = "料头重量")
    private String sprueWeight;
    /**
     * 每模重量
     */
    @ApiModelProperty(value = "每模重量")
    private String perWeight;
    /**
     * 成型周期
     */
    @ApiModelProperty(value = "成型周期")
    private String cycleTime;
    /**
     * 材料名称
     */
    @ApiModelProperty(value = "材料名称")
    private String designation;
    /**
     * 干燥方法
     */
    @ApiModelProperty(value = "干燥方法")
    private String dryingMethod;
    /**
     * 干燥温度
     */
    @ApiModelProperty(value = "干燥温度")
    private String dryingTemp;
    /**
     * 干燥时间
     */
    @ApiModelProperty(value = "干燥时间")
    private String dryingTime;
    /**
     * 模具编号
     */
    @ApiModelProperty(value = "模具编号")
    private String mouldNumber;
    /**
     * 模腔取数
     */
    @ApiModelProperty(value = "模腔取数")
    private String nbCavity;
    /**
     * 浇口类型
     */
    @ApiModelProperty(value = "浇口类型")
    private String gateType;
    /**
     * 动模温度
     */
    @ApiModelProperty(value = "动模温度")
    private String fixedPlate;
    /**
     * 定模温度
     */
    @ApiModelProperty(value = "定模温度")
    private String movePlate;

    private String partName;
    private String partCode;
    private String documentNo;
    private String productId;
    private String approveUser;
    private String createUser;
    private String auditorUser;
}