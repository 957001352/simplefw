package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareIn;
import com.fw.entity.device.DevicesSpareOut;
import com.fw.web.device.service.fbk.DevicesSpareInServiceFbk;
import com.fw.web.device.service.fbk.DevicesSpareOutServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther :yangwang
 * Data:2020/10/27
 * Time:9:32
 * @Description:
 */
@FeignClient(value = "fw-service/devicesSpareInService", fallback = DevicesSpareInServiceFbk.class)
public interface DevicesSpareInService {

    /**
     * 新增备品备件入库
     *
     * @param spareIn
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesSpareIn spareIn);

    /**
     * 根据id查看备品备件入库信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getDevicesSpareIn")
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
