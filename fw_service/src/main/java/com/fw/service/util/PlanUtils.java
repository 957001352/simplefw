package com.fw.service.util;

import com.fw.entity.plan.InjectionMolding;
import com.fw.utils.DateUtils;

public class PlanUtils {
    //根据生产指令的开始时间和生产时间获取预计完成时间
    public static String getEndTime(InjectionMolding injectionMolding){
        int min = injectionMolding.getProductTime().intValue();
        return DateUtils.getAddTime(injectionMolding.getStartTime(), min);
    }
    //获取生产指令生产时间--->计划生产数量/模具型腔数*周期+首件调试时间+首件检验时间+在线保养时间（30min）
    public static Integer getProductTime(double cycle,double count,String cavity,double firstDebugTime,
                                        double firstCheckTime,double keepTime){
        int i = Integer.parseInt(cavity);//模具的型腔数
        int min = (int) (Math.ceil(Math.ceil(count/i)*cycle/60) + firstDebugTime + firstCheckTime + keepTime);
        return min;
    }
}
