package com.fw.entity.board;

import lombok.Data;
/**
* @Description:    品质参数
* @Author:         gchen
* @CreateDate:     2020/10/19 16:43
*/
@Data
public class QualityParam {
    //循环时间
    private String tmCycleTime;
    //射出时间
    private String tmInjTime;
    //转保压时间
    private String tmTurnTime;
    //储料时间
    private String tmChargeTime;
    //射出起点
    private String tmInjStartPosi;
    //射出终点
    private String tmInjEndPosi;
    //转保压位置
    private String tmTurnPosi;
    //转保压压力
    private String tmTurnPress;
    //射出尖压
    private String tmInjMaxPress;
}
