package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesProblem;
import com.fw.entity.device.DevicesRepair;
import com.fw.service.device.service.DevicesProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description: 设备为题列表控制层
 * @author: wqiang
 * @create: 2020-10-22 09:52
 **/
@RestController
@RequestMapping(value = "/devicesProblem")
public class DevicesProblemController {

    @Autowired
    private DevicesProblemService devicesProblemService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody DevicesProblem DevicesProblem) {
        return devicesProblemService.save(DevicesProblem);
    }

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids){
        return devicesProblemService.delete(ids);
    }
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "content", required = false) String content,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "type", required = false) Integer type,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesProblemService.findList(name, content, status, type,pageNum, pageSize);
    }
}
