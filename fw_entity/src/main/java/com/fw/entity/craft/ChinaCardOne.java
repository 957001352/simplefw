package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 今机丰铁-注塑成型工艺卡
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "chinaCardOne", description = "今机丰铁-注塑成型工艺卡")
public class ChinaCardOne extends CardCommParams implements Serializable {

    /**-------------射出设定---------------**/
    /**
     * 射出一段起始位置
     */
    @ApiModelProperty(value = "射出一段起始位置")
    private String shotOneLocal;
    /**
     * 射出一段压力
     */
    @ApiModelProperty(value = "射出一段压力")
    private String shotOneStress;
    /**
     * 射出一段速度
     */
    @ApiModelProperty(value = "射出一段速度")
    private String shotOneSpeed;
    /**
     * 射出一段时间
     */
    @ApiModelProperty(value = "射出一段时间")
    private String shotOneTime;
    /**
     * 射出二段起始位置
     */
    @ApiModelProperty(value = "射出二段起始位置")
    private String shotTwoLocal;
    /**
     * 射出二段压力
     */
    @ApiModelProperty(value = "射出二段压力")
    private String shotTwoStress;
    /**
     * 射出二段速度
     */
    @ApiModelProperty(value = "射出二段速度")
    private String shotTwoSpeed;
    /**
     * 射出二段时间
     */
    @ApiModelProperty(value = "射出二段时间")
    private String shotTwoTime;
    /**
     * 射出三段起始位置
     */
    @ApiModelProperty(value = "射出三段起始位置")
    private String shotThreeLocal;
    /**
     * 射出三段压力
     */
    @ApiModelProperty(value = "射出三段压力")
    private String shotThreeStress;
    /**
     * 射出三段速度
     */
    @ApiModelProperty(value = "射出三段速度")
    private String shotThreeSpeed;
    /**
     * 射出三段时间
     */
    @ApiModelProperty(value = "射出三段时间")
    private String shotThreeTime;
    /**
     * 射出四段起始位置
     */
    @ApiModelProperty(value = "射出四段起始位置")
    private String shotFourLocal;
    /**
     * 射出四段压力
     */
    @ApiModelProperty(value = "射出四段压力")
    private String shotFourStress;
    /**
     * 射出四段速度
     */
    @ApiModelProperty(value = "射出四段速度")
    private String shotFourSpeed;
    /**
     * 射出四段时间
     */
    @ApiModelProperty(value = "射出四段时间")
    private String shotFourTime;
    /**
     * 转保压起始位置
     */
    @ApiModelProperty(value = "转保压起始位置")
    private String turnHoldLocal;
    /**
     * 转保压压力
     */
    @ApiModelProperty(value = "转保压压力")
    private String turnHoldStress;
    /**
     * 转保压速度
     */
    @ApiModelProperty(value = "转保压速度")
    private String turnHoldSpeed;
    /**
     * 转保压时间
     */
    @ApiModelProperty(value = "转保压时间")
    private String turnHoldTime;
    /**
     * 保压一段起始位置
     */
    @ApiModelProperty(value = "保压一段起始位置")
    private String holdOneLocal;
    /**
     * 保压一段压力
     */
    @ApiModelProperty(value = "保压一段压力")
    private String holdOneStress;
    /**
     * 保压一段速度
     */
    @ApiModelProperty(value = "保压一段速度")
    private String holdOneSpeed;
    /**
     * 保压一段时间
     */
    @ApiModelProperty(value = "保压一段时间")
    private String holdOneTime;
    /**
     * 保压二段起始位置
     */
    @ApiModelProperty(value = "保压二段起始位置")
    private String holdTwoLocal;
    /**
     * 保压二段压力
     */
    @ApiModelProperty(value = "保压二段压力")
    private String holdTwoStress;
    /**
     * 保压二段速度
     */
    @ApiModelProperty(value = "保压二段速度")
    private String holdTwoSpeed;
    /**
     * 保压二段时间
     */
    @ApiModelProperty(value = "保压二段时间")
    private String holdTwoTime;
    /**
     * 保压三段起始位置
     */
    @ApiModelProperty(value = "保压三段起始位置")
    private String holdThreeLocal;
    /**
     * 保压三段压力
     */
    @ApiModelProperty(value = "保压三段压力")
    private String holdThreeStress;
    /**
     * 保压三段速度
     */
    @ApiModelProperty(value = "保压三段速度")
    private String holdThreeSpeed;
    /**
     * 保压三段时间
     */
    @ApiModelProperty(value = "保压三段时间")
    private String holdThreeTime;
    /**
     * 保压四段起始位置
     */
    @ApiModelProperty(value = "保压四段起始位置")
    private String holdFourLocal;
    /**
     * 保压四段压力
     */
    @ApiModelProperty(value = "保压四段压力")
    private String holdFourStress;
    /**
     * 保压四段速度
     */
    @ApiModelProperty(value = "保压四段速度")
    private String holdFourSpeed;
    /**
     * 保压四段时间
     */
    @ApiModelProperty(value = "保压四段时间")
    private String holdFourTime;

    /**-------------温度设定（℃）---半温使用-----**/
    /**
     * 半温使用设定值 1#
     */
    @ApiModelProperty(value = "半温使用设定值 1#")
    private String tempOneValue;
    /**
     * 半温使用设定值 2#
     */
    @ApiModelProperty(value = "半温使用设定值 2#")
    private String tempTwoValue;
    /**
     * 半温使用设定值 3#
     */
    @ApiModelProperty(value = "半温使用设定值 3#")
    private String tempThreeValue;
    /**
     * 半温使用设定值 4#
     */
    @ApiModelProperty(value = "半温使用设定值 4#")
    private String tempFourValue;
    /**
     * 半温使用设定值 5#
     */
    @ApiModelProperty(value = "半温使用设定值 5#")
    private String tempFiveValue;

    /**-------------温度设定（℃）---热流道-----**/
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

    /**---------------储料设定-----------------**/
    /**
     *储料一段起始位置
     */
    @ApiModelProperty(value = "储料一段起始位置")
    private String storeOneLocal;
    /**
     *储料一段压力
     */
    @ApiModelProperty(value = "储料一段压力")
    private String storeOneStress;
    /**
     *储料一段速度
     */
    @ApiModelProperty(value = "储料一段速度")
    private String storeOneSpeed;
    /**
     *储料一段背压
     */
    @ApiModelProperty(value = "储料一段背压")
    private String storeOnePress;
    /**
     *储料二段起始位置
     */
    @ApiModelProperty(value = "储料二段起始位置")
    private String storeTwoLocal;
    /**
     *储料二段压力
     */
    @ApiModelProperty(value = "储料二段压力")
    private String storeTwoStress;
    /**
     *储料二段速度
     */
    @ApiModelProperty(value = "储料二段速度")
    private String storeTwoSpeed;
    /**
     *储料二段背压
     */
    @ApiModelProperty(value = "储料二段背压")
    private String storeTwoPress;
    /**
     *储料三段起始位置
     */
    @ApiModelProperty(value = "储料三段起始位置")
    private String storeThreeLocal;
    /**
     *储料三段压力
     */
    @ApiModelProperty(value = "储料三段压力")
    private String storeThreeStress;
    /**
     *储料三段速度
     */
    @ApiModelProperty(value = "储料三段速度")
    private String storeThreeSpeed;
    /**
     *储料三段背压
     */
    @ApiModelProperty(value = "储料三段背压")
    private String storeThreePress;
    /**
     *储料终止起始位置
     */
    @ApiModelProperty(value = "储料终止起始位置")
    private String storeEndLocal;
    /**
     *储料终止压力
     */
    @ApiModelProperty(value = "储料终止压力")
    private String storeEndStress;
    /**
     *储料终止速度
     */
    @ApiModelProperty(value = "储料终止速度")
    private String storeEndSpeed;
    /**
     *储料终止背压
     */
    @ApiModelProperty(value = "储料终止背压")
    private String storeEndPress;
    /**
     *射退偏移位置
     */
    @ApiModelProperty(value = "射退偏移位置")
    private String shootOutLocal;
    /**
     *射退压力
     */
    @ApiModelProperty(value = "射退压力")
    private String shootOutStress;
    /**
     *射退速度
     */
    @ApiModelProperty(value = "射退速度")
    private String shootOutSpeed;
    /**
     *冷却时间偏移位置
     */
    @ApiModelProperty(value = "冷却时间偏移位置")
    private String coolLocal;
    /**
     *冷却时间压力
     */
    @ApiModelProperty(value = "冷却时间压力")
    private String coolStress;
    /**
     *冷却时间速度
     */
    @ApiModelProperty(value = "冷却时间速度")
    private String coolSpeed;

    /**---------------开模行程-----------------**/
    /**
     * 开模行程
     */
    @ApiModelProperty(value = "开模行程")
    private String openTrip;
    /**
     * 关模一段起始位置
     */
    @ApiModelProperty(value = "关模一段起始位置")
    private String closeOneLocal;
    /**
     * 关模一段压力
     */
    @ApiModelProperty(value = "关模一段压力")
    private String closeOneStress;
    /**
     * 关模一段速度
     */
    @ApiModelProperty(value = "关模一段速度")
    private String closeOneSpeed;

    /**
     * 关模二段起始位置
     */
    @ApiModelProperty(value = "关模二段起始位置")
    private String closeTwoLocal;
    /**
     * 关模二段压力
     */
    @ApiModelProperty(value = "关模二段压力")
    private String closeTwoStress;
    /**
     * 关模二段速度
     */
    @ApiModelProperty(value = "关模二段速度")
    private String closeTwoSpeed;

    /**
     * 关模三段起始位置
     */
    @ApiModelProperty(value = "关模三段起始位置")
    private String closeThreeLocal;
    /**
     * 关模三段压力
     */
    @ApiModelProperty(value = "关模三段压力")
    private String closeThreeStress;
    /**
     * 关模三段速度
     */
    @ApiModelProperty(value = "关模三段速度")
    private String closeThreeSpeed;
    /**
     * 关模低压起始位置
     */
    @ApiModelProperty(value = "关模低压起始位置")
    private String closeLowLocal;
    /**
     * 关模低压压力
     */
    @ApiModelProperty(value = "关模低压压力")
    private String closeLowStress;
    /**
     * 关模低压速度
     */
    @ApiModelProperty(value = "关模低压速度")
    private String closeLowSpeed;
    /**
     * 关模高压起始位置
     */
    @ApiModelProperty(value = "关模高压起始位置")
    private String closeHighLocal;
    /**
     * 关模高压压力
     */
    @ApiModelProperty(value = "关模高压压力")
    private String closeHighStress;
    /**
     * 关模高压速度
     */
    @ApiModelProperty(value = "关模高压速度")
    private String closeHighSpeed;



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