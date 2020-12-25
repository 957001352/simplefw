package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesScrap;
import com.fw.web.device.service.fbk.DevicesExtensionServiceFbk;
import com.fw.web.device.service.fbk.DevicesScrapServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 设备报废
 * @author gchen
 * @since 2020-10-23
 */
@FeignClient(value = "fw-service/devicesScrap", fallback = DevicesScrapServiceFbk.class)
public interface DevicesScrapService {

    /**
     * 新增/修改
     * @param devicesScrap
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesScrap devicesScrap);


    /**
     * 列表查询
     * @param devicesCode devicesClassify status startTime endTime
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "devicesCode", required = false) String devicesCode,
                    @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                    @RequestParam(value = "devicesScrapId", required = false) Integer devicesScrapId,
                    @RequestParam(value = "pageNum", required = false)Integer pageNum,
                    @RequestParam(value = "pageSize", required = false)Integer pageSize);

    /**
     * 根据履历id查询设备报废履历
     * @param deviceExtensionId
     */
    @GetMapping("/findByDeviceExtensionId")
    Result findByDeviceExtensionId(@RequestParam(value = "deviceExtensionId", required = false) Integer deviceExtensionId);

    /**
     * 根据id查询报废设备详情
     * @param devicesScrapId
     */
    @GetMapping("/selectById")
    Result selectById(@RequestParam(value = "devicesId", required = false) Integer devicesScrapId);

}
