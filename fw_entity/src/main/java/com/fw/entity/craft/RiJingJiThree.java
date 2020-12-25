package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 日精机18020-注塑成型工艺卡
 * @Author gchen
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "riJingJiTwo", description = "日精机18020-注塑成型工艺卡")
public class RiJingJiThree extends CardCommParams implements Serializable {
    /**
     * 射出
     */
    @ApiModelProperty(value = "射出")
    private String shoot;
    /**
     * 冷却
     */
    @ApiModelProperty(value = "冷却")
    private String cooling;
    /**
     * 中间
     */
    @ApiModelProperty(value = "中间")
    private String centre;
    /**
     * 充填速度V1
     */
    @ApiModelProperty(value = "充填速度V1")
    private String vOne;
    /**
     * 充填速度V2
     */
    @ApiModelProperty(value = "充填速度V2")
    private String vTwo;
    /**
     * 充填速度V3
     */
    @ApiModelProperty(value = "充填速度V3")
    private String vThree;
    /**
     * 充填速度V4
     */
    @ApiModelProperty(value = "充填速度V4")
    private String vFour;
    /**
     * 充填速度V5
     */
    @ApiModelProperty(value = "充填速度V5")
    private String vFive;
    /**
     * 充填速度V6
     */
    @ApiModelProperty(value = "充填速度V6")
    private String vSix;
    /**
     * 充填速度SV1
     */
    @ApiModelProperty(value = "充填速度SV1")
    private String svOne;
    /**
     * 充填速度SV2
     */
    @ApiModelProperty(value = "充填速度SV2")
    private String svTwo;
    /**
     * 充填速度SV3
     */
    @ApiModelProperty(value = "充填速度SV3")
    private String svThree;
    /**
     * 充填速度SV4
     */
    @ApiModelProperty(value = "充填速度SV4")
    private String svFour;
    /**
     * 充填速度SV5
     */
    @ApiModelProperty(value = "充填速度SV5")
    private String svFive;
    /**
     * 合模 高速锁模速度
     */
    @ApiModelProperty(value = "合模 高速锁模速度")
    private String highLockSpeed;
    /**
     * 合模 低速锁模速度
     */
    @ApiModelProperty(value = "合模 低速锁模速度")
    private String lowLockSpeed;
    /**
     * 合模 低速低压位置
     */
    @ApiModelProperty(value = "合模 低速低压位置")
    private String lowSpeedPlace;
    /**
     * 合模 高速位置
     */
    @ApiModelProperty(value = "合模 高速位置")
    private String highSpeedPlace;
    /**
     * 合模 低压
     */
    @ApiModelProperty(value = "合模 低压")
    private String lowStress;
    /**
     * 合模 高压
     */
    @ApiModelProperty(value = "合模 高压")
    private String highStress;
    /**
     * 开模 终期慢速
     */
    @ApiModelProperty(value = "开模 终期慢速")
    private String endSlowSpeed;
    /**
     * 开模 高速1
     */
    @ApiModelProperty(value = "开模 高速1")
    private String highSpeed;
    /**
     * 开模 初期开模速度
     */
    @ApiModelProperty(value = "开模 初期开模速度")
    private String initOpenSpeed;
    /**
     * 开模 开模停止
     */
    @ApiModelProperty(value = "开模 开模停止")
    private String openStop;
    /**
     * 开模 慢速距离
     */
    @ApiModelProperty(value = "开模 慢速距离")
    private String slowSpeedSpace;
    /**
     * 开模 高速1始
     */
    @ApiModelProperty(value = "开模 高速1始")
    private String highSpeedOne;
    /**
     * 保压 Pp1
     */
    @ApiModelProperty(value = "保压 Pp1")
    private String ppOne;
    /**
     * 保压 Pp2
     */
    @ApiModelProperty(value = "保压 Pp2")
    private String ppTwo;
    /**
     * 保压 Pp3
     */
    @ApiModelProperty(value = "保压 Pp3")
    private String ppThree;
    /**
     * 保压 Tp1
     */
    @ApiModelProperty(value = "保压 Tp1")
    private String tpOne;
    /**
     * 保压 Tp2
     */
    @ApiModelProperty(value = "保压 Tp2")
    private String tpTwo;
    /**
     * 限度 Pv1
     */
    @ApiModelProperty(value = "限度 Pv1")
    private String pvOne;
    /**
     * 限度 Pv2
     */
    @ApiModelProperty(value = "限度 Pv2")
    private String pvTwo;
    /**
     * 限度 Pv3
     */
    @ApiModelProperty(value = "限度 Pv3")
    private String pvThree;
    /**
     * 限度 Sp1
     */
    @ApiModelProperty(value = "限度 Sp1")
    private String spOne;
    /**
     * 限度 Sp2
     */
    @ApiModelProperty(value = "限度 Sp2")
    private String spTwo;
    /**
     * 推顶 前进限度
     */
    @ApiModelProperty(value = "限度 前进限度")
    private String advanceLimit;
    /**
     * 位置V-P切换
     */
    @ApiModelProperty(value = "位置V-P切换")
    private String siteVpCut;
    /**
     * 计量 SD
     */
    @ApiModelProperty(value = "计量 SD")
    private String sd;
    /**
     * 计量 SM
     */
    @ApiModelProperty(value = "计量 SM")
    private String sm;
    /**
     * 计量 VS
     */
    @ApiModelProperty(value = "计量 VS")
    private String vs;
    /**
     * 计量 BP
     */
    @ApiModelProperty(value = "计量 BP")
    private String bp;
    /**
     * 温度 喷嘴
     */
    @ApiModelProperty(value = "温度 喷嘴")
    private String nozzle;
    /**
     * 温度 中部
     */
    @ApiModelProperty(value = "温度 中部")
    private String middlePart;
    /**
     * 温度 前部
     */
    @ApiModelProperty(value = "温度 前部")
    private String frontPart;
    /**
     * 温度 后部1
     */
    @ApiModelProperty(value = "温度 后部1")
    private String backPartOne;
    /**
     * 温度 后部2
     */
    @ApiModelProperty(value = "温度 后部2")
    private String backPartTwo;
    /**
     * 温度 备注
     */
    @ApiModelProperty(value = "温度 备注")
    private String remark;
}