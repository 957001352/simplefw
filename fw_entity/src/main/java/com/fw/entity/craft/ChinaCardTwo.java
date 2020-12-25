package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 海天-注塑成型工艺卡
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "chinaCardTwo", description = "海天-注塑成型工艺卡")
public class ChinaCardTwo extends CardCommParams implements Serializable {
    /**---------------射出设定----------------**/
    /**
     * 射出一段设定位置
     */
    @ApiModelProperty(value = "射出一段设定位置")
    private String shotOneLocal;
    /**
     * 射出二段设定位置
     */
    @ApiModelProperty(value = "射出二段设定位置")
    private String shotTwoLocal;
    /**
     * 射出三段设定位置
     */
    @ApiModelProperty(value = "射出三段设定位置")
    private String shotThreeLocal;
    /**
     * 射出四段设定位置
     */
    @ApiModelProperty(value = "射出四段设定位置")
    private String shotFourLocal;
    /**
     * 射出五段设定位置
     */
    @ApiModelProperty(value = "射出五段设定位置")
    private String shotFiveLocal;

    /**
     * 射出一段设定压力
     */
    @ApiModelProperty(value = "射出一段设定压力")
    private String shotOneStress;
    /**
     * 射出二段设定压力
     */
    @ApiModelProperty(value = "射出二段设定压力")
    private String shotTwoStress;
    /**
     * 射出三段设定压力
     */
    @ApiModelProperty(value = "射出三段设定压力")
    private String shotThreeStress;
    /**
     * 射出四段设定压力
     */
    @ApiModelProperty(value = "射出四段设定压力")
    private String shotFourStress;
    /**
     * 射出五段设定压力
     */
    @ApiModelProperty(value = "射出五段设定压力")
    private String shotFiveStress;


    /**
     * 射出一段设定速度
     */
    @ApiModelProperty(value = "射出一段设定速度")
    private String shotOneSpeed;
    /**
     * 射出二段设定速度
     */
    @ApiModelProperty(value = "射出二段设定速度")
    private String shotTwoSpeed;
    /**
     * 射出三段设定速度
     */
    @ApiModelProperty(value = "射出三段设定速度")
    private String shotThreeSpeed;
    /**
     * 射出四段设定速度
     */
    @ApiModelProperty(value = "射出四段设定速度")
    private String shotFourSpeed;
    /**
     * 射出五段设定速度
     */
    @ApiModelProperty(value = "射出五段设定速度")
    private String shotFiveSpeed;

    /**
     * 压力
     */
    @ApiModelProperty(value = "压力")
    private String holdStress;
    /**
     * 位置
     */
    @ApiModelProperty(value = "位置")
    private String holdLocal;
    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private String holdTime;
    /**
     * 保压一段设定压力
     */
    @ApiModelProperty(value = "保压一段设定压力")
    private String holdOneStress;
    /**
     * 保压二段设定压力
     */
    @ApiModelProperty(value = "保压二段设定压力")
    private String holdTwoStress;
    /**
     * 保压三段设定压力
     */
    @ApiModelProperty(value = "保压三段设定压力")
    private String holdThreeStress;
    /**
     * 保压四段设定压力
     */
    @ApiModelProperty(value = "保压四段设定压力")
    private String holdFourStress;
    /**
     * 保压五段设定压力
     */
    @ApiModelProperty(value = "保压五段设定压力")
    private String holdFiveStress;
    /**
     * 保压一段设定速度
     */
    @ApiModelProperty(value = "保压一段设定速度")
    private String holdOneSpeed;
    /**
     * 保压二段设定速度
     */
    @ApiModelProperty(value = "保压二段设定速度")
    private String holdTwoSpeed;
    /**
     * 保压三段设定速度
     */
    @ApiModelProperty(value = "保压三段设定速度")
    private String holdThreeSpeed;
    /**
     * 保压四段设定速度
     */
    @ApiModelProperty(value = "保压四段设定速度")
    private String holdFourSpeed;
    /**
     * 保压五段设定速度
     */
    @ApiModelProperty(value = "保压五段设定速度")
    private String holdFiveSpeed;
    /**
     * 保压一段设定时间
     */
    @ApiModelProperty(value = "保压一段设定时间")
    private String holdOneTime;
    /**
     * 保压二段设定时间
     */
    @ApiModelProperty(value = "保压二段设定时间")
    private String holdTwoTime;
    /**
     * 保压三段设定时间
     */
    @ApiModelProperty(value = "保压三段设定时间")
    private String holdThreeTime;
    /**
     * 保压四段设定时间
     */
    @ApiModelProperty(value = "保压四段设定时间")
    private String holdFourTime;
    /**
     * 保压五段设定时间
     */
    @ApiModelProperty(value = "保压五段设定时间")
    private String holdFiveTime;
    /**---------------储料设定----------------**/
    /**
     * 储料一段设定位置
     */
    @ApiModelProperty(value = "储料一段设定位置")
    private String storeOneLocal;
    /**
     * 储料二段设定位置
     */
    @ApiModelProperty(value = "储料二段设定位置")
    private String storeTwoLocal;
    /**
     * 储料三段设定位置
     */
    @ApiModelProperty(value = "储料三段设定位置")
    private String storeThreeLocal;
    /**
     * 储料四段设定位置
     */
    @ApiModelProperty(value = "储料四段设定位置")
    private String storeFourLocal;
    /**
     * 储料一段设定压力
     */
    @ApiModelProperty(value = "储料一段设定压力")
    private String storeOneStress;
    /**
     * 储料二段设定压力
     */
    @ApiModelProperty(value = "储料二段设定压力")
    private String storeTwoStress;
    /**
     * 储料三段设定压力
     */
    @ApiModelProperty(value = "储料三段设定压力")
    private String storeThreeStress;
    /**
     * 储料四段设定压力
     */
    @ApiModelProperty(value = "储料四段设定压力")
    private String storeFourStress;
    /**
     * 储料五段设定压力
     */
    @ApiModelProperty(value = "储料五段设定压力")
    private String storeFiveStress;
    /**
     * 储料一段设定速度
     */
    @ApiModelProperty(value = "储料一段设定速度")
    private String storeOneSpeed;
    /**
     * 储料二段设定速度
     */
    @ApiModelProperty(value = "储料二段设定速度")
    private String storeTwoSpeed;
    /**
     * 储料三段设定速度
     */
    @ApiModelProperty(value = "储料三段设定速度")
    private String storeThreeSpeed;
    /**
     * 储料四段设定速度
     */
    @ApiModelProperty(value = "储料四段设定速度")
    private String storeFourSpeed;
    /**
     * 储料五段设定速度
     */
    @ApiModelProperty(value = "储料五段设定速度")
    private String storeFiveSpeed;
    /**
     * 储料一段设定背压
     */
    @ApiModelProperty(value = "储料一段设定背压")
    private String storeOnePress;
    /**
     * 储料二段设定背压
     */
    @ApiModelProperty(value = "储料二段设定背压")
    private String storeTwoPress;
    /**
     * 储料三段设定背压
     */
    @ApiModelProperty(value = "储料三段设定背压")
    private String storeThreePress;
    /**
     * 储料四段设定背压
     */
    @ApiModelProperty(value = "储料四段设定背压")
    private String storeFourPress;
    /**
     * 射退模式
     */
    @ApiModelProperty(value = "射退模式")
    private String shootModel;
    /**
     * 射退距离
     */
    @ApiModelProperty(value = "射退距离")
    private String shootDistance;
    /**
     * 冷却时间
     */
    @ApiModelProperty(value = "冷却时间")
    private String coolTime;

    /**---------------温度设定（℃）----------------**/
    /**
     * 螺杆设定值 1#
     */
    @ApiModelProperty(value = "螺杆设定值 1#")
    private String screwOneValue;
    /**
     * 螺杆设定值 2#
     */
    @ApiModelProperty(value = "螺杆设定值 2#")
    private String screwTwoValue;
    /**
     * 螺杆设定值 3#
     */
    @ApiModelProperty(value = "螺杆设定值 3#")
    private String screwThreeValue;
    /**
     * 螺杆设定值 4#
     */
    @ApiModelProperty(value = "螺杆设定值 4#")
    private String screwFourValue;
    /**
     * 螺杆设定值 5#
     */
    @ApiModelProperty(value = "螺杆设定值 5#")
    private String screwFiveValue;

    /**
     * 上偏差 1#
     */
    @ApiModelProperty(value = "上偏差 1#")
    private String upErrorOne;
    /**
     * 上偏差 2#
     */
    @ApiModelProperty(value = "上偏差 2#")
    private String upErrorTwo;
    /**
     * 上偏差 3#
     */
    @ApiModelProperty(value = "上偏差 3#")
    private String upErrorThree;
    /**
     * 上偏差 4#
     */
    @ApiModelProperty(value = "上偏差 4#")
    private String upErrorFour;
    /**
     * 上偏差 5#
     */
    @ApiModelProperty(value = "上偏差 5#")
    private String upErrorFive;

    /**
     * 下偏差 1#
     */
    @ApiModelProperty(value = "下偏差 1#")
    private String downErrorOne;
    /**
     * 下偏差 2#
     */
    @ApiModelProperty(value = "下偏差 2#")
    private String downErrorTwo;
    /**
     * 下偏差 3#
     */
    @ApiModelProperty(value = "下偏差 3#")
    private String downErrorThree;
    /**
     * 下偏差 4#
     */
    @ApiModelProperty(value = "下偏差 4#")
    private String downErrorFour;
    /**
     * 下偏差 5#
     */
    @ApiModelProperty(value = "下偏差 5#")
    private String downErrorFive;
    /**
     * 热流道设定值 1#
     */
    @ApiModelProperty(value = "热流道设定值 1#")
    private String flowOneValue;
    /**
     * 热流道设定值 2#
     */
    @ApiModelProperty(value = "热流道设定值 2#")
    private String flowTwoValue;
    /**
     * 热流道设定值 3#
     */
    @ApiModelProperty(value = "热流道设定值 3#")
    private String flowThreeValue;
    /**
     * 热流道设定值 4#
     */
    @ApiModelProperty(value = "热流道设定值 4#")
    private String flowFourValue;
    /**
     * 热流道设定值 5#
     */
    @ApiModelProperty(value = "热流道设定值 5#")
    private String flowFiveValue;



    /**
     * 热流道设定值 6#
     */
    @ApiModelProperty(value = "热流道设定值 6#")
    private String flowSixValue;
    /**
     * 热流道设定值 7#
     */
    @ApiModelProperty(value = "热流道设定值 7#")
    private String flowSevenValue;
    /**
     * 热流道设定值 8#
     */
    @ApiModelProperty(value = "热流道设定值 8#")
    private String flowEightValue;
    /**
     * 热流道设定值 9#
     */
    @ApiModelProperty(value = "热流道设定值 9#")
    private String flowNineValue;
    /**
     * 热流道设定值 10#
     */
    @ApiModelProperty(value = "热流道设定值 10#")
    private String flowTenValue;



    /**---------------开合模设定----------------**/
    /**
     * 开模行程
     */
    @ApiModelProperty(value = "开模行程")
    private String openTrip;
    /**
     * 开模一段起始位置
     */
    @ApiModelProperty(value = "开模一段起始位置")
    private String openOneLocal;
    /**
     * 开模一段压力
     */
    @ApiModelProperty(value = "开模一段压力")
    private String openOneStress;
    /**
     * 开模一段速度
     */
    @ApiModelProperty(value = "开模一段速度")
    private String openOneSpeed;

    /**
     * 开模二段起始位置
     */
    @ApiModelProperty(value = "开模二段起始位置")
    private String openTwoLocal;
    /**
     * 开模二段压力
     */
    @ApiModelProperty(value = "开模二段压力")
    private String openTwoStress;
    /**
     * 开模二段速度
     */
    @ApiModelProperty(value = "开模二段速度")
    private String openTwoSpeed;

    /**
     * 开模三段起始位置
     */
    @ApiModelProperty(value = "开模三段起始位置")
    private String openThreeLocal;
    /**
     * 开模三段压力
     */
    @ApiModelProperty(value = "开模三段压力")
    private String openThreeStress;
    /**
     * 开模三段速度
     */
    @ApiModelProperty(value = "开模三段速度")
    private String openThreeSpeed;
    /**
     * 开模四段起始位置
     */
    @ApiModelProperty(value = "开模四段起始位置")
    private String openFourLocal;
    /**
     * 开模四段压力
     */
    @ApiModelProperty(value = "开模四段压力")
    private String openFourStress;
    /**
     * 开模四段速度
     */
    @ApiModelProperty(value = "开模四段速度")
    private String openFourSpeed;
    /**
     * 开模五段起始位置
     */
    @ApiModelProperty(value = "开模五段起始位置")
    private String openFiveLocal;
    /**
     * 开模五段压力
     */
    @ApiModelProperty(value = "开模五段压力")
    private String openFiveStress;
    /**
     * 开模五段速度
     */
    @ApiModelProperty(value = "开模五段速度")
    private String openFiveSpeed;
    /**
     * 合模一段起始位置
     */
    @ApiModelProperty(value = "合模一段起始位置")
    private String closeOneLocal;
    /**
     * 合模一段压力
     */
    @ApiModelProperty(value = "合模一段压力")
    private String closeOneStress;
    /**
     * 合模一段速度
     */
    @ApiModelProperty(value = "合模一段速度")
    private String closeOneSpeed;

    /**
     * 合模二段起始位置
     */
    @ApiModelProperty(value = "合模二段起始位置")
    private String closeTwoLocal;
    /**
     * 合模二段压力
     */
    @ApiModelProperty(value = "合模二段压力")
    private String closeTwoStress;
    /**
     * 合模二段速度
     */
    @ApiModelProperty(value = "合模二段速度")
    private String closeTwoSpeed;

    /**
     * 合模三段起始位置
     */
    @ApiModelProperty(value = "合模三段起始位置")
    private String closeThreeLocal;
    /**
     * 合模三段压力
     */
    @ApiModelProperty(value = "合模三段压力")
    private String closeThreeStress;
    /**
     * 合模三段速度
     */
    @ApiModelProperty(value = "合模三段速度")
    private String closeThreeSpeed;
    /**
     * 合模低压起始位置
     */
    @ApiModelProperty(value = "合模低压起始位置")
    private String closeLowLocal;
    /**
     * 合模低压压力
     */
    @ApiModelProperty(value = "合模低压压力")
    private String closeLowStress;
    /**
     * 合模低压速度
     */
    @ApiModelProperty(value = "合模低压速度")
    private String closeLowSpeed;
    /**
     * 合模高压起始位置
     */
    @ApiModelProperty(value = "合模高压起始位置")
    private String closeHighLocal;
    /**
     * 合模高压压力
     */
    @ApiModelProperty(value = "合模高压压力")
    private String closeHighStress;
    /**
     * 合模高压速度
     */
    @ApiModelProperty(value = "合模高压速度")
    private String closeHighSpeed;


    /**---------------托模设定----------------**/
    /**
     * 托模方式
     */
    @ApiModelProperty(value = "托模方式")
    private String joeWay;
    /**
     * 托模次数
     */
    @ApiModelProperty(value = "托模次数")
    private String joeCount;


    /**
     * 顶出一段起始位置
     */
    @ApiModelProperty(value = "顶出一段起始位置")
    private String pushOutOneLocal;
    /**
     * 顶出一段压力
     */
    @ApiModelProperty(value = "顶出一段压力")
    private String pushOutOneStress;
    /**
     * 顶出一段速度
     */
    @ApiModelProperty(value = "顶出一段速度")
    private String pushOutOneSpeed;
    /**
     * 顶出一段延迟时间
     */
    @ApiModelProperty(value = "顶出一段延迟时间")
    private String pushOutOneDelay;
    /**
     * 顶出二段起始位置
     */
    @ApiModelProperty(value = "顶出二段起始位置")
    private String pushOutTwoLocal;
    /**
     * 顶出二段压力
     */
    @ApiModelProperty(value = "顶出二段压力")
    private String pushOutTwoStress;
    /**
     * 顶出二段速度
     */
    @ApiModelProperty(value = "顶出二段速度")
    private String pushOutTwoSpeed;
    /**
     * 顶出二段延迟时间
     */
    @ApiModelProperty(value = "顶出二段延迟时间")
    private String pushOutTwoDelay;
    /**
     * 顶退一段起始位置
     */
    @ApiModelProperty(value = "顶退一段起始位置")
    private String pushBackOneLocal;
    /**
     * 顶退一段压力
     */
    @ApiModelProperty(value = "顶退一段压力")
    private String pushBackOneStress;
    /**
     * 顶退一段速度
     */
    @ApiModelProperty(value = "顶退一段速度")
    private String pushBackOneSpeed;
    /**
     * 顶退一段延迟时间
     */
    @ApiModelProperty(value = "顶退一段延迟时间")
    private String pushBackOneDelay;
    /**
     * 顶退二段起始位置
     */
    @ApiModelProperty(value = "顶退二段起始位置")
    private String pushBackTwoLocal;
    /**
     * 顶退二段压力
     */
    @ApiModelProperty(value = "顶退二段压力")
    private String pushBackTwoStress;
    /**
     * 顶退二段速度
     */
    @ApiModelProperty(value = "顶退二段速度")
    private String pushBackTwoSpeed;
    /**
     * 顶退二段延迟时间
     */
    @ApiModelProperty(value = "顶退二段延迟时间")
    private String pushBackTwoDelay;

}