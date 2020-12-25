package com.fw.web.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldRepairItem;
import com.fw.web.mould.service.MouldRepairItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-22 15:36
 **/
@RestController
@RequestMapping(value = "/mouldRepairItem")
@Api(description = "模具维修项目", value = "MouldRepairItemController")
public class MouldRepairItemController {

    @Autowired
    private MouldRepairItemService mouldRepairItemService;

    /**
     * 新增或修改
     *
     * @param mouldRepairItem
     * @return
     */
    @ApiOperation(value= "新增/修改")
    @PostMapping(value = "/save")
    public Result save(@RequestBody MouldRepairItem mouldRepairItem) {
        return mouldRepairItemService.save(mouldRepairItem);
    }

    /**
     * 获取列表
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value= "获取维修项目列表")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldRepairItemService.findList(name, pageNum, pageSize);
    }

    /**
     * 删除
     */
    @ApiOperation(value= "批量删除维修项目列表")
    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return mouldRepairItemService.delete(ids);
    }
}
