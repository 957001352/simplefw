package com.fw.service.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.service.quality.service.QualityStoreCheckService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:入库检验管理
 * @author: wqiang
 * @create: 2020-11-26 10:53
 **/
@RestController
@RequestMapping("/qualityStoreCheck")
public class QualityStoreCheckController {

    @Autowired
    private QualityStoreCheckService qualityStoreCheckService;

    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(
            @RequestParam(value = "productDevicesCode",required = false) String productDevicesCode,
            @RequestParam(value = "checkNo",required = false) String checkNo,
                           @RequestParam(value = "productCode",required = false) String productCode,
                           @RequestParam(value = "materialCode",required = false) String materialCode,
                           @RequestParam(value = "startTime",required = false) String startTime,
                           @RequestParam(value = "stopTime",required = false) String stopTime,
                          @RequestParam(value = "status",required = false) Integer status,
                           @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){

        return qualityStoreCheckService.findList(productDevicesCode,checkNo,productCode,materialCode,startTime,stopTime,status,pageNum,pageSize);
    }

    @GetMapping("/findAppearanceOrSizeInspectInfo")
    @RequiresAuthentication
    public Result findAppearanceOrSizeInspectInfo(@RequestParam(value = "id",required = true) Integer id){
        return qualityStoreCheckService.findAppearanceOrSizeInspectInfo(id);
    }

    @PostMapping("/submit")
    @RequiresPermissions(value = "qualityStoreCheck:submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityStoreCheckService.submit(qualityInspectResultList);
    }

    @PostMapping("/update")
    @RequiresPermissions(value = "qualityStoreCheck:update")
    Result update(@RequestBody QualityInspectResult qualityInspectResultList){
        return qualityStoreCheckService.update(qualityInspectResultList);
    }
}
