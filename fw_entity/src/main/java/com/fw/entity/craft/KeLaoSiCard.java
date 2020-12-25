package com.fw.entity.craft;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 克劳斯玛菲-注塑成型工艺卡
 * @Author lpsong
 * @Date 2020/11/4
 */
@Data
@ApiModel(value = "keLaoSiCard", description = "克劳斯玛菲-注塑成型工艺卡")
public class KeLaoSiCard extends CardCommParams implements Serializable {
    /**
     * 保压段数
     */
    @ApiModelProperty(value = "保压段数")
    private String holdNum;
    /**
     * 注射段数
     */
    @ApiModelProperty(value = "注射段数")
    private String shootNum;

    /**
     * 速度1
     */
    @ApiModelProperty(value = "速度1")
    private String oneSpeed;
    /**
     * 速度2
     */
    @ApiModelProperty(value = "速度2")
    private String twoSpeed;
    /**
     * 速度3
     */
    @ApiModelProperty(value = "速度3")
    private String threeSpeed;
    /**
     * 速度4
     */
    @ApiModelProperty(value = "速度4")
    private String fourSpeed;
    /**
     * 速度5
     */
    @ApiModelProperty(value = "速度5")
    private String fiveSpeed;
    /**
     * 压力1
     */
    @ApiModelProperty(value = "压力1")
    private String oneStress;
    /**
     * 压力2
     */
    @ApiModelProperty(value = "压力2")
    private String twoStress;
    /**
     * 压力3
     */
    @ApiModelProperty(value = "压力3")
    private String threeStress;
    /**
     * 压力4
     */
    @ApiModelProperty(value = "压力4")
    private String fourStress;
    /**
     * 压力5
     */
    @ApiModelProperty(value = "压力5")
    private String fiveStress;
    /**
     * 到1
     */
    @ApiModelProperty(value = "到1")
    private String oneReach;
    /**
     * 到2
     */
    @ApiModelProperty(value = "到2")
    private String twoReach;
    /**
     * 到3
     */
    @ApiModelProperty(value = "到3")
    private String threeReach;
    /**
     * 到4
     */
    @ApiModelProperty(value = "到4")
    private String fourReach;
    /**
     * 到5
     */
    @ApiModelProperty(value = "到5")
    private String fiveReach;
    /**
     * 时间1
     */
    @ApiModelProperty(value = "时间1")
    private String oneTime;
    /**
     * 时间2
     */
    @ApiModelProperty(value = "时间2")
    private String twoTime;
    /**
     * 时间3
     */
    @ApiModelProperty(value = "时间3")
    private String threeTime;
    /**
     * 时间4
     */
    @ApiModelProperty(value = "时间4")
    private String fourTime;
    /**
     * 时间5
     */
    @ApiModelProperty(value = "时间5")
    private String fiveTime;
    /**
     * 螺杆位置1
     */
    @ApiModelProperty(value = "螺杆位置1")
    private String oneLocation;
    /**
     * 螺杆位置2
     */
    @ApiModelProperty(value = "螺杆位置2")
    private String twoLocation;
    /**
     * 射胶时间模式1
     */
    @ApiModelProperty(value = "射胶时间模式1")
    private String oneTimeModel;
    /**
     * 射胶时间模式2
     */
    @ApiModelProperty(value = "射胶时间模式2")
    private String twoTimeModel;

    /**
     * 射胶压力1
     */
    @ApiModelProperty(value = "射胶压力1")
    private String oneShootStress;
    /**
     * 射胶压力2
     */
    @ApiModelProperty(value = "射胶压力2")
    private String twoShootStress;
    /**
     * 注射延时1
     */
    @ApiModelProperty(value = "注射延时1")
    private String oneShootDelay;
    /**
     * 注射延时2
     */
    @ApiModelProperty(value = "注射延时2")
    private String twoShootDelay;
    /**
     * 冷却时间1
     */
    @ApiModelProperty(value = "冷却时间1")
    private String oneCoolTime;
    /**
     * 冷却时间2
     */
    @ApiModelProperty(value = "冷却时间2")
    private String twoCoolTime;

    /**----------------表格二 熔胶段数--------**/

    /**
     * 熔胶段数
     */
    @ApiModelProperty(value = "熔胶段数")
    private String meltNum;
    /**
     * 松退-熔胶前
     */
    @ApiModelProperty(value = "松退-熔胶前")
    private String looseNum;
    /**
     * 速度1
     */
    @ApiModelProperty(value = "速度1")
    private String looseOneSpeed;
    /**
     * 速度2
     */
    @ApiModelProperty(value = "速度2")
    private String looseTwoSpeed;
    /**
     * 速度3
     */
    @ApiModelProperty(value = "速度3")
    private String looseThreeSpeed;
    /**
     * 背压1
     */
    @ApiModelProperty(value = "背压1")
    private String looseOnePress;
    /**
     * 背压2
     */
    @ApiModelProperty(value = "背压2")
    private String looseTwoPress;
    /**
     * 背压3
     */
    @ApiModelProperty(value = "背压3")
    private String looseThreePress;
    /**
     * 位置0
     */
    @ApiModelProperty(value = "位置0")
    private String looseZeroLocat;
    /**
     * 位置1
     */
    @ApiModelProperty(value = "位置1")
    private String looseOneLocat;
    /**
     * 位置2
     */
    @ApiModelProperty(value = "位置2")
    private String looseTwoLocat;
    /**
     * 位置3
     */
    @ApiModelProperty(value = "位置3")
    private String looseThreeLocat;
    /**
     * 位置4
     */
    @ApiModelProperty(value = "位置4")
    private String looseFourLocat;
    /**
     * 松退-熔胶前压力
     */
    @ApiModelProperty(value = "松退-熔胶前压力")
    private String meltBeforeStress;
    /**
     * 松退-熔胶前速度
     */
    @ApiModelProperty(value = "松退-熔胶前速度")
    private String meltBeforeSpeed;
    /**
     * 松退-熔胶前时间
     */
    @ApiModelProperty(value = "松退-熔胶前时间")
    private String meltBeforeTime;
    /**
     * 松退-熔胶后压力
     */
    @ApiModelProperty(value = "松退-熔胶后压力")
    private String meltAfterStress;
    /**
     * 松退-熔胶后速度
     */
    @ApiModelProperty(value = "松退-熔胶后速度")
    private String meltAfterSpeed;
    /**
     * 松退-熔胶后时间
     */
    @ApiModelProperty(value = "松退-熔胶后时间")
    private String meltAfterTime;
    /**
     * 熔胶扭矩
     */
    @ApiModelProperty(value = "熔胶扭矩")
    private String distance;
    /**
     * 熔胶延时
     */
    @ApiModelProperty(value = "熔胶延时")
    private String delay;
    /**----------------料筒加热---------------**/
    /**
     * 料筒加热设定值 1#
     */
    @ApiModelProperty(value = "料筒加热设定值 1#")
    private String heatOneValue;
    /**
     * 料筒加热设定值 2#
     */
    @ApiModelProperty(value = "料筒加热设定值 2#")
    private String heatTwoValue;
    /**
     * 料筒加热设定值 3#
     */
    @ApiModelProperty(value = "料筒加热设定值 3#")
    private String heatThreeValue;
    /**
     * 料筒加热设定值 4#
     */
    @ApiModelProperty(value = "料筒加热设定值 4#")
    private String heatFourValue;
    /**
     * 料筒加热设定值 5#
     */
    @ApiModelProperty(value = "料筒加热设定值 5#")
    private String heatFiveValue;

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


}