package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsTakeOrder;
import com.fw.service.logistics.service.LogisticsTakeOrderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description: 收货
 * @author: wqiang
 * @create: 2020-11-13 09:19
 **/

@RestController
@RequestMapping("/logisticsTakeOrder")
public class LogisticsTakeOrderController {

    @Autowired
    private LogisticsTakeOrderService logisticsTakeOrderService;


    /**
     * 更新实收物料数量
     * @param logisticsTakeOrder
     * @return
     */
    @PostMapping(value = "/update")
    @RequiresPermissions(value = "logisticsTakeOrder:update")
    public Result update(@RequestBody LogisticsTakeOrder logisticsTakeOrder){
        return logisticsTakeOrderService.update(logisticsTakeOrder);
    }


    /**
     * 获取详细信息
     * @param taskNo
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "taskNo",required = false) String taskNo,
                           @RequestParam(value = "status",required = false) Integer status,
                           @RequestParam(value = "kind",required = false) Integer kind,
                           @RequestParam(value = "startTime",required = false) String startTime,
                           @RequestParam(value = "stopTime",required = false) String stopTime,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize

    ){
        return logisticsTakeOrderService.findList(taskNo,status,kind,startTime,stopTime,pageNum,pageSize);
    }
}
