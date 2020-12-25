package com.fw.service.plan.service;

import com.fw.domain.Result;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.PlanSwap;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 注塑排产计划
 * @author lpsong
 * @since 2020-11-25
 */
public interface InjectionMoldingService {

    /**
     * 新增
     * @param injectionMolding
     * @return
     */
    Result insert(InjectionMolding injectionMolding);

    /**
     * 修改
     * @param injectionMolding
     * @return
     */
    Result update(InjectionMolding injectionMolding);
    /**
     * 暂停
     * @param injectionStopList
     * @return
     */
    Result stop(InjectionStopList injectionStopList);
    /**
    *  列表查询
     * @param productDevicesId
     * @param productDeviceCode
     * @param productCode
     * @param partsCode
     * @param startTime
     * @param endTime
     * @param status
     * @param pageNum
     * @param pageSize
    * @return
    */
    Result findList(Integer productDevicesId,
                    String productDeviceCode,
                    String productCode,
                    String partsCode,
                    String startTime,
                    String endTime,
                    String status,
                    Integer pageNum,
                    Integer pageSize);

    /**
     * 预排产列表
     * @return
     */
    Result findPlanList(InjectionMolding injectionMolding);

    /**
     * 上移 下移 取消
     */
    Result moveUpDownCancel(PlanSwap planSwap);
}
