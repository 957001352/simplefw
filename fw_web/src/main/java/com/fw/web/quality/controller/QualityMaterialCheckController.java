package com.fw.web.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.web.quality.service.QualityMaterialCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-25 17:36
 **/

@RestController
@RequestMapping(value = "/qualityMaterialCheck")
@Api(description = "来料检验管理", value = "QualityMaterialCheckController")
public class QualityMaterialCheckController {

    @Autowired
    private QualityMaterialCheckService qualityMaterialCheckService;

    @ApiOperation("获取来料检验任务信息")
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "checkNo",required = false) String checkNo,
                    @RequestParam(value = "startTime",required = false) String startTime,
                    @RequestParam(value = "stopTime",required = false) String stopTime,
                    @RequestParam(value = "exeStartTime",required = false) String exeStartTime,
                    @RequestParam(value = "exeStopTime",required = false) String exeStopTime,
                    @RequestParam(value = "status",required = false) Integer status,
                    @RequestParam(value = "pageNum",required = true,defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize",required = true,defaultValue = "10") Integer pageSize){
        return qualityMaterialCheckService.findList(checkNo,startTime,stopTime,exeStartTime,exeStopTime,status,pageNum,pageSize);
    }

    @ApiOperation("根据来料检验id获取检验物料信息")
    @GetMapping("/getCheckoutMaterialInfoById")
    Result getCheckoutMaterialInfoById(@RequestParam(value = "id",required = true) Integer id){
        return qualityMaterialCheckService.getCheckoutMaterialInfoById(id);
    }

    @ApiOperation("提交质检结果")
    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityMaterialCheckService.submit(qualityInspectResultList);
    }

    @ApiOperation("根据任务ID查询质检结果")
    @GetMapping("/findResultByDataIdAndClassify")
    Result findResultByDataIdAndClassify(@RequestParam(value = "id",required = true) Integer id){
        return qualityMaterialCheckService.findResultByDataIdAndClassify(id);
    }
}
