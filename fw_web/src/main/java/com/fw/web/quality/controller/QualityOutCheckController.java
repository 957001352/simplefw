package com.fw.web.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.web.quality.service.QualityOutCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-26 17:15
 **/

@RestController
@RequestMapping(value = "/qualityOutCheck")
@Api(description = "出库检验管理", value = "QualityOutCheckController")
public class QualityOutCheckController {

    @Autowired
    private QualityOutCheckService qualityOutCheckService;


    @ApiOperation("获取列表")
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "checkNo",required = false) String checkNo,
                    @RequestParam(value = "customer",required = false) String customer,
                    @RequestParam(value = "materialCode",required = false) String materialCode,
                    @RequestParam(value = "startTime",required = false) String startTime,
                    @RequestParam(value = "stopTime",required = false)String stopTime,
                    @RequestParam(value = "status",required = false)Integer status,
                    @RequestParam(value = "pageNum",required = true,defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize",required = true,defaultValue = "10")Integer pageSize){
        return qualityOutCheckService.findList(checkNo,customer,materialCode,startTime,stopTime,status,pageNum,pageSize);
    }

    @ApiOperation("提交质检结果")
    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityOutCheckService.submit(qualityInspectResultList);
    }

    @ApiOperation("根据任务ID查询质检结果")
    @GetMapping("/findResultByDataIdAndClassify")
    Result findResultByDataIdAndClassify(@RequestParam(value = "id",required = true) Integer id){
        return qualityOutCheckService.findResultByDataIdAndClassify(id);
    }


    @ApiOperation("更新出库检验结果")
    @PostMapping("/update")
    Result update(@RequestBody QualityInspectResult qualityInspectResultList){
        return qualityOutCheckService.update(qualityInspectResultList);
    }
}
