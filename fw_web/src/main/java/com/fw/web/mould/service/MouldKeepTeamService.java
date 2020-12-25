package com.fw.web.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTeam;
import com.fw.web.mould.service.fbk.MouldKeepTeamServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xkliu
 * @des 模具保养项目组Feign接口
 * @date 2020/10/27
 */
@FeignClient(value = "fw-service/mouldKeepTeam", fallback = MouldKeepTeamServiceFbk.class)
public interface MouldKeepTeamService {

    /**
     * 新增/修改设备保养项目
     *
     * @param mouldKeepTeam
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody MouldKeepTeam mouldKeepTeam);

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
     * @param mouldCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "mouldCode", required = false) String mouldCode,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 获取所有模具编号
     *
     * @return
     */
    @GetMapping(value = "/getMouldCode")
    Result getMouldCode();

    /**
     * 查看模具保养项目组
     *
     * @param id
     * @return
     */
    @GetMapping("/getMouldKeepTeam")
    Result getMouldKeepTeam(@RequestParam(value = "id") Integer id);


    /**
     * 根据保养周期获取保养项目
     *
     * @return result
     */
    @GetMapping(value = "/getKeepItemByCycle")
    Result getKeepItemByCycle(@RequestParam(value = "cycle") Integer cycle);
}
