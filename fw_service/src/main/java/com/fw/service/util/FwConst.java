package com.fw.service.util;//

public class FwConst {
    public final static String DHLK_TENANTID= "dhlk-tenantId";//租户id

    public final static String POST= "POST";//POST请求
    public final static String GET= "GET";//GET请求

    public final static String GYK= "GYK";// 工艺卡前缀

    //Redis登录日志记住登录设备的token key
    public final static String  DEVICE_LOGIN_TOKEN_KEY= "DEVICE_LOGIN_TOKEN_KEY";
    //Redis记住设备是否在线 key:DEVICE_STATE_LOG_KEY  item:设备code  value:是否存入日志
    public final static String  DEVICE_STATE_LOG_KEY= "DEVICE_STATE_LOG_KEY";

    public final static String UPDATEPARAMKEY = "'tmUpdateContent','tmUpdateOld','tmUpdateNew','tmUpdateDateTime'";
//    public final static String tmChargeTime = "tmChargeTime";//储料时间
//    public final static String tmInjEndPosi = "tmInjEndPosi";//射出终点
//    public final static String tmClpOpnTime = "tmClpOpnTime";//开模时间
//    public final static String tmTurnPosi = "tmTurnPosi";//转保压位置
//    public final static String tmTemp7_CurrentB = "tmTemp7_CurrentB";//实际温度 7 B
//    public final static String tmTurnPress = "tmTurnPress";//转保压压力
//    public final static String tmClpOpnTimeMax = "tmClpOpnTimeMax";//开模时间上限
//    public final static String tmTemp8_Set = "tmTemp8_Set";//设定温度 8
//    public final static String tmTemp2_SetB = "tmTemp2_SetB";//设定温度 2B
//    public final static String tmInjMaxPress = "tmInjMaxPress";//射出尖压
//    public final static String tmTemp3_Current = "tmTemp3_Current";//实际温度 3
//    public final static String tmVendor = "tmVendor";//控制器类别
//    public final static String tmOnlineState = "tmOnlineState";//在线状态
//    public final static String tmAlarmSTime2 = "tmAlarmSTime2";//警报 2 开始时间
//    public final static String tmAlarmETime2 = "tmAlarmETime2";//警报 2 结束时间
//    public final static String tmTurnPressMax = "tmTurnPressMax";//转保压压力上限
//    public final static String tmAlarmETime3 = "tmAlarmETime3";//警报 3 结束时间
//    public final static String tmTurnPosiMax = "tmTurnPosiMax";//转保压位置上限
//    public final static String tmTemp5_Current = "tmTemp5_Current";//实际温度 5
//    public final static String tmInjMaxPressMax = "tmInjMaxPressMax";//射出尖压上限
//    public final static String tmPlanCount = "tmPlanCount";//计划数
//    public final static String tmHeatState = "tmHeatState";//电热状态
//    public final static String tmUpdateOld = "tmUpdateOld";//旧值
//    public final static String tmUpdateDateTime = "tmUpdateDateTime";//修改时间
//    public final static String tmTemp4_Set = "tmTemp4_Set";//设定温度 4
//    public final static String tmTemp3_Set = "tmTemp3_Set";//设定温度 3
//    public final static String tmTemp9_Set = "tmTemp9_Set";//设定温度 9
//    public final static String tmClpClsTime = "tmClpClsTime";//关模时间
//    public final static String tmTemp5_CurrentB = "tmTemp5_CurrentB";//实际温度 5 B
//    public final static String tmAlarmETime1 = "tmAlarmETime1";//警报 1 结束时间
//    public final static String tmAlarmSTime1 = "tmAlarmSTime1";//警报 1 开始时间
//    public final static String tmInjBackTimeMax = "tmInjBackTimeMax";//射退时间上限
//    public final static String tmTemp7_SetB = "tmTemp7_SetB";//设定温度 7B
//    public final static String tmTemp4_SetB = "tmTemp4_SetB";//设定温度 4B
//    public final static String tmTemp6_CurrentB = "tmTemp6_CurrentB";//实际温度 6 B
//    public final static String tmMachineIP= "tmMachineIP";//IP 地址
//    public final static String tmCycleTimeMax = "tmCycleTimeMax";//循环时间上限
//    public final static String tmTurnTimeMax = "tmTurnTimeMax";//转保压时间上限
//    public final static String tmEjectTimeMax = "tmEjectTimeMax";//托模时间上限
//    public final static String tmInjEndPosiMax = "tmInjEndPosiMax";//射出终点上限
//    public final static String tmAlarmID2 = "tmAlarmID2";//警报 2 信息
//    public final static String tmMaterial = "tmMaterial";//材料
//    public final static String tmCraftID = "tmCraftID";//模具名称
//    public final static String tmTemp1_Current = "tmTemp1_Current";//实际温度 1
//    public final static String tmChargeMaxPressMax = "tmChargeMaxPressMax";//储料尖压上限
//    public final static String tmTemp6_Current = "tmTemp6_Current";// 实际温度 6
//    public final static String tmTemp7_Current = "tmTemp7_Current";//实际温度 7
//    public final static String tmTemp9_Current = "tmTemp9_Current";//实际温度 9
//    public final static String tmLotNumber = "tmLotNumber";//生产批号
//    public final static String tmTemp2_Set = "tmTemp2_Set";//设定温度 2
//    public final static String tmTemp7_Set = "tmTemp7_Set";//设定温度 7
//    public final static String tmTemp4_CurrentB = "tmTemp4_CurrentB";//实际温度 4 B
//    public final static String tmTemp8_SetB = "tmTemp8_SetB";//设定温度 8B
//    public final static String tmMachineMac = "tmMachineMac";//网卡地址
//    public final static String tmClpClsTimeMax = "tmClpClsTimeMax";//关模时间上限
//    public final static String tmMoldCavity = "tmMoldCavity";//模穴数
//    public final static String tmUpdateContent = "tmUpdateContent";// 修改内容
//    public final static String tmInjTime = "tmInjTime";//射出时间
//    public final static String tmTemp4_Current = "tmTemp4_Current";//实际温度 4
//    public final static String tmTemp1_CurrentB = "tmTemp1_CurrentB";//实际温度 1 B
//    public final static String tmTemp9_CurrentB = "tmTemp9_CurrentB";//实际温度 9 B
//    public final static String tmTemp8_CurrentB = "tmTemp8_CurrentB";//实际温度 8 B
//    public final static String tmInjStartPosiMax = "tmInjStartPosiMax";//射出起点上限
//    public final static String tmTemp6_SetB = "tmTemp6_SetB";//设定温度 6B
//    public final static String tmTemp3_SetB = "tmTemp3_SetB";// 设定温度 3B
//    public final static String tmTemp9_SetB = "tmTemp9_SetB";// 设定温度 9B
//    public final static String  tmOperateMode = "tmOperateMode";//操作状态
//    public final static String  tmShotCount = "tmShotCount";//开模序号
//    public final static String  tmInjTimeMax = "tmInjTimeMax";//射出时间上限
//    public final static String  tmAlarmSTime3 = "tmAlarmSTime3";//警报 3 开始时间
//    public final static String  tmColor = "tmColor";//颜色
//    public final static String  tmTemp2_Current = "tmTemp2_Current";//实际温度 2
//    public final static String  tmTempOil_Current = "tmTempOil_Current";//油温
//    public final static String  tmCycleTime = "tmCycleTime";//循环时间
//    public final static String  tmTemp5_Set = "tmTemp5_Set";//设定温度 5
//    public final static String  tmTemp6_Set = "tmTemp6_Set";//设定温度 6
//    public final static String  tmAlarmID1 = "tmAlarmID1";// 警报 1 信息
//    public final static String  tmTemp2_CurrentB = "tmTemp2_CurrentB";// 实际温度 2 B
//    public final static String  tmTemp1_SetB = "tmTemp1_SetB";// 设定温度 1B
//    public final static String  tmTemp8_Current = "tmTemp8_Current";//实际温度 8
//    public final static String  tmTemp3_CurrentB = "tmTemp3_CurrentB";//实际温度 3 B
//    public final static String  tmAlarmState = "tmAlarmState";//警报状态
//    public final static String  tmTemp1_Set = "tmTemp1_Set";//设定温度 1
//    public final static String  tmBadShotCount = "tmBadShotCount";//不良品总数
//    public final static String  tmClpOpnPosiMax = "tmClpOpnPosiMax";//开模位置上限
//    public final static String  tmChargeTimeMax = "tmChargeTimeMax";//储料时间上限
//    public final static String  tmTemp5_SetB = "tmTemp5_SetB";//设定温度 5B
//    public final static String  tmTurnTime = "tmTurnTime";//转保压时间
//    public final static String  tmAlarmID3 = "tmAlarmID3";//警报 3 信息
}
