package com.fw.entity.plan;

import lombok.Data;

/**
 * 用作前端给后台传操作的两个生产指令
 */
@Data
public class PlanSwap {
    private InjectionMolding upInjectionMolding; //上面的生产指令
    private InjectionMolding downInjectionMolding; //下面的生产指令

}
