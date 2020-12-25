package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.web.device.service.fbk.DevicesCheckItemServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@FeignClient(value = "fw-service/devicesCheckItem", fallback = DevicesCheckItemServiceFbk.class)
public interface DevicesCheckItemService {


    /**
     * 新增/修改设备保养项目
     *
     * @param devicesCheckItem
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesCheckItem devicesCheckItem);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

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
                    @RequestParam(value = "content", required = false) String content,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}
