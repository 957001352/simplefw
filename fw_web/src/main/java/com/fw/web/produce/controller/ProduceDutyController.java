package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.web.produce.service.ProduceDutyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 16:03
 **/


@RestController
@RequestMapping("/produceDuty")
@Api(description = "安排员工", value = "ProduceDutyController")
public class ProduceDutyController {

    @Autowired
    private ProduceDutyService produceDutyService;

    @ApiOperation("保存")
    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceDuty produceDuty) {
        return produceDutyService.save(produceDuty);
    }

    @ApiOperation("获取列表")
    @GetMapping(value = "/findList")
    Result findList( @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceDutyService.findList(pageNum,pageSize);
    }
}
