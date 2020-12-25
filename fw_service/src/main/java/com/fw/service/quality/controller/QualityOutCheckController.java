package com.fw.service.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.service.quality.service.QualityOutCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-26 17:08
 **/

@RestController
@RequestMapping(value = "/qualityOutCheck")
public class QualityOutCheckController {

    @Autowired
    private QualityOutCheckService qualityOutCheckService;

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

    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityOutCheckService.submit(qualityInspectResultList);
    }

    @GetMapping("/findResultByDataIdAndClassify")
    Result findResultByDataIdAndClassify(@RequestParam(value = "id",required = true) Integer id){
        return qualityOutCheckService.findResultByDataIdAndClassify(id);
    }

    @PostMapping("/update")
    Result update(@RequestBody QualityInspectResult qualityInspectResultList){
        return qualityOutCheckService.update(qualityInspectResultList);
    }

}
