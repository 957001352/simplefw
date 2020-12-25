package com.fw.web.plan.service;

import com.fw.domain.Result;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.ReworkStopList;
import com.fw.web.plan.service.fbk.ReworkInjectionServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 二次加工排产计划
 * @author gchen
 * @since 2020-11-25
 */
@FeignClient(value = "fw-service/reworkInjection", fallback = ReworkInjectionServiceFbk.class)
public interface ReworkInjectionService {


    /**
     * 新增
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/insert")
    Result insert(@RequestBody ReworkInjection reworkInjection);
    /**
     * 编辑
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/update")
    Result update(@RequestBody ReworkInjection reworkInjection);
    /**
     * 上移
     */
    @PostMapping(value = "/moveUp")
    Result moveUp(@RequestBody ReworkInjection reworkInjection);
    /**
     * 下移
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/moveDown")
    Result moveDown(@RequestBody ReworkInjection reworkInjection);
    /**
     * 取消
     * @param reworkInjection
     */
    @PostMapping(value = "/cancel")
    Result cancel(@RequestBody ReworkInjection reworkInjection);
    /**
     * 暂停
     * @param reworkStopList
     * @return
     */
    @PostMapping(value = "/stop")
    Result stop(@RequestBody ReworkStopList reworkStopList);
    /**
     * 列表查询
     * @param productCode
     * @param partsCode
     * @param startTime
     * @param endTime
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "partsCode", required = false) String partsCode,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 生成生产指令 获取生产指令和开始时间和结束时间
     */
    @GetMapping("/createProductCode")
    Result createProductCode(@RequestParam(value = "productTime", required = false) Double productTime);
}
