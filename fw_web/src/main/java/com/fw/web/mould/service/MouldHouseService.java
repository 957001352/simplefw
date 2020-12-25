package com.fw.web.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.*;
import com.fw.web.mould.service.fbk.MouldDevicesServiceFbk;
import com.fw.web.mould.service.fbk.MouldHouseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/mouldHouse", fallback = MouldHouseServiceFbk.class)
public interface MouldHouseService {


    /**
     * 新增/修改
     *
     * @param mouldHouse
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody MouldHouse mouldHouse);


    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    /**
     * 列表查询
     *
     * @param code
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "code", required = false) String code,
                    @RequestParam(value = "mouldCode", required = false) String mouldCode,
                    @RequestParam(value = "operate", required = false) Integer operate,
                    @RequestParam(value = "startTime", required = false) String startTime,
                    @RequestParam(value = "endTime", required = false) String endTime,
                    @RequestParam(value = "status", required = false) Integer status,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
