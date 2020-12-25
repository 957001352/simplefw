package com.fw.web.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.web.quality.service.fbk.QualityOutCheckServiceFbk;
import com.fw.web.quality.service.fbk.QualityStoreCheckServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "fw-service/qualityOutCheck", fallback = QualityOutCheckServiceFbk.class)
public interface QualityOutCheckService {

    @GetMapping("/findList")
    Result findList(@RequestParam(value = "checkNo",required = false) String checkNo,
                    @RequestParam(value = "customer",required = false) String customer,
                    @RequestParam(value = "materialCode",required = false) String materialCode,
                    @RequestParam(value = "startTime",required = false) String startTime,
                    @RequestParam(value = "stopTime",required = false)String stopTime,
                    @RequestParam(value = "status",required = false)Integer status,
                    @RequestParam(value = "pageNum",required = true,defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize",required = true,defaultValue = "10")Integer pageSize);

    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList);


    @GetMapping("/findResultByDataIdAndClassify")
    Result findResultByDataIdAndClassify(@RequestParam(value = "id",required = true) Integer id);

    @PostMapping("/update")
    Result update(@RequestBody QualityInspectResult qualityInspectResultList);
}
