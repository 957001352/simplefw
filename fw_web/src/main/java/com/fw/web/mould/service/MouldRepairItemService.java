package com.fw.web.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.MouldRepairItem;
import com.fw.web.mould.service.fbk.MouldRepairItemServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/mouldRepairItem", fallback = MouldRepairItemServiceFbk.class)
public interface MouldRepairItemService {

    @PostMapping(value = "/save")
    Result save(@RequestBody MouldRepairItem mouldRepairItem);

    @GetMapping("/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 删除
     */
    @GetMapping("/delete")
    Result delete(@RequestParam(value = "ids") String ids);

}
