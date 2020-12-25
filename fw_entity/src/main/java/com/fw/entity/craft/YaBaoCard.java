package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 雅宝-注塑成型工艺卡
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "yaBaoCard", description = "雅宝-注塑成型工艺卡")
public class YaBaoCard extends CardCommParams implements Serializable {
    /**---------加热筒.作动油温度------**/
    /**
     * 一段温度
     */
    @ApiModelProperty(value = "一段温度")
    private String tempOne;
    /**
     * 二段温度
     */
    @ApiModelProperty(value = "二段温度")
    private String tempTwo;
    /**
     * 三段温度
     */
    @ApiModelProperty(value = "三段温度")
    private String tempThree;
    /**
     * 四段温度
     */
    @ApiModelProperty(value = "四段温度")
    private String tempFour;
    /**
     * 五段温度
     */
    @ApiModelProperty(value = "五段温度")
    private String tempFive;

    /**-----------------开模------------------**/
    /**
     * V开模1
     */
    @ApiModelProperty(value = "V开模1")
    private String openVmOne;
    /**
     * V开模2
     */
    @ApiModelProperty(value = "V开模2")
    private String openVmTwo;
    /**
     * V开模3
     */
    @ApiModelProperty(value = "V开模3")
    private String openVmThree;
    /**
     * F开模1
     */
    @ApiModelProperty(value = "F开模1")
    private String openFmOne;
    /**
     * F开模2
     */
    @ApiModelProperty(value = "F开模2")
    private String openFmTwo;
    /**
     * F开模3
     */
    @ApiModelProperty(value = "F开模3")
    private String openFmThree;
    /**
     * S开模1
     */
    @ApiModelProperty(value = "S开模1")
    private String openSmOne;
    /**
     * S开模2
     */
    @ApiModelProperty(value = "S开模2")
    private String openSmTwo;
    /**
     * S开模3
     */
    @ApiModelProperty(value = "S开模3")
    private String openSmThree;
    /**-----------------合模------------------**/
    /**
     * V合模1
     */
    @ApiModelProperty(value = "V合模1")
    private String closeVmOne;
    /**
     * V合模2
     */
    @ApiModelProperty(value = "V合模2")
    private String closeVmTwo;
    /**
     * V合模3
     */
    @ApiModelProperty(value = "V合模3")
    private String closeVmThree;
    /**
     * F合模1
     */
    @ApiModelProperty(value = "F合模1")
    private String closeFmOne;
    /**
     * F合模2
     */
    @ApiModelProperty(value = "F合模2")
    private String closeFmTwo;
    /**
     * F合模3
     */
    @ApiModelProperty(value = "F合模3")
    private String closeFmThree;
    /**
     * S合模1
     */
    @ApiModelProperty(value = "S合模1")
    private String closeSmOne;
    /**
     * S合模2
     */
    @ApiModelProperty(value = "S合模2")
    private String closeSmTwo;
    /**
     * S合模3
     */
    @ApiModelProperty(value = "S合模3")
    private String closeSmThree;
    /**-----------------注射------------------**/
    /**
     * V注射1
     */
    @ApiModelProperty(value = "V注射1")
    private String shootVmOne;
    /**
     * V注射2
     */
    @ApiModelProperty(value = "V注射2")
    private String shootVmTwo;
    /**
     * Q注射3
     */
    @ApiModelProperty(value = "V注射3")
    private String shootVmThree;
    /**
     * P注射1
     */
    @ApiModelProperty(value = "P注射1")
    private String shootPmOne;
    /**
     * P注射2
     */
    @ApiModelProperty(value = "P注射2")
    private String shootPmTwo;
    /**
     * P注射3
     */
    @ApiModelProperty(value = "P注射3")
    private String shootPmThree;
    /**
     * S注射1
     */
    @ApiModelProperty(value = "S注射1")
    private String shootSmOne;
    /**
     * S注射2
     */
    @ApiModelProperty(value = "S注射2")
    private String shootSmTwo;
    /**
     * S注射3
     */
    @ApiModelProperty(value = "S注射3")
    private String shootSmThree;

    /**---------------保压----------------**/
    /**
     * V保压1
     */
    @ApiModelProperty(value = "V保压1")
    private String holdVmOne;
    /**
     * V保压2
     */
    @ApiModelProperty(value = "V保压2")
    private String holdVmTwo;
    /**
     * V保压3
     */
    @ApiModelProperty(value = "V保压3")
    private String holdVmThree;
    /**
     * P保压1
     */
    @ApiModelProperty(value = "P保压1")
    private String holdPmOne;
    /**
     * P保压2
     */
    @ApiModelProperty(value = "P保压2")
    private String holdPmTwo;
    /**
     * P保压3
     */
    @ApiModelProperty(value = "P保压3")
    private String holdPmThree;
    /**
     * T保压1
     */
    @ApiModelProperty(value = "T保压1")
    private String holdTmOne;
    /**
     * T保压2
     */
    @ApiModelProperty(value = "T保压2")
    private String holdTmTwo;
    /**
     * T保压3
     */
    @ApiModelProperty(value = "T保压3")
    private String holdTmThree;
    /**---------------进料----------------**/
    /**
     * 剩余冷却时间
     */
    @ApiModelProperty(value = "剩余冷却时间")
    private String coolTime;
    /**
     * 延迟时间
     */
    @ApiModelProperty(value = "延迟时间")
    private String delyTime;
    /**
     * 预塑量
     */
    @ApiModelProperty(value = "预塑量")
    private String modelDistance;
    /**
     * 背压
     */
    @ApiModelProperty(value = "背压")
    private String press;
    /**
     * 预塑速度
     */
    @ApiModelProperty(value = "预塑速度")
    private String modelSpeed;
    /**---------------松退----------------**/
    /**
     * 松退速度
     */
    @ApiModelProperty(value = "松退速度")
    private String looseSpeed;
    /**
     * 松退量
     */
    @ApiModelProperty(value = "松退量")
    private String looseDistance;
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
}