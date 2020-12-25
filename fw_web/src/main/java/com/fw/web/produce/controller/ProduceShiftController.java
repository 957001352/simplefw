package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceShift;
import com.fw.web.produce.service.ProduceShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 17:01
 **/


@RestController
@RequestMapping("/produceShift")
@Api(description = "换班交接", value = "ProduceShiftController")
public class ProduceShiftController {

    @Autowired
    private ProduceShiftService produceShiftService;


    @ApiOperation("保存")
    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceShift produceShift) {
        return produceShiftService.save(produceShift);
    }


    @ApiOperation("获取列表")
    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "planMoldingId", required = false) Integer planMoldingId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceShiftService.findList(planMoldingId,pageNum,pageSize);
    }
}
