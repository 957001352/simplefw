package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.entity.produce.ProduceShift;
import com.fw.service.produce.service.ProduceDutyService;
import com.fw.service.produce.service.ProduceShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 16:57
 **/

@RequestMapping(value = "/produceShift")
@RestController
public class ProduceShiftController {

    @Autowired
    private ProduceShiftService produceShiftService;

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceShift produceShift) {
        return produceShiftService.save(produceShift);
    }

    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "planMoldingId", required = false) Integer planMoldingId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceShiftService.findList(planMoldingId,pageNum, pageSize);
    }
}
