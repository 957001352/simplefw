package com.fw.service.craft.controller;

import com.fw.domain.Result;
import com.fw.entity.craft.CraftModel;
import com.fw.entity.device.DevicesRepair;
import com.fw.service.craft.service.CraftModelService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-06 11:40
 **/
@RestController
@RequestMapping("/craftModel")
public class CraftModelController {

    @Autowired
    private CraftModelService craftModelService;

    @PostMapping(value = "/save")
    @RequiresPermissions(value = "craftModel:save")
    public Result save(@RequestBody CraftModel craftModel) {
        return craftModelService.save(craftModel);
    }


    @GetMapping(value = "/delete")
    @RequiresPermissions(value = "craftModel:delete")
    public Result delete(@RequestParam("ids") String ids) {
        return craftModelService.delete(ids);
    }


    @GetMapping(value = "/findList")
    @RequiresAuthentication
    public Result findList(
            @RequestParam(value = "productCode", required = false) String productCode,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "modelType", required = false) Integer modelType,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return craftModelService.findList(productCode, productName, modelType, pageNum, pageSize);
    }

    @GetMapping(value = "/findByProductCode")
    @RequiresAuthentication
    public Result findByProductCode(@RequestParam(value = "productCode", required = false) String productCode) {
        return craftModelService.findByProductCode(productCode);
    }
}