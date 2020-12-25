package com.fw.web.craft.controller;

import com.fw.domain.Result;
import com.fw.entity.craft.CraftModel;
import com.fw.web.craft.service.CraftModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-06 11:49
 **/

@RestController
@RequestMapping(value = "/craftModel")
@Api(description = "注塑工艺模型", value = "CraftModelController")
public class CraftModelController {


    @Autowired
    private CraftModelService craftModelService;

    @ApiOperation("新增/修改")
    @PostMapping(value = "/save")
    public Result save(@RequestBody CraftModel craftModel) {
        return craftModelService.save(craftModel);
    }


    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam("ids") String ids) {
        return craftModelService.delete(ids);
    }


    @ApiOperation("获取列表")
    @GetMapping(value = "/findList")
    public Result findList(
            @RequestParam(value = "productCode", required = false) String productCode,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "modelType", required = false) Integer modelType,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return craftModelService.findList(productCode, productName, modelType, pageNum, pageSize);

    }

    /**
     * 根据物料长代码查询工艺卡模型
     * @param productCode
     * @return
     */
    @GetMapping(value = "/findByProductCode")
    @ApiOperation("根据物料长代码查询工艺卡模型")
    public Result findByProductCode(@RequestParam(value = "productCode", required = false) String productCode) {
        return craftModelService.findByProductCode(productCode);
    }
}
