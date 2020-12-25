package com.fw.web.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.web.quality.service.QualityInspectResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-25 09:37
 **/

@RestController
@RequestMapping(value = "/qualityInspectResult")
@Api(description = "质量检验结果管理", value = "QualityInspectResultController")
public class QualityInspectResultController {

    @Autowired
    private QualityInspectResultService qualityInspectResultService;


    @ApiOperation("更具生产指令号查询历史质检结果列表")
    @GetMapping(value = "/findHistoryResultByOfon")
    Result findHistoryResultByOfon(@RequestParam(value = "productCode",required = true) String ofNo){
        return qualityInspectResultService.findHistoryResultByOfon(ofNo);
    }

}
