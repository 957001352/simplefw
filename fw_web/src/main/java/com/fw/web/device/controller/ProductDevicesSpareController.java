package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.ProductDevicesSpareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description: 备品备件web controller
 */
@RestController
@RequestMapping(value = "/productDevicesSpare")
@Api(description = "备品备件", value = "ProductDevicesSpareController")
public class ProductDevicesSpareController {

    @Autowired
    private ProductDevicesSpareService productDevicesSpareService;

    /**
     * 新增/修改备品备件
     *
     * @param productDevicesSpare
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody ProductDevicesSpare productDevicesSpare, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return productDevicesSpareService.save(productDevicesSpare);
        }
        return result;
    }

    /**
     * 查看备品
     *
     * @param id
     * @return
     */
    @ApiOperation("查看备品")
    @GetMapping("/getProductDevicesSpare")
    public Result getProductDevicesSpare(@RequestParam(value = "id") Integer id) {
        return productDevicesSpareService.getProductDevicesSpare(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return productDevicesSpareService.delete(ids);
    }

    /**
     * 列表全查
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result FindList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return productDevicesSpareService.findList(code,name,pageNum, pageSize);
    }
}
