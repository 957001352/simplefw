package com.fw.web.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.web.quality.service.QualityStoreCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-26 11:04
 **/


@RestController
@RequestMapping(value = "/qualityStoreCheck")
@Api(description = "入库检验管理", value = "QualityStoreCheckController")
public class QualityStoreCheckController {

    @Autowired
    private QualityStoreCheckService qualityStoreCheckService;


    @ApiOperation("获取列表")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "productDevicesCode",required = false) String productDevicesCode,
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


    @ApiOperation("根据id查询检验信息")
    @GetMapping("/findAppearanceOrSizeInspectInfo")
    public Result findAppearanceOrSizeInspectInfo(@RequestParam(value = "id",required = true) Integer id){
        return qualityStoreCheckService.findAppearanceOrSizeInspectInfo(id);
    }

    @ApiOperation("提交入库检验结果")
    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityStoreCheckService.submit(qualityInspectResultList);
    }

    @ApiOperation("更新入库检验结果")
    @PostMapping("/update")
    Result update(@RequestBody QualityInspectResult qualityInspectResultList){
        return qualityStoreCheckService.update(qualityInspectResultList);
    }
}
