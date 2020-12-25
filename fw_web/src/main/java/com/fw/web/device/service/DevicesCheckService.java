package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;
import com.fw.web.device.service.fbk.DevicesCheckServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 设备点检
 *
 * @author lpsong
 * @since 2020-10-21
 */
@FeignClient(value = "fw-service/devicesCheck", fallback = DevicesCheckServiceFbk.class)
public interface DevicesCheckService {


    /**
     * 新增/修改设备保养项目
     *
     * @param devicesCheck
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesCheck devicesCheck);


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
     * @param code
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "code", required = false) String code,
                    @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                    @RequestParam(value = "devicesCode", required = false) String devicesCode,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 查询需要有点检任务
     *
     * @param executeUser
     * @param productDevicesId
     * @return
     */
    @GetMapping("/findCheckTask")
    Result findCheckTask(@RequestParam(value = "executeUser") Integer executeUser,
                         @RequestParam(value = "productDevicesId") Integer productDevicesId);
}
