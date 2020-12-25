package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBake;
import com.fw.entity.produce.ProduceDuty;
import com.fw.service.produce.service.ProduceBakeService;
import com.fw.service.produce.service.ProduceDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 17:52
 **/

@RequestMapping(value = "/produceBake")
@RestController
public class ProduceBakeController {

    @Autowired
    private ProduceBakeService produceBakeService;

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceBake produceBake) {
        return produceBakeService.save(produceBake);
    }

    @GetMapping(value = "/findList")
    Result findList( @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceBakeService.findList(pageNum,pageSize);
    }
}
