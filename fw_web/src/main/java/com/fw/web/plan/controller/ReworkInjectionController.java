package com.fw.web.plan.controller;

import com.fw.domain.Result;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.ReworkStopList;
import com.fw.utils.ResultUtils;
import com.fw.web.plan.service.ReworkInjectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 二次加工排产计划
 * @author gchen
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/reworkInjection")
@Api(description = "二次加工排产计划",value = "ReworkInjectionController")
public class ReworkInjectionController {

    @Autowired
    private ReworkInjectionService reworkInjectionService;

    /**
     * 新增
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增")
    public Result insert(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.insert(reworkInjection);
    }
    /**
     * 编辑
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.update(reworkInjection);
    }
    /**
     * 上移
     */
    @PostMapping(value = "/moveUp")
    @ApiOperation(value = "上移")
    public Result moveUp(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.moveUp(reworkInjection);
    }
    /**
     * 下移
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/moveDown")
    @ApiOperation(value = "下移")
    public Result moveDown(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.moveDown(reworkInjection);
    }
    /**
     * 取消
     * @param reworkInjection
     */
    @PostMapping(value = "/cancel")
    @ApiOperation(value = "取消")
    public Result cancel(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.cancel(reworkInjection);
    }
    /**
     * 暂停
     * @param reworkStopList
     * @return
     */
    @PostMapping(value = "/stop")
    @ApiOperation(value = "暂停")
    public Result stop(@RequestBody ReworkStopList reworkStopList) {
        return reworkInjectionService.stop(reworkStopList);
    }
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
    @ApiOperation(value = "列表查询")
    public Result findList(@RequestParam(value = "productCode", required = false) String productCode,
                           @RequestParam(value = "partsCode", required = false) String partsCode,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return reworkInjectionService.findList(productCode, partsCode, startTime, endTime, status, pageNum, pageSize);
    }

    /**
     * 生成生产指令 获取生产指令和开始时间和结束时间
     */
    @GetMapping("/createProductCode")
    @ApiOperation(value = "生成生产指令 获取生产指令和开始时间和结束时间")
    public Result createProductCode(@RequestParam(value = "productTime", required = false) Double productTime) {
        return reworkInjectionService.createProductCode(productTime);
    }
}
