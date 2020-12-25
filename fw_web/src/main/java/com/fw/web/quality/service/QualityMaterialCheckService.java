package com.fw.web.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.web.quality.service.fbk.QualityFirstendCheckServiceFbk;
import com.fw.web.quality.service.fbk.QualityMaterialCheckServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "fw-service/qualityMaterialCheck", fallback = QualityMaterialCheckServiceFbk.class)
public interface QualityMaterialCheckService {

    @GetMapping("/findList")
    Result findList(@RequestParam(value = "checkNo",required = false) String checkNo,
                    @RequestParam(value = "startTime",required = false) String startTime,
                    @RequestParam(value = "stopTime",required = false) String stopTime,
                    @RequestParam(value = "exeStartTime",required = false) String exeStartTime,
                    @RequestParam(value = "exeStopTime",required = false) String exeStopTime,
                    @RequestParam(value = "status",required = false) Integer status,
                    @RequestParam(value = "pageNum",required = true,defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize",required = true,defaultValue = "10") Integer pageSize);


    @GetMapping("/getCheckoutMaterialInfoById")
    Result getCheckoutMaterialInfoById(@RequestParam(value = "id",required = true) Integer id);

    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList);

    @GetMapping("/findResultByDataIdAndClassify")
    Result findResultByDataIdAndClassify(@RequestParam(value = "id",required = true) Integer id);
}
