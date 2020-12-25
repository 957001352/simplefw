package com.fw.service.plan.service;

import com.fw.domain.Result;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.ReworkStopList;

import java.util.Map;

/**
 * 二次加工排产计划
 * @author lpsong
 * @since 2020-11-25
 */
public interface ReworkInjectionService {

    /**
     * 新增
     * @param reworkInjection
     * @return
     */
    Result insert(ReworkInjection reworkInjection);

    /**
     * 修改
     * @param reworkInjection
     * @return
     */
    Result update(ReworkInjection reworkInjection);
    /**
     * 上移
     */
    Result moveUp(ReworkInjection reworkInjection);
    /**
     * 下移
     * @param reworkInjection
     * @return
     */
    Result moveDown(ReworkInjection reworkInjection);
    /**
     * 暂停
     * @param reworkStopList
     * @return
     */
    Result stop(ReworkStopList reworkStopList);

    /**
     * 取消
     * @param reworkInjection
     */
    Result cancel(ReworkInjection reworkInjection);



    /**
    *  列表查询
     * @param productCode
     * @param partsCode
     * @param startTime
     * @param endTime
     * @param status
     * @param pageNum
     * @param pageSize
    * @return
    */
    Result findList(String productCode,
                    String partsCode,
                    String startTime,
                    String endTime,
                    String status,
                    Integer pageNum,
                    Integer pageSize);

    /**
     * 创建生产指令  计算该生产指令的开始时间和结束时间
     */
    Map<String,String> createProductCode(Double productTime);
}
