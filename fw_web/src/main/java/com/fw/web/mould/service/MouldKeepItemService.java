package com.fw.web.mould.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.entity.mould.MouldKeepItem;
import com.fw.web.mould.service.fbk.MouldKeepItemServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xkliu
 * @des 模具保养项目Feign接口
 * @date 2020/10/20
 */
@FeignClient(value = "fw-service/mouldKeepItem", fallback = MouldKeepItemServiceFbk.class)
public interface MouldKeepItemService {


    /**
     * 新增/修改设备保养项目
     *
     * @param mouldKeepItem
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody MouldKeepItem mouldKeepItem);

    /**
     * 查看设备保养项目
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getMouldKeepItem")
    Result getMouldKeepItem(@RequestParam(value = "id") Integer id);

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


}
