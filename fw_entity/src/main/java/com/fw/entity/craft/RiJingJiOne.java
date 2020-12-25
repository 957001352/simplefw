package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 日精机6008-注塑成型工艺卡
 * @Author gchen
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "riJingJiOne", description = "日精机6008-注塑成型工艺卡")
public class RiJingJiOne extends CardCommParams implements Serializable {
    /**
     * 料筒控制喷嘴
     */
    @ApiModelProperty(value = "料筒控制喷嘴")
    private String nozzle;
    /**
     * 料筒控制前部
     */
    @ApiModelProperty(value = "料筒控制前部")
    private String frontPart;
    /**
     * 料筒控制中部
     */
    @ApiModelProperty(value = "料筒控制中部")
    private String middlePart;
    /**
     * 料筒控制后部
     */
    @ApiModelProperty(value = "料筒控制后部")
    private String backPart;
    /**
     * 料筒控制CH5
     */
    @ApiModelProperty(value = "料筒控制CH5")
    private String chFive;
    /**
     * 料筒控制CH6
     */
    @ApiModelProperty(value = "料筒控制CH6")
    private String chSix;
    /**
     * 料筒控制CH7
     */
    @ApiModelProperty(value = "料筒控制CH7")
    private String chSeven;
    /**
     * 料筒控制CH8
     */
    @ApiModelProperty(value = "料筒控制CH8")
    private String chEight;
    /**
     * 作动油温度上限
     */
    @ApiModelProperty(value = "作动油温度上限")
    private String upperLimit;
    /**
     * 作动油温度目标
     */
    @ApiModelProperty(value = "作动油温度目标")
    private String target;
    /**
     * 作动油温度下限
     */
    @ApiModelProperty(value = "作动油温度下限")
    private String floorLimit;
    /**
     * 开模关模高速锁模速度
     */
    @ApiModelProperty(value = "开模关模高速锁模速度")
    private String highLockSpeed;
    /**
     * 开模关模低速锁模速度
     */
    @ApiModelProperty(value = "开模关模低速锁模速度")
    private String lowLockSpeed;
    /**
     * 开模关模低压锁模压力
     */
    @ApiModelProperty(value = "开模关模低压锁模压力")
    private String lowLockStress;
    /**
     * 开模关模高压锁模压力
     */
    @ApiModelProperty(value = "开模关模高压锁模压力")
    private String highLockStress;
    /**
     * 开模关模低速开模速度
     */
    @ApiModelProperty(value = "开模关模低速开模速度")
    private String lowOpenSpeed;
    /**
     * 开模关模高速开模速度
     */
    @ApiModelProperty(value = "开模关模高速开模速度")
    private String highOpenSpeed;
    /**
     * 开模关模初期开模速度
     */
    @ApiModelProperty(value = "开模关模初期开模速度")
    private String initOpenSpeed;
    /**
     * 开模关模初期开模时间
     */
    @ApiModelProperty(value = "开模关模初期开模时间")
    private String initOpenTime;
    /**
     * 开模关模模开暂停时间
     */
    @ApiModelProperty(value = "开模关模模开暂停时间")
    private String openStopTime;
    /**
     * 开模关模模开暂停SW
     */
    @ApiModelProperty(value = "开模关模模开暂停SW")
    private String openStopSw;
    /**
     * 开模关模模开暂停再启动
     */
    @ApiModelProperty(value = "开模关模模开暂停再启动")
    private String openStopStart;
    /**
     * 开模关模锁模异常时
     */
    @ApiModelProperty(value = "开模关模锁模异常时")
    private String lockError;
    /**
     * 推顶选择图形
     */
    @ApiModelProperty(value = "推顶选择图形")
    private String choiceGraph;
    /**
     * 推顶顶出压力
     */
    @ApiModelProperty(value = "推顶顶出压力")
    private String pushOutStress;
    /**
     * 推顶顶出EV1
     */
    @ApiModelProperty(value = "推顶顶出EV1")
    private String pushOutEvOne;
    /**
     * 推顶顶出EV2
     */
    @ApiModelProperty(value = "推顶顶出EV2")
    private String pushOutEvTwo;
    /**
     * 推顶顶出次数
     */
    @ApiModelProperty(value = "推顶顶出次数")
    private String pushOutEvCount;
    /**
     * 推顶开始TM
     */
    @ApiModelProperty(value = "推顶开始TM")
    private String startTm;
    /**
     * 推顶2速切换
     */
    @ApiModelProperty(value = "推顶2速切换")
    private String twoSpeedCut;
    /**
     * 推顶前进限
     */
    @ApiModelProperty(value = "推顶前进限")
    private String advanceLimit;
    /**
     * 推顶暂停
     */
    @ApiModelProperty(value = "推顶暂停")
    private String pause;
    /**
     * 推顶后退EV4
     */
    @ApiModelProperty(value = "推顶后退EV4")
    private String retreatEvFour;
    /**
     * 推顶后退压力
     */
    @ApiModelProperty(value = "推顶后退压力")
    private String retreatStress;
    /**
     * 推顶停止时间
     */
    @ApiModelProperty(value = "推顶停止时间")
    private String stopTime;
    /**
     * 推顶反复后退限
     */
    @ApiModelProperty(value = "推顶反复后退限")
    private String againRetreatLimit;
    /**
     * 推顶反复顶出EV3
     */
    @ApiModelProperty(value = "推顶反复顶出EV3")
    private String againPushOutEvThree;
    /**
     * 射出计量1射出TM
     */
    @ApiModelProperty(value = "射出计量1射出TM")
    private String shootOutTm;

    /**
     * 射出计量1冷却
     */
    @ApiModelProperty(value = "射出计量1冷却")
    private String cooling;
    /**
     * 射出计量1中间TM
     */
    @ApiModelProperty(value = "射出计量1中间TM")
    private String middleTm;
    /**
     * 射出计量1计量开始时间
     */
    @ApiModelProperty(value = "射出计量1计量开始时间")
    private String measureStartTime;
    /**
     * 射出计量1喷嘴后退时期
     */
    @ApiModelProperty(value = "射出计量1喷嘴后退时期")
    private String nozzleRetreatTime;
    /**
     * 射出计量1V5
     */
    @ApiModelProperty(value = "射出计量1V5")
    private String vFive;
    /**
     * 射出计量1V4
     */
    @ApiModelProperty(value = "射出计量1V4")
    private String vFour;
    /**
     * 射出计量1V3
     */
    @ApiModelProperty(value = "射出计量1V3")
    private String vThree;
    /**
     * 射出计量1V2
     */
    @ApiModelProperty(value = "射出计量1V2")
    private String vTwo;
    /**
     * 射出计量1V1
     */
    @ApiModelProperty(value = "射出计量1V1")
    private String vOne;
    /**
     * 射出计量1TP2
     */
    @ApiModelProperty(value = "射出计量1TP2")
    private String tpTwo;
    /**
     * 射出计量1S5
     */
    @ApiModelProperty(value = "射出计量1S5")
    private String sFive;
    /**
     * 射出计量1S4
     */
    @ApiModelProperty(value = "射出计量1S4")
    private String sFour;
    /**
     * 射出计量1S3
     */
    @ApiModelProperty(value = "射出计量1S3")
    private String sThree;
    /**
     * 射出计量1S2
     */
    @ApiModelProperty(value = "射出计量1S2")
    private String sTwo;
    /**
     * 射出计量1S1
     */
    @ApiModelProperty(value = "射出计量1S1")
    private String sOne;
    /**
     * 射出计量1SM
     */
    @ApiModelProperty(value = "射出计量1SM")
    private String sm;
    /**
     *射出计量1SD
     */
    @ApiModelProperty(value = "射出计量1SD")
    private String sd;
    /**
     * 射出计量1P3
     */
    @ApiModelProperty(value = "射出计量1P3")
    private String pThree;
    /**
     * 射出计量1P2
     */
    @ApiModelProperty(value = "射出计量1P2")
    private String pTwo;
    /**
     * 射出计量1P1
     */
    @ApiModelProperty(value = "射出计量1P1")
    private String pOne;
    /**
     * 射出计量1VS
     */
    @ApiModelProperty(value = "射出计量1VS")
    private String vs;
    /**
     * 射出计量1背压
     */
    @ApiModelProperty(value = "射出计量1背压")
    private String backStress;
    /**
     * 射出计量2过量注料防止
     */
    @ApiModelProperty(value = "射出计量2过量注料防止")
    private String excessInjectStop;
    /**
     * 射出计量2射出率提高
     */
    @ApiModelProperty(value = "射出计量2射出率提高")
    private String shootRateEnhance;
    /**
     *射出计量2压力切换位置
     */
    @ApiModelProperty(value = "射出计量2压力切换位置")
    private String stressCutSite;
    /**
     * 射出计量2压力
     */
    @ApiModelProperty(value = "射出计量2压力")
    private String stress;
    /**
     * 射出计量2位置
     */
    @ApiModelProperty(value = "射出计量2位置")
    private String site;
    /**
     * 射出计量2喷嘴接触成型
     */
    @ApiModelProperty(value = "射出计量2喷嘴接触成型")
    private String nozzleTouchMould;
    /**
     *射出计量2射出装置后退时间
     */
    @ApiModelProperty(value = "射出计量2射出装置后退时间")
    private String shootRetreatTime;
    /**
     * 监视幅度F1
     */
    @ApiModelProperty(value = "监视幅度F1")
    private String fOne;
    /**
     * 监视幅度F2
     */
    @ApiModelProperty(value = "监视幅度F2")
    private String fTwo;
    /**
     * 监视幅度良否判别
     */
    @ApiModelProperty(value = "监视幅度良否判别")
    private String goodNoJudge;
    /**
     * 监视幅度连续不良停止
     */
    @ApiModelProperty(value = "监视幅度连续不良停止")
    private String continuousNoStop;
    /**
     * 监视幅度强制不良数
     */
    @ApiModelProperty(value = "监视幅度强制不良数")
    private String forceNoCount;
    /**
     * 监视幅度M1
     */
    @ApiModelProperty(value = "监视幅度M1")
    private String mOne;
    /**
     * 监视幅度M2
     */
    @ApiModelProperty(value = "监视幅度M2")
    private String mTwo;
    /**
     * 8监视幅度
     */
    @ApiModelProperty(value = "8监视幅度")
    private String eightMonitorRange;
    /**
     *9监视幅度
     */
    @ApiModelProperty(value = "9监视幅度")
    private String nineMonitorRange;
    /**
     * 监视幅度射出终了位置
     */
    @ApiModelProperty(value = "监视幅度射出终了位置")
    private String shootEndSite;
    /**
     * 监视幅度10TM基准值
     */
    @ApiModelProperty(value = "监视幅度10TM基准值")
    private String tenTmBaseValue;
    /**
     * 监视幅度11TM基准值
     */
    @ApiModelProperty(value = "监视幅度11TM基准值")
    private String elevenTmBaseValue;
    /**
     * 监视幅度M3
     */
    @ApiModelProperty(value = "M3")
    private String mThree;
    /**
     * 监视幅度M3位置
     */
    @ApiModelProperty(value = "监视幅度M3位置")
    private String mThreeSite;
    /**
     *热流道 1
     */
    @ApiModelProperty(value = "热流道 1")
    private String hotRunnerOne;
    /**
     *热流道 2
     */
    @ApiModelProperty(value = "热流道 2")
    private String hotRunnerTwo;
    /**
     *热流道 3
     */
    @ApiModelProperty(value = "热流道 3")
    private String hotRunnerThree;
    /**
     *热流道 4
     */
    @ApiModelProperty(value = "热流道 4")
    private String hotRunnerFour;
    /**
     *热流道 5
     */
    @ApiModelProperty(value = "热流道 5")
    private String hotRunnerFive;
    /**
     *热流道 6
     */
    @ApiModelProperty(value = "热流道 6")
    private String hotRunnerSix;
    /**
     *热流道 7
     */
    @ApiModelProperty(value = "热流道 7")
    private String hotRunnerSeven;
    /**
     *热流道 8
     */
    @ApiModelProperty(value = "热流道 8")
    private String hotRunnerEight;
    /**
     *备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}