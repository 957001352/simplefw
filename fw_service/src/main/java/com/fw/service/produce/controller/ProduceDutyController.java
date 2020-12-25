package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.service.produce.service.ProduceDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 15:57
 **/

@RequestMapping(value = "/produceDuty")
@RestController
public class ProduceDutyController {

    @Autowired
    private ProduceDutyService produceDutyService;

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceDuty produceDuty) {
        return produceDutyService.save(produceDuty);
    }

    @GetMapping(value = "/findList")
    Result findList( @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceDutyService.findList(pageNum,pageSize);
    }
}
