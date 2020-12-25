package com.fw.web.quality.service;


import com.fw.domain.Result;
import com.fw.web.quality.service.fbk.QualityInspectResultServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/qualityInspectResult", fallback = QualityInspectResultServiceFbk.class)
public interface QualityInspectResultService {


    @GetMapping(value = "/findHistoryResultByOfon")
    Result findHistoryResultByOfon(@RequestParam(value = "productCode",required = true) String ofNo);

}
