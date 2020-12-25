package com.fw.entity.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* @Description:    设备履历实体类
* @Author:         gchen
* @CreateDate:     2020/10/23 11:17
*/
@Data
@ApiModel(value = "devicesExtension", description = "设备履历")
public class DevicesExtension  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "E2C设备id")
    private String productDevicesId; // E2C设备id
    @ApiModelProperty(value = "射嘴长度")
    private Double shzcd;               // 射嘴长度
    @ApiModelProperty(value = "射嘴外径")
    private Double shzwj;               // 射嘴外径
    @ApiModelProperty(value = "射出容量")
    private Double shchrl;             // 射出容量
    @ApiModelProperty(value = "射出重量")
    private Double shechzhl;            // 射出重量
    @ApiModelProperty(value = "螺杆直径")
    private Double lgzhj;              // 螺杆直径
    @ApiModelProperty(value = "螺杆塑化能力")
    private Double lgshnl;             // 螺杆塑化能力
    @ApiModelProperty(value = "射出压力")
    private Double shchyl;             // 射出压力
    @ApiModelProperty(value = "顶出力")
    private Double dchl;               // 顶出力
    @ApiModelProperty(value = "顶杆数量")
    private Double dgshl;              // 顶杆数量
    @ApiModelProperty(value = "顶杆空直径")
    private Double dgkzhj;             // 顶杆空直径
    @ApiModelProperty(value = "顶针行程")
    private Double dzhxch;             // 顶针行程
    @ApiModelProperty(value = "模具最大闭合厚度")
    private Double mjzdbhhd;           // 模具最大闭合厚度
    @ApiModelProperty(value = "最大开模行程")
    private Double zdkmxch;            // 最大开模行程
    @ApiModelProperty(value = "导柱间距(HV)")
    private String dzhjjHv;          // 导出间距(HV)
    @ApiModelProperty(value = "最小模具尺寸")
    private Double zxmjchc;           // 最小模具尺寸
    @ApiModelProperty(value = "油压中子")
    private Double yyzhz;              // 油压中子
    @ApiModelProperty(value = "气压中子")
    private Double qyzhz;             // 气压中子
    @ApiModelProperty(value = "定位环直径")
    private Double dwhzhj;             // 定位环直径
    @ApiModelProperty(value = "射嘴球面半径")
    private Double shzqmbj;            // 射嘴球面半径
    @ApiModelProperty(value = "射嘴接触压力")
    private Double shzjchyl;           // 射嘴接触压力
    @ApiModelProperty(value = "设备尺寸")
    private String shbchc;              // 设备尺寸
    @ApiModelProperty(value = "设备重量")
    private Double shbzhl;              // 设备重量
    @ApiModelProperty(value = "液压流量")
    private Double yyll;                // 液压流量
    @ApiModelProperty(value = "电机功率")
    private Double djgl;               // 电机功率
    @ApiModelProperty(value = "加热功率")
    private Double jrgl;                // 加热功率
    @ApiModelProperty(value = "机械手")
    private Double jxsh;               // 机械手
    @ApiModelProperty(value = "入场时间")
    private String intoTime;            // 入场时间
    @ApiModelProperty(value = "设备状态 0:正常 2:报废")
    private Integer status;             //设备状态 0:正常 2:报废

    @ApiModelProperty(value = "射嘴孔径")
    private Double shzkj;             //射嘴孔径
    @ApiModelProperty(value = "模板尺寸")
    private Double mbchc;             //模板尺寸

    @ApiModelProperty(value = "该设备是否在进行报废审核")
    private Integer auditStatus;                                 // 该设备是否在进行报废审核 0-没有报废申请 1-报废申请正在审核中

    @ApiModelProperty(value = "自定义属性")
    private String extendAttr;
}
