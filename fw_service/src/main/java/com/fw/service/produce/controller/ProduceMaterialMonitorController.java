package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.service.produce.service.ProduceMaterialMonitorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: dhlk_fw_plat
 * @description:物料生产监控
 * @author: wqiang
 * @create: 2020-12-17 10:08
 **/
@RestController
@RequestMapping("/produceMaterialMonitor")
public class ProduceMaterialMonitorController {

    @Autowired
    private ProduceMaterialMonitorService produceMaterialMonitorService;

    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "productCode",required = false) String productCode,
                           @RequestParam(value = "productName",required = false) String productName,
                           @RequestParam(value = "startTime",required = false) String startTime,
                           @RequestParam(value = "stopTime",required = false) String stopTime,
                           @RequestParam(value = "timeType",required = false) Integer timeType,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
                           ){
        return  produceMaterialMonitorService.findList(productCode,productName,startTime,stopTime,timeType,pageNum,pageSize);
    }
}
