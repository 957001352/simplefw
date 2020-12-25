package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetailDTO;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesOutinDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description:
 */
@RestController
@RequestMapping("/devicesOutinDetail")
@Api(description = "出入库明细", value = "DevicesOutinDetailController")
public class DevicesOutinDetailController {

    @Autowired
    private DevicesOutinDetailService devicesOutinDetailService;

    @GetMapping("/findList")
    public Result findAll(
            @RequestParam(value = "outOrInNo", required = false) String outOrInNo,
            @RequestParam(value = "operate", required = false) String operate,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "stopTime", required = false) String stopTime,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesOutinDetailService.findAll(outOrInNo, operate, startTime, stopTime, pageNum, pageSize);
    }
}
