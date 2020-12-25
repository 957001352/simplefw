package com.fw.web.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.web.quality.service.fbk.QualityFirstendCheckServiceFbk;
import com.fw.web.quality.service.fbk.QualityStoreCheckServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "fw-service/qualityStoreCheck", fallback = QualityStoreCheckServiceFbk.class)
public interface QualityStoreCheckService {

    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "productDevicesCode",required = false) String productDevicesCode,
                           @RequestParam(value = "checkNo",required = false) String checkNo,
                           @RequestParam(value = "productCode",required = false) String productCode,
                           @RequestParam(value = "materialCode",required = false) String materialCode,
                           @RequestParam(value = "startTime",required = false) String startTime,
                           @RequestParam(value = "stopTime",required = false) String stopTime,
                           @RequestParam(value = "status",required = false) Integer status,
                           @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize);

    @GetMapping("/findAppearanceOrSizeInspectInfo")
    public Result findAppearanceOrSizeInspectInfo(@RequestParam(value = "id",required = true) Integer id);

    @PostMapping("/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList);

    @PostMapping("/update")
    Result update(@RequestBody QualityInspectResult qualityInspectResultList);
}
