package com.fw.web.quality.controller;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspection;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityInspectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 检验规范 前端控制器
 *
 * @author xkliu
 * @since 2020-11-24
 */
@RestController
@RequestMapping(value = "/qualityInspection")
@Api(description = "检验规范", value = "QualityInspectionController")
public class QualityInspectionController {

    @Autowired
    private QualityInspectionService qualityInspectionService;

    /**
     * 新增检验规范
     *
     * @param qualityInspection
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("新增检验规范")
    public Result save(@Valid @RequestBody QualityInspection qualityInspection, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return qualityInspectionService.save(qualityInspection);
        }
        return result;
    }

    /**
     * 变更检验规范
     *
     * @param qualityInspection
     * @return
     */
    @PostMapping("/change")
    @ApiOperation("变更检验规范")
    public Result change(@Valid @RequestBody QualityInspection qualityInspection, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return qualityInspectionService.change(qualityInspection);
        }
        return result;
    }

    /**
     * 列表查询
     *
     * @param code
     * @param productName
     * @param classify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "preciseCode", required = false) String preciseCode,
                           @RequestParam(value = "productName", required = false) String productName,
                           @RequestParam(value = "classify", required = false) Integer classify,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return qualityInspectionService.findList(code, preciseCode, productName, classify, pageNum, pageSize);
    }

    /**
     * 查看检验规范详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getInspectionDetail")
    @ApiOperation("查看检验规范详情")
    public Result getInspectionDetail(@RequestParam(value = "id") Integer id) {
        return qualityInspectionService.getInspectionDetail(id);
    }

    /**
     * 查看检验规范变更记录
     *
     * @param id
     * @return
     */
    @GetMapping("/getInspectionChangeLog")
    @ApiOperation("查看检验规范变更记录")
    public Result getInspectionChangeLog(@RequestParam(value = "id") Integer id) {
        return qualityInspectionService.getInspectionChangeLog(id);
    }

}
