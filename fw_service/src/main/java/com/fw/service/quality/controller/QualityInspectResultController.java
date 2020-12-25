package com.fw.service.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.service.quality.service.QualityInspectResultService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-25 09:23
 **/

@RestController
@RequestMapping("/qualityInspectResult")
public class QualityInspectResultController {

    @Autowired
    private QualityInspectResultService qualityInspectResultService;


    @GetMapping(value = "/findHistoryResultByOfon")
    Result findHistoryResultByOfon(@RequestParam(value = "productCode",required = true) String ofNo){
        return qualityInspectResultService.findHistoryResultByOfon(ofNo);
    }

}
