package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 硅胶机-注塑成型工艺卡
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "guiJiaoJiCard", description = "硅胶机-注塑成型工艺卡")
public class GuiJiaoJiCard implements Serializable {
    /**----------------表头---------------**/
    /**
     * 上模温度1
     */
    @ApiModelProperty(value = "上模温度1")
    private String upTempOne;
    /**
     * 上模温度2
     */
    @ApiModelProperty(value = "上模温度2")
    private String upTempTwo;
    /**
     * 上模温度3
     */
    @ApiModelProperty(value = "上模温度3")
    private String upTempThree;
    /**
     * 上模温度4
     */
    @ApiModelProperty(value = "上模温度4")
    private String upTempFour;
    /**
     * 下模温度1
     */
    @ApiModelProperty(value = "下模温度1")
    private String downTempOne;
    /**
     * 下模温度2
     */
    @ApiModelProperty(value = "下模温度2")
    private String downTempTwo;
    /**
     * 下模温度3
     */
    @ApiModelProperty(value = "下模温度3")
    private String downTempThree;
    /**
     * 下模温度4
     */
    @ApiModelProperty(value = "下模温度4")
    private String downTempFour;

    /**
     * 冷却水温度
     */
    @ApiModelProperty(value = "冷却水温度")
    private String coolWaterTemp;
    /**
     * 喷嘴温度
     */
    @ApiModelProperty(value = "喷嘴温度")
    private String nozzle;
    /**
     * 烘箱温度
     */
    @ApiModelProperty(value = "烘箱温度")
    private String ovenTemp;
    /**
     * 烘零件时间
     */
    @ApiModelProperty(value = "烘零件时间")
    private String dryPartTime;
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
    /**----------------模缸页---------------**/
    /**
     * 关模快速压力
     */
    @ApiModelProperty(value = "关模快速压力")
    private String shutFastStress;
    /**
     * 关模慢速压力
     */
    @ApiModelProperty(value = "关模慢速压力")
    private String shutSlowStress;
    /**
     * 高压锁模压力
     */
    @ApiModelProperty(value = "高压锁模压力")
    private String pressModelStress;
    /**
     * 开模快速压力
     */
    @ApiModelProperty(value = "开模快速压力")
    private String openFastStress;
    /**
     * 开模慢速压力
     */
    @ApiModelProperty(value = "开模慢速压力")
    private String openSlowStress;

    /**
     * 调模压力
     */
    @ApiModelProperty(value = "调模压力")
    private String debugModelStress;
    /**
     * 关模快速速度
     */
    @ApiModelProperty(value = "关模快速速度")
    private String shutFastSpeed;
    /**
     * 关模慢速速度
     */
    @ApiModelProperty(value = "关模慢速速度")
    private String shutSlowSpeed;
    /**
     * 高压锁模速度
     */
    @ApiModelProperty(value = "高压锁模速度")
    private String pressModelSpeed;
    /**
     * 开模快速速度
     */
    @ApiModelProperty(value = "开模快速速度")
    private String openFastSpeed;
    /**
     * 开模慢速速度
     */
    @ApiModelProperty(value = "开模慢速速度")
    private String openSlowSpeed;

    /**
     * 调模速度
     */
    @ApiModelProperty(value = "调模速度")
    private String debugModelSpeed;
    /**
     * 关慢位
     */
    @ApiModelProperty(value = "关慢位")
    private String shutSlowLocal;
    /**
     * 锁模位
     */
    @ApiModelProperty(value = "锁模位")
    private String lockLocal;
    /**
     * 开快位
     */
    @ApiModelProperty(value = "开快位")
    private String openFastSLocal;
    /**
     * 开慢位
     */
    @ApiModelProperty(value = "开慢位")
    private String openSlowSLocal;
    /**
     * 开模止
     */
    @ApiModelProperty(value = "开模止")
    private String stopLocal;
    /**
     * 消音泄压时间
     */
    @ApiModelProperty(value = "消音泄压时间")
    private String muteTime;

    /**----------------辅助页---------------**/
    /**
     * 移模压力
     */
    @ApiModelProperty(value = "移模压力")
    private String moveStress;
    /**
     * 左移慢压力
     */
    @ApiModelProperty(value = "左移慢压力")
    private String leftStress;
    /**
     * 右移慢压力
     */
    @ApiModelProperty(value = "右移慢压力")
    private String rightStress;
    /**
     * 定位压力
     */
    @ApiModelProperty(value = "定位压力")
    private String localStress;
    /**
     * 座动压力
     */
    @ApiModelProperty(value = "座动压力")
    private String actStress;
    /**
     * 移模速度
     */
    @ApiModelProperty(value = "移模速度")
    private String moveSpeed;
    /**
     * 左移慢速度
     */
    @ApiModelProperty(value = "左移慢速度")
    private String leftSpeed;
    /**
     * 右移慢速度
     */
    @ApiModelProperty(value = "右移慢速度")
    private String rightSpeed;
    /**
     * 定位速度
     */
    @ApiModelProperty(value = "定位速度")
    private String localSpeed;
    /**
     * 座动速度
     */
    @ApiModelProperty(value = "座动速度")
    private String actSpeed;
    /**
     * 座进时间
     */
    @ApiModelProperty(value = "座进时间")
    private String intoTime;
    /**
     * 座退时间
     */
    @ApiModelProperty(value = "座退时间")
    private String quitTime;
    /**
     * 顶出时间
     */
    @ApiModelProperty(value = "顶出时间")
    private String outTime;
    /**
     * 顶停时间
     */
    @ApiModelProperty(value = "顶停时间")
    private String stopTime;
    /**
     * 顶退时间
     */
    @ApiModelProperty(value = "顶退时间")
    private String backTime;
    /**
     * 上顶针延迟时间
     */
    @ApiModelProperty(value = "上顶针延迟时间")
    private String upDelayTime;
    /**
     * 上顶针顶停时间
     */
    @ApiModelProperty(value = "上顶针顶停时间")
    private String upStopTime;

    /**----------------射顶缸2---------------**/
    /**
     * 充填一压压力
     */
    @ApiModelProperty(value = "充填一压压力")
    private String fillOneStress;
    /**
     * 充填二压压力
     */
    @ApiModelProperty(value = "充填二压压力")
    private String fillTwoStress;
    /**
     * 充填三压压力
     */
    @ApiModelProperty(value = "充填三压压力")
    private String fillThreeStress;
    /**
     * 充填四压压力
     */
    @ApiModelProperty(value = "充填四压压力")
    private String fillFourStress;
    /**
     * 保压一压压力
     */
    @ApiModelProperty(value = "保压一压压力")
    private String holdOneStress;
    /**
     * 保压二压压力
     */
    @ApiModelProperty(value = "保压二压压力")
    private String holdTwoStress;
    /**
     * 保压三压压力
     */
    @ApiModelProperty(value = "保压三压压力")
    private String holdThreeStress;
    /**
     * 充填一压速度
     */
    @ApiModelProperty(value = "充填一压速度")
    private String fillOneSpeed;
    /**
     * 充填二压速度
     */
    @ApiModelProperty(value = "充填二压速度")
    private String fillTwoSpeed;
    /**
     * 充填三压速度
     */
    @ApiModelProperty(value = "充填三压速度")
    private String fillThreeSpeed;
    /**
     * 充填四压速度
     */
    @ApiModelProperty(value = "充填四压速度")
    private String fillFourSpeed;
    /**
     * 保压一压速度
     */
    @ApiModelProperty(value = "保压一压速度")
    private String holdOneSpeed;
    /**
     * 保压二压速度
     */
    @ApiModelProperty(value = "保压二压速度")
    private String holdTwoSpeed;
    /**
     * 保压三压速度
     */
    @ApiModelProperty(value = "保压三压速度")
    private String holdThreeSpeed;

    /**
     * 二压位
     */
    @ApiModelProperty(value = "二压位")
    private String fillTwo;
    /**
     * 三压位
     */
    @ApiModelProperty(value = "三压位")
    private String fillThree;
    /**
     * 四压位
     */
    @ApiModelProperty(value = "四压位")
    private String fillFour;
    /**
     * 保压位
     */
    @ApiModelProperty(value = "保压位")
    private String keepLocal;
    /**
     * 保一时
     */
    @ApiModelProperty(value = "保一时")
    private String holdOne;
    /**
     * 保二时
     */
    @ApiModelProperty(value = "保二时")
    private String holdTwo;
    /**
     * 保三时
     */
    @ApiModelProperty(value = "保三时")
    private String holdThree;
    /**
     * 填充自动转换保压(填充监视)时间
     */
    @ApiModelProperty(value = "填充自动转换保压(填充监视)时间")
    private String converTime;
    /**
     * 冷却时间
     */
    @ApiModelProperty(value = "冷却时间")
    private String coolTime;
    /**
     * 射出重点最大位置
     */
    @ApiModelProperty(value = "射出重点最大位置")
    private String maxLocal;
    /**
     * 射出重点最小位置
     */
    @ApiModelProperty(value = "射出重点最小位置")
    private String minLocal;
    /**----------------射顶缸1---------------**/
    /**
     * 加料压力
     */
    @ApiModelProperty(value = "加料压力")
    private String addStress;
    /**
     * 松退压力
     */
    @ApiModelProperty(value = "松退压力")
    private String subStress;
    /**
     * 油压嘴压力
     */
    @ApiModelProperty(value = "油压嘴压力")
    private String mouthStress;
    /**
     * 加料速度
     */
    @ApiModelProperty(value = "加料速度")
    private String addSpeed;
    /**
     * 松退速度
     */
    @ApiModelProperty(value = "松退速度")
    private String subSpeed;
    /**
     * 油压嘴速度
     */
    @ApiModelProperty(value = "油压嘴速度")
    private String mouthSpeed;
    /**
     * 加料止位置
     */
    @ApiModelProperty(value = "加料止位置")
    private String addLocal;
    /**
     * 松退止位置
     */
    @ApiModelProperty(value = "松退止位置")
    private String subLocal;

    /**
     * 产量记数设定
     */
    @ApiModelProperty(value = "产量记数设定")
    private String productCount;
    /**
     * 前松退时间
     */
    @ApiModelProperty(value = "前松退时间")
    private String befSubTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
}