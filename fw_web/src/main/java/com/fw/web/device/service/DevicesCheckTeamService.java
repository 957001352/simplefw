package com.fw.web.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckTeam;
import com.fw.web.device.service.fbk.DevicesCheckTeamServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 点检点检项目
 * @author lpsong
 * @since 2020-10-22
 */
@FeignClient(value = "fw-service/devicesCheckTeam", fallback = DevicesCheckTeamServiceFbk.class)
public interface DevicesCheckTeamService {

    /**
     * 新增/修改点检保养项目
     *
     * @param devicesCheckTeam
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DevicesCheckTeam devicesCheckTeam);


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
                    @RequestParam(value = "checkItem", required = false) String checkItem,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
    /**
     * 根据设备查询保养项目
     * @param devicesId
     * @return
     */
    @GetMapping("/findCheckItemListByDevicve")
    Result findCheckItemListByDevicve(@RequestParam(value = "devicesId") Integer devicesId);
}
