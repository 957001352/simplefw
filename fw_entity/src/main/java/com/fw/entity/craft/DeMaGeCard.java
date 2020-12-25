package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 德马格-注塑成型工艺卡
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "deMaGeCard", description = "德马格-注塑成型工艺卡")
public class DeMaGeCard extends CardCommParams implements Serializable {
    /**----------------注射---------------**/
    /**
     * 注射一段位置
     */
    @ApiModelProperty(value = "注射一段位置")
    private String shotOneLocal;
    /**
     * 注射一段压力
     */
    @ApiModelProperty(value = "注射一段压力")
    private String shotOneStress;
    /**
     * 注射一段速度
     */
    @ApiModelProperty(value = "注射一段速度")
    private String shotOneSpeed;
    /**
     * 注射二段位置
     */
    @ApiModelProperty(value = "注射二段位置")
    private String shotTwoLocal;
    /**
     * 注射二段压力
     */
    @ApiModelProperty(value = "注射二段压力")
    private String shotTwoStress;
    /**
     * 注射二段速度
     */
    @ApiModelProperty(value = "注射二段速度")
    private String shotTwoSpeed;
    /**
     * 注射三段位置
     */
    @ApiModelProperty(value = "注射三段位置")
    private String shotThreeLocal;
    /**
     * 注射三段压力
     */
    @ApiModelProperty(value = "注射三段压力")
    private String shotThreeStress;
    /**
     * 注射三段速度
     */
    @ApiModelProperty(value = "注射三段速度")
    private String shotThreeSpeed;
    /**
     * 注射四段位置
     */
    @ApiModelProperty(value = "注射四段位置")
    private String shotFourLocal;
    /**
     * 注射四段压力
     */
    @ApiModelProperty(value = "注射四段压力")
    private String shotFourStress;
    /**
     * 注射四段速度
     */
    @ApiModelProperty(value = "注射四段速度")
    private String shotFourSpeed;
    /**
     * 注射五段位置
     */
    @ApiModelProperty(value = "注射五段位置")
    private String shotFiveLocal;
    /**
     * 注射五段压力
     */
    @ApiModelProperty(value = "注射五段压力")
    private String shotFiveStress;
    /**
     * 注射五段速度
     */
    @ApiModelProperty(value = "注射五段速度")
    private String shotFiveSpeed;
    /**---------------保压----------------**/
    /**
     * 保压一段时间
     */
    @ApiModelProperty(value = "保压一段时间")
    private String holdOneTime;
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
     * 保压二段时间
     */
    @ApiModelProperty(value = "保压二段时间")
    private String holdTwoTime;
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
     * 保压三段时间
     */
    @ApiModelProperty(value = "保压三段时间")
    private String holdThreeTime;
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
     * 保压四段时间
     */
    @ApiModelProperty(value = "保压四段时间")
    private String holdFourTime;
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
     * 保压五段时间
     */
    @ApiModelProperty(value = "保压五段时间")
    private String holdFiveTime;
    /**
     * 保压五段压力
     */
    @ApiModelProperty(value = "保压五段压力")
    private String holdFiveStress;
    /**
     * 保压五段速度
     */
    @ApiModelProperty(value = "保压五段速度")
    private String holdFiveSpeed;

    /**----------------温度---------------**/
    /**
     * 喷嘴2
     */
    @ApiModelProperty(value = "喷嘴2")
    private String nozzleTwo;
    /**
     * 喷嘴1
     */
    @ApiModelProperty(value = "喷嘴1")
    private String nozzleOne;
    /**
     * 区 1#
     */
    @ApiModelProperty(value = "区 1#")
    private String AreaTempOne;
    /**
     * 区 2#
     */
    @ApiModelProperty(value = "区 2#")
    private String AreaTempTwo;
    /**
     * 区 3#
     */
    @ApiModelProperty(value = "区 3#")
    private String AreaTempThree;
    /**
     * 区 4#
     */
    @ApiModelProperty(value = "区 4#")
    private String AreaTempFour;
    /**----------------热流道温度---------------**/
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
    /**
     * 热流道设定值 11#
     */
    @ApiModelProperty(value = "热流道设定值 11#")
    private String flowElevenValue;
    /**
     * 热流道设定值 12#
     */
    @ApiModelProperty(value = "热流道设定值 12#")
    private String flowTwelveValue;
    /**
     * 热流道设定值 13#
     */
    @ApiModelProperty(value = "热流道设定值 13#")
    private String flowThirtValue;
    /**
     * 热流道设定值 14#
     */
    @ApiModelProperty(value = "热流道设定值 14#")
    private String flowFourtValue;
    /**
     * 热流道设定值 15#
     */
    @ApiModelProperty(value = "热流道设定值 15#")
    private String flowFiftValue;

    /**---------------预塑----------------**/
    /**
     * 预塑一段位置
     */
    @ApiModelProperty(value = "预塑一段位置")
    private String modelOneLocal;
    /**
     * 预塑一段压力
     */
    @ApiModelProperty(value = "预塑一段压力")
    private String modelOneStress;
    /**
     * 预塑一段速度
     */
    @ApiModelProperty(value = "预塑一段速度")
    private String modelOneSpeed;
    /**
     * 预塑一段螺杆转速
     */
    @ApiModelProperty(value = "预塑一段螺杆转速")
    private String modelOneRev;
    /**
     * 预塑二段位置
     */
    @ApiModelProperty(value = "预塑二段位置")
    private String modelTwoLocal;
    /**
     * 预塑二段压力
     */
    @ApiModelProperty(value = "预塑二段压力")
    private String modelTwoStress;
    /**
     * 预塑二段速度
     */
    @ApiModelProperty(value = "预塑二段速度")
    private String modelTwoSpeed;
    /**
     * 预塑二段螺杆转速
     */
    @ApiModelProperty(value = "预塑二段螺杆转速")
    private String modelTwoRev;
    /**
     * 预塑三段位置
     */
    @ApiModelProperty(value = "预塑三段位置")
    private String modelThreeLocal;
    /**
     * 预塑三段压力
     */
    @ApiModelProperty(value = "预塑三段压力")
    private String modelThreeStress;
    /**
     * 预塑三段速度
     */
    @ApiModelProperty(value = "预塑三段速度")
    private String modelThreeSpeed;
    /**
     * 预塑三段螺杆转速
     */
    @ApiModelProperty(value = "预塑三段螺杆转速")
    private String modelThreeRev;
    /**
     * 预塑四段位置
     */
    @ApiModelProperty(value = "预塑四段位置")
    private String modelFourLocal;
    /**
     * 预塑四段压力
     */
    @ApiModelProperty(value = "预塑四段压力")
    private String modelFourStress;
    /**
     * 预塑四段速度
     */
    @ApiModelProperty(value = "预塑四段速度")
    private String modelFourSpeed;
    /**
     * 预塑四段螺杆转速
     */
    @ApiModelProperty(value = "预塑四段螺杆转速")
    private String modelFourRev;
    /**
     * 预塑五段位置
     */
    @ApiModelProperty(value = "预塑五段位置")
    private String modelFiveLocal;
    /**
     * 预塑五段压力
     */
    @ApiModelProperty(value = "预塑五段压力")
    private String modelFiveStress;
    /**
     * 预塑五段速度
     */
    @ApiModelProperty(value = "预塑五段速度")
    private String modelFiveSpeed;
    /**
     * 预塑五段螺杆转速
     */
    @ApiModelProperty(value = "预塑五段螺杆转速")
    private String modelFiveRev;
    /**---------------合模----------------**/
    /**
     * 合模一段时间
     */
    @ApiModelProperty(value = "合模一段时间")
    private String compOneTime;
    /**
     * 合模一段位置
     */
    @ApiModelProperty(value = "合模一段位置")
    private String compOneLocal;
    /**
     * 合模一段速度
     */
    @ApiModelProperty(value = "合模一段速度")
    private String compOneSpeed;
    /**
     * 合模一段力
     */
    @ApiModelProperty(value = "合模一段力")
    private String compOneForce;
    /**
     * 合模二段时间
     */
    @ApiModelProperty(value = "合模二段时间")
    private String compTwoTime;
    /**
     * 合模二段位置
     */
    @ApiModelProperty(value = "合模二段位置")
    private String compTwoLocal;
    /**
     * 合模二段速度
     */
    @ApiModelProperty(value = "合模二段速度")
    private String compTwoSpeed;
    /**
     * 合模二段力
     */
    @ApiModelProperty(value = "合模二段力")
    private String compTwoForce;
    /**
     * 合模三段时间
     */
    @ApiModelProperty(value = "合模三段时间")
    private String compThreeTime;
    /**
     * 合模三段位置
     */
    @ApiModelProperty(value = "合模三段位置")
    private String compThreeLocal;
    /**
     * 合模三段速度
     */
    @ApiModelProperty(value = "合模三段速度")
    private String compThreeSpeed;
    /**
     * 合模三段力
     */
    @ApiModelProperty(value = "合模三段力")
    private String compThreeForce;
    /**---------------模保----------------**/
    /**
     * 模保一段时间
     */
    @ApiModelProperty(value = "模保一段时间")
    private String keepOneTime;
    /**
     * 模保一段位置
     */
    @ApiModelProperty(value = "模保一段位置")
    private String keepOneLocal;
    /**
     * 模保一段速度
     */
    @ApiModelProperty(value = "模保一段速度")
    private String keepOneSpeed;
    /**
     * 模保一段力
     */
    @ApiModelProperty(value = "模保一段力")
    private String keepOneForce;
    /**
     * 模保二段时间
     */
    @ApiModelProperty(value = "模保二段时间")
    private String keepTwoTime;
    /**
     * 模保二段位置
     */
    @ApiModelProperty(value = "模保二段位置")
    private String keepTwoLocal;
    /**
     * 模保二段速度
     */
    @ApiModelProperty(value = "模保二段速度")
    private String keepTwoSpeed;
    /**
     * 模保二段力
     */
    @ApiModelProperty(value = "模保二段力")
    private String keepTwoForce;
    /**
     * 模保三段时间
     */
    @ApiModelProperty(value = "模保三段时间")
    private String keepThreeTime;
    /**
     * 模保三段位置
     */
    @ApiModelProperty(value = "模保三段位置")
    private String keepThreeLocal;
    /**
     * 模保三段速度
     */
    @ApiModelProperty(value = "模保三段速度")
    private String keepThreeSpeed;
    /**
     * 模保三段力
     */
    @ApiModelProperty(value = "模保三段力")
    private String keepThreeForce;
    /**
     * 模保四段时间
     */
    @ApiModelProperty(value = "模保四段时间")
    private String keepFourTime;
    /**
     * 模保四段位置
     */
    @ApiModelProperty(value = "模保四段位置")
    private String keepFourLocal;
    /**
     * 模保四段速度
     */
    @ApiModelProperty(value = "模保四段速度")
    private String keepFourSpeed;
    /**
     * 模保四段力
     */
    @ApiModelProperty(value = "模保四段力")
    private String keepFourForce;
    /**---------------锁模----------------**/
    /**
     * 锁模启动时间
     */
    @ApiModelProperty(value = "锁模启动时间")
    private String keepOpenTime;
    /**
     * 锁模启动位置
     */
    @ApiModelProperty(value = "锁模启动位置")
    private String keepOpenLocal;
    /**
     * 锁模启动速度
     */
    @ApiModelProperty(value = "锁模启动速度")
    private String keepOpenSpeed;
    /**
     * 锁模启动力
     */
    @ApiModelProperty(value = "锁模启动力")
    private String keepOpenForce;
    /**
     * 锁模时间
     */
    @ApiModelProperty(value = "锁模时间")
    private String keepCloseTime;
    /**
     * 锁模位置
     */
    @ApiModelProperty(value = "锁模位置")
    private String keepCloseLocal;
    /**
     * 锁模速度
     */
    @ApiModelProperty(value = "锁模速度")
    private String keepCloseSpeed;
    /**
     * 锁模力
     */
    @ApiModelProperty(value = "锁模力")
    private String keepCloseForce;
    /**----------------开模---------------**/
    /**
     * 暂停时间
     */
    @ApiModelProperty(value = "暂停时间")
    private String stopTime;
    /**
     * 暂停位置
     */
    @ApiModelProperty(value = "暂停位置")
    private String stopLocal;
    /**
     * 暂停速度
     */
    @ApiModelProperty(value = "暂停速度")
    private String stopSpeed;
    /**
     * 开模位置时间
     */
    @ApiModelProperty(value = "开模位置时间")
    private String openTime;
    /**
     * 开模位置位置
     */
    @ApiModelProperty(value = "开模位置位置")
    private String openLocal;
    /**
     * 开模位置速度
     */
    @ApiModelProperty(value = "开模位置速度")
    private String openSpeed;
    /**
     * 开模时间1
     */
    @ApiModelProperty(value = "开模时间1")
    private String openOneTime;
    /**
     * 开模位置1
     */
    @ApiModelProperty(value = "开模位置1")
    private String openOneLocal;
    /**
     * 开模速度1
     */
    @ApiModelProperty(value = "开模速度1")
    private String openOneSpeed;
    /**
     * 开模时间2
     */
    @ApiModelProperty(value = "开模时间2")
    private String openTwoTime;
    /**
     * 开模位置2
     */
    @ApiModelProperty(value = "开模位置2")
    private String openTwoLocal;
    /**
     * 开模速度2
     */
    @ApiModelProperty(value = "开模速度2")
    private String openTwoSpeed;
    /**
     * 开模时间3
     */
    @ApiModelProperty(value = "开模时间3")
    private String openThreeTime;
    /**
     * 开模位置3
     */
    @ApiModelProperty(value = "开模位置3")
    private String openThreeLocal;
    /**
     * 开模速度3
     */
    @ApiModelProperty(value = "开模速度3")
    private String openThreeSpeed;
    /**
     * 开模时间4
     */
    @ApiModelProperty(value = "开模时间4")
    private String openFourTime;
    /**
     * 开模位置4
     */
    @ApiModelProperty(value = "开模位置4")
    private String openFourLocal;
    /**
     * 开模速度4
     */
    @ApiModelProperty(value = "开模速度4")
    private String openFourSpeed;
    /**
     * 开模时间脱模
     */
    @ApiModelProperty(value = "开模时间脱模")
    private String openLossTime;
    /**
     * 开模位置脱模
     */
    @ApiModelProperty(value = "开模位置脱模")
    private String openLossLocal;
    /**
     * 开模速度脱模
     */
    @ApiModelProperty(value = "开模速度脱模")
    private String openLossSpeed;
    /**
     * 左延时时间
     */
    @ApiModelProperty(value = "左延时时间")
    private String leftDelayTime;
    /**
     * 左延时位置
     */
    @ApiModelProperty(value = "左延时位置")
    private String leftDelayLocal;
    /**
     * 左延时速度
     */
    @ApiModelProperty(value = "左延时速度")
    private String leftDelaySpeed;
    /**
     * 左延时压力
     */
    @ApiModelProperty(value = "左延时压力")
    private String leftDelayPress;
    /**
     * 左延时到位关闭
     */
    @ApiModelProperty(value = "左延时到位关闭")
    private String leftDelayClose;
    /**
     * 左1时间
     */
    @ApiModelProperty(value = "左1时间")
    private String leftOneTime;
    /**
     * 左1位置
     */
    @ApiModelProperty(value = "左1位置")
    private String leftOneLocal;
    /**
     * 左1速度
     */
    @ApiModelProperty(value = "左1速度")
    private String leftOneSpeed;
    /**
     * 左1压力
     */
    @ApiModelProperty(value = "左1压力")
    private String leftOnePress;
    /**
     * 左1到位关闭
     */
    @ApiModelProperty(value = "左1到位关闭")
    private String leftOneClose;
    /**
     * 左2时间
     */
    @ApiModelProperty(value = "左2时间")
    private String leftTwoTime;
    /**
     * 左2位置
     */
    @ApiModelProperty(value = "左2位置")
    private String leftTwoLocal;
    /**
     * 左2速度
     */
    @ApiModelProperty(value = "左2速度")
    private String leftTwoSpeed;
    /**
     * 左2压力
     */
    @ApiModelProperty(value = "左2压力")
    private String leftTwoPress;
    /**
     * 左2到位关闭
     */
    @ApiModelProperty(value = "左2到位关闭")
    private String leftTwoClose;
    /**
     * 顶针前顶时间
     */
    @ApiModelProperty(value = "顶针前顶时间")
    private String topTime;
    /**
     * 顶针前顶位置
     */
    @ApiModelProperty(value = "顶针前顶位置")
    private String topLocal;
    /**
     * 顶针前顶速度
     */
    @ApiModelProperty(value = "顶针前顶速度")
    private String topSpeed;
    /**
     * 顶针前顶压力
     */
    @ApiModelProperty(value = "顶针前顶压力")
    private String topPress;
    /**
     * 顶针前顶到位关闭
     */
    @ApiModelProperty(value = "顶针前顶到位关闭")
    private String topClose;
    /**
     * 正偏差时间
     */
    @ApiModelProperty(value = "正偏差时间")
    private String plusTime;
    /**
     * 正偏差位置
     */
    @ApiModelProperty(value = "正偏差位置")
    private String plusLocal;
    /**
     * 正偏差速度
     */
    @ApiModelProperty(value = "正偏差速度")
    private String plusSpeed;
    /**
     * 正偏差压力
     */
    @ApiModelProperty(value = "正偏差压力")
    private String plusPress;
    /**
     * 正偏差到位关闭
     */
    @ApiModelProperty(value = "正偏差到位关闭")
    private String plusClose;
    /**
     * 负偏差时间
     */
    @ApiModelProperty(value = "负偏差时间")
    private String loseTime;
    /**
     * 负偏差位置
     */
    @ApiModelProperty(value = "负偏差位置")
    private String loseLocal;
    /**
     * 负偏差速度
     */
    @ApiModelProperty(value = "负偏差速度")
    private String loseSpeed;
    /**
     * 负偏差压力
     */
    @ApiModelProperty(value = "负偏差压力")
    private String losePress;
    /**
     * 负偏差到位关闭
     */
    @ApiModelProperty(value = "负偏差到位关闭")
    private String loseClose;
    /**
     * 顶针退回时间
     */
    @ApiModelProperty(value = "顶针退回时间")
    private String backTime;
    /**
     * 顶针退回位置
     */
    @ApiModelProperty(value = "顶针退回位置")
    private String backLocal;
    /**
     * 顶针退回速度
     */
    @ApiModelProperty(value = "顶针退回速度")
    private String backSpeed;
    /**
     * 顶针退回压力
     */
    @ApiModelProperty(value = "顶针退回压力")
    private String backPress;
    /**
     * 顶针退回到位关闭
     */
    @ApiModelProperty(value = "顶针退回到位关闭")
    private String backClose;
    /**
     * 右1时间
     */
    @ApiModelProperty(value = "右1时间")
    private String rightOneTime;
    /**
     * 右1位置
     */
    @ApiModelProperty(value = "右1位置")
    private String rightOneLocal;
    /**
     * 右1速度
     */
    @ApiModelProperty(value = "右1速度")
    private String rightOneSpeed;
    /**
     * 右1压力
     */
    @ApiModelProperty(value = "右1压力")
    private String rightOnePress;
    /**
     * 右1到位关闭
     */
    @ApiModelProperty(value = "右1到位关闭")
    private String rightOneClose;
    /**
     * 右2时间
     */
    @ApiModelProperty(value = "右2时间")
    private String rightTwoTime;
    /**
     * 右2位置
     */
    @ApiModelProperty(value = "右2位置")
    private String rightTwoLocal;
    /**
     * 右2速度
     */
    @ApiModelProperty(value = "右2速度")
    private String rightTwoSpeed;
    /**
     * 右2压力
     */
    @ApiModelProperty(value = "右2压力")
    private String rightTwoPress;
    /**
     * 右2到位关闭
     */
    @ApiModelProperty(value = "右2到位关闭")
    private String rightTwoClose;
    /**
     * 右延时位置
     */
    @ApiModelProperty(value = "右延时位置")
    private String rightDelayLocal;
    /**
     * 右延时速度
     */
    @ApiModelProperty(value = "右延时速度")
    private String rightDelaySpeed;
    /**
     * 右延时压力
     */
    @ApiModelProperty(value = "右延时压力")
    private String rightDelayPress;
    /**
     * 右延时到位关闭
     */
    @ApiModelProperty(value = "右延时到位关闭")
    private String rightDelayClose;
    /**
     * 次品顶出锁定
     */
    @ApiModelProperty(value = "次品顶出锁定")
    private String ejectorLock;
    /**
     * 次数/多次行程回退位置
     */
    @ApiModelProperty(value = "次数/多次行程回退位置")
    private String multBackLocat;
}