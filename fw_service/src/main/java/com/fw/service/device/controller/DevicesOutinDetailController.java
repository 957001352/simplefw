package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetail;
import com.fw.entity.device.DevicesOutinDetailDTO;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.service.device.service.DevicesOutInDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description:
 */
@RestController
@RequestMapping("/devicesOutinDetail")
public class DevicesOutinDetailController {

    @Autowired
    private DevicesOutInDetailService devicesOutInDetailService;


    @GetMapping("/findList")
    public Result findAll(
            @RequestParam(value = "outOrInNo", required = false) String outOrInNo,
            @RequestParam(value = "operate", required = false) String operate,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "stopTime", required = false) String stopTime,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesOutInDetailService.findAll(outOrInNo, operate, startTime, stopTime, pageNum, pageSize);
    }

}
