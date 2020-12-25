package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetailDTO;
import com.fw.web.device.service.fbk.DevicesOutinDetailServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description:
 */
@FeignClient(value = "fw-service/devicesOutinDetail", fallback = DevicesOutinDetailServiceFbk.class)
public interface DevicesOutinDetailService {

    /**
     * 列表查询
     *
     * @param outOrInNo
     * @param operate
     * @param startTime
     * @param stopTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findAll(@RequestParam(value = "outOrInNo", required = false) String outOrInNo,
                   @RequestParam(value = "operate", required = false) String operate,
                   @RequestParam(value = "startTime", required = false) String startTime,
                   @RequestParam(value = "stopTime", required = false) String stopTime,
                   @RequestParam(value = "pageNum", required = false, defaultValue = "1")  Integer pageNum,
                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
