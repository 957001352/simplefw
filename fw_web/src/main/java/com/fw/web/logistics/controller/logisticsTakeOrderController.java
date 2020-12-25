package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsTakeOrder;
import com.fw.web.logistics.service.LogisticsTakeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-13 09:28
 **/

@RestController
@RequestMapping("/logisticsTakeOrder")
@Api(description = "收货管理", value = "logisticsTakeOrderController")
public class logisticsTakeOrderController {



    @Autowired
    private LogisticsTakeOrderService logisticsTakeOrderService;


    @ApiOperation(value = "更新实收物料数量")
    @PostMapping(value = "/update")
    public Result update(@RequestBody LogisticsTakeOrder logisticsTakeOrder){
        return logisticsTakeOrderService.update(logisticsTakeOrder);
    }



    @ApiOperation(value = "获取详细信息")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "taskNo",required = false) String taskNo,
                           @RequestParam(value = "status",required = false) Integer status,
                           @RequestParam(value = "kind",required = false) Integer kind,
                           @RequestParam(value = "startTime",required = false) String startTime,
                           @RequestParam(value = "stopTime",required = false) String stopTime,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        return logisticsTakeOrderService.findList(taskNo,status,kind,startTime,stopTime,pageNum,pageSize);
    }
}
