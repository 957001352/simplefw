package com.fw.service.quality.controller;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.service.quality.dao.QualityInspectResultDao;
import com.fw.service.quality.service.QualityMaterialCheckService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import java.util.List;

/**
 * <p>
 * 来料检验管理 前端控制器
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/qualityMaterialCheck")
public class QualityMaterialCheckController {

    @Autowired
    private QualityMaterialCheckService qualityMaterialCheckService;

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

    @GetMapping("/getCheckoutMaterialInfoById")
    Result getCheckoutMaterialInfoById(@RequestParam(value = "id",required = true) Integer id){
        return qualityMaterialCheckService.getCheckoutMaterialInfoById(id);
    }

    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityMaterialCheckService.submit(qualityInspectResultList);
    }

    @GetMapping("/findResultByDataIdAndClassify")
    Result findResultByDataIdAndClassify(@RequestParam(value = "id",required = true) Integer id){
        return qualityMaterialCheckService.findResultByDataIdAndClassify(id);
    }

}
