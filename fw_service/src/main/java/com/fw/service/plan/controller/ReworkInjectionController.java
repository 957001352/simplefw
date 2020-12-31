package com.fw.service.plan.controller;

import com.fw.domain.Result;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.ReworkStopList;
import com.fw.service.plan.service.ReworkInjectionService;
import com.fw.utils.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 二次加工排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/reworkInjection")
public class ReworkInjectionController {

    @Autowired
    private ReworkInjectionService reworkInjectionService;

    /**
     * 新增
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/insert")
    @RequiresPermissions("reworkInjection:insert")
    public Result insert(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.insert(reworkInjection);
    }
    /**
     * 编辑
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/update")
    @RequiresPermissions("reworkInjection:update")
    public Result update(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.update(reworkInjection);
    }
    /**
     * 上移
     */
    @PostMapping(value = "/moveUp")
    @RequiresPermissions("reworkInjection:moveUp")
    public Result moveUp(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.moveUp(reworkInjection);
    }
    /**
     * 下移
     * @param reworkInjection
     * @return
     */
    @PostMapping(value = "/moveDown")
    @RequiresPermissions("reworkInjection:moveDown")
    public Result moveDown(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.moveDown(reworkInjection);
    }
    /**
     * 取消
     * @param reworkInjection
     */
    @PostMapping(value = "/cancel")
    @RequiresPermissions("reworkInjection:cancel")
    public Result cancel(@RequestBody ReworkInjection reworkInjection) {
        return reworkInjectionService.cancel(reworkInjection);
    }
    /**
     * 暂停
     * @param reworkStopList
     * @return
     */
    @PostMapping(value = "/stop")
    @RequiresPermissions("reworkInjection:stop")
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
    @RequiresAuthentication
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
    @RequiresAuthentication
    public Result createProductCode(@RequestParam(value = "productTime", required = false) Double productTime) {
        return ResultUtils.success(reworkInjectionService.createProductCode(productTime));
    }
}
