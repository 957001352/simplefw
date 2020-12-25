package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.web.produce.service.ProduceMaterialMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-17 10:14
 **/

@RestController
@RequestMapping("/produceMaterialMonitor")
@Api(description = "物料过程监控", value = "ProduceMaterialMonitorController")
public class ProduceMaterialMonitorController {
    @Autowired
    private ProduceMaterialMonitorService produceMaterialMonitorService;

    @ApiOperation(value = "获取列表 timeType：0入库日期 1领料日期")
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
