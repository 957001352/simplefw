package com.fw.web.device.service;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.entity.device.DevicesRepairItem;
import com.fw.web.device.service.fbk.DevicesKeepItemServiceFbk;
import com.fw.web.device.service.fbk.DevicesRepairItemServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/devicesRepairItem", fallback = DevicesRepairItemServiceFbk.class)
public interface DevicesRepairItemService {


    /**
     * 新增/修改设备保养项目
     *
     * @param devicesRepairItem
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesRepairItem devicesRepairItem);

    /**
     * 列表查询
     *
     * @param name
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 获取设备类型
     *
     * @return
     */
    @GetMapping("/findDecicesList")
    Result findDecicesList();

    /**
     * 删除
     */
    @GetMapping("/delete")
    Result delete(@RequestParam(value = "ids") String ids);
}
