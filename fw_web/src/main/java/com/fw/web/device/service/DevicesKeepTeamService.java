package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTeam;
import com.fw.web.device.service.fbk.DevicesKeepTeamServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xkliu
 * @des 设备保养项目组Feign接口
 * @date 2020/10/21
 */
@FeignClient(value = "fw-service/devicesKeepTeam", fallback = DevicesKeepTeamServiceFbk.class)
public interface DevicesKeepTeamService {

    /**
     * 新增/修改设备保养项目
     *
     * @param devicesKeepTeam
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesKeepTeam devicesKeepTeam);

    /**
     * 查看设备保养项目组
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesKeepTeam")
    Result getDevicesKeepTeam(@RequestParam(value = "id") Integer id);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    /**
     * 获取保养项目
     *
     * @return
     */
    @GetMapping(value = "/getAllKeepItem")
    Result getAllKeepItem();

    /**
     * 列表查询
     *
     * @param name
     * @param devicesClassify
     * @param devicesCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                    @RequestParam(value = "devicesCode", required = false) String devicesCode,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 根据保养周期获取保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getKeepItemByCycle")
    Result getKeepItemByCycle(@RequestParam(value = "cycle") Integer cycle);
}
