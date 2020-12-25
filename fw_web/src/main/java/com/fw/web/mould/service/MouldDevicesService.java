package com.fw.web.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.*;
import com.fw.web.mould.service.fbk.MouldDevicesServiceFbk;
import com.fw.web.mould.service.fbk.MouldRepairServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/mouldDevices", fallback = MouldDevicesServiceFbk.class)
public interface MouldDevicesService {


    @PostMapping(value = "/save")
    Result save(@RequestBody MouldDevices mouldDevices);

    /**
     * 批量删除
     * @param ids
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam String ids);


    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "code", required = false) String code,
                    @RequestParam(value = "mouldName", required = false) String mouldName,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "turnStatus", required = false) Integer turnStatus,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 模具报废申请
     */
    @PostMapping(value = "/saveScrap")
    Result saveScrap(@RequestBody MouldScrap mouldScrap);

    /**
     * 模具停用申请
     */
    @PostMapping(value = "/saveStop")
    Result saveStop(@RequestBody MouldStop mouldStop);

    /**
     * 模具转段申请
     */
    @PostMapping(value = "/saveTurn")
    Result saveTurn(@RequestBody MouldTurn mouldTurn);

    /**
     * 转段履历
     */
    @GetMapping(value = "/findTurn")
    Result findTurn(@RequestParam("mouldDeviceId") Integer mouldDeviceId);

    /**
     * 查询所有未绑定库位的模具
     */
    @GetMapping(value = "/findUnBoundMould")
    Result findUnBoundMould();

    /**
     * 新增模具使用
     * @param mouldUseRecord
     * @return result
     */
    @PostMapping(value = "/saveUseRecord")
    Result saveUseRecord(@RequestBody MouldUseRecord mouldUseRecord);

    /**
     * 查询模具使用
     * @param status
     * @return result
     */
    @GetMapping(value = "/findListUseRecord")
    Result findListUseRecord(@RequestParam(value = "mouldCode",required = false) String mouldCode,
                             @RequestParam(value = "status",required = false) Integer status,
                             @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据id查询转段信息
     * @param id
     * @return result
     */
    @GetMapping(value = "/findOneById")
    Result findOneById(@RequestParam("id") Integer id);
}
