package com.fw.web.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepItem;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldKeepItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xkliu
 * @des 模具保养项目
 * @date 2020/10/27
 */
@RestController
@RequestMapping(value = "/mouldKeepItem")
@Api(description = "模具保养项目", value = "MouldKeepItemController")
public class MouldKeepItemController {

    @Autowired
    private MouldKeepItemService mouldKeepItemService;

    /**
     * 新增/修改模具保养项目
     *
     * @param mouldKeepItem
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改模具保养项目")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody MouldKeepItem mouldKeepItem, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return mouldKeepItemService.save(mouldKeepItem);
        }
        return result;
    }


    /**
     * 查看模具保养项目
     *
     * @param id
     * @return
     */
    @ApiOperation("查看模具保养项目")
    @GetMapping("/getMouldKeepItem")
    public Result getMouldKeepItem(@RequestParam(value = "id") Integer id) {
        return mouldKeepItemService.getMouldKeepItem(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return mouldKeepItemService.delete(ids);
    }

    /**
     * 列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepItemService.findList(name, pageNum, pageSize);
    }


}
