package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareOut;
import com.fw.web.device.service.fbk.DevicesSpareOutServiceFbk;
import com.fw.web.device.service.fbk.ProductDevicesSpareServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description: 备品备件出库web service
 */
@FeignClient(value = "fw-service/devicesSpareOutService", fallback = DevicesSpareOutServiceFbk.class)
public interface DevicesSpareOutService {

    /**
     * 新增备品备件出库
     *
     * @param spareOut
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesSpareOut spareOut);

    /**
     * 根据id查看备品备件出库信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getDevicesSpareOut")
    Result getDevicesSpareOut(@RequestParam(value = "id") Integer id);


    /**
     * 列表全查
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


}
