package com.fw.web.mould.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.entity.mould.MouldKeepPlan;
import com.fw.web.mould.service.fbk.MouldKeepPlanServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xkliu
 * @des 模具保养计划制定Feign接口
 * @date 2020/10/27
 */
@FeignClient(value = "fw-service/mouldKeepPlan", fallback = MouldKeepPlanServiceFbk.class)
public interface MouldKeepPlanService {

    /**
     * 新增/修改设备保养计划定制
     *
     * @param MouldKeepPlan
     * @return
     */
    @PostMapping("/save")
    Result save(@RequestBody MouldKeepPlan MouldKeepPlan);

    /**
     * 查看设备保养计划定制
     *
     * @param id
     * @return
     */
    @GetMapping("/getMouldKeepPlan")
    Result getMouldKeepPlan(@RequestParam(value = "id") Integer id);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    /**
     * 获取保养项目组
     *
     * @return
     */
    @GetMapping(value = "/getAllKeepTeam")
    Result getAllKeepTeam();

    /**
     * 列表查询
     *
     * @param name
     * @param keepTeamName
     * @param mouldCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "keepTeamName", required = false) String keepTeamName,
                    @RequestParam(value = "mouldCode", required = false) String mouldCode,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
