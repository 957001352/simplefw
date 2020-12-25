package com.fw.web.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldSpareIn;
import com.fw.entity.mould.MouldSpareOut;
import com.fw.entity.mould.MouldStorageInfo;
import com.fw.web.mould.service.fbk.MouldDevicesServiceFbk;
import com.fw.web.mould.service.fbk.MouldStorageServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 模具库存管理
 * @Author: fchai
 * @Date: 2020/10/26 14:55
 */

@FeignClient(value = "fw-service/mouldStorage", fallback = MouldStorageServiceFbk.class)
public interface MouldStorageService {


    @PostMapping(value = "/insertSpareInfo")
    Result insertSpareInfo(@RequestBody MouldStorageInfo mouldStorageInfo);

    @GetMapping(value = "/deleteSpareInfo")
    Result deleteSpareInfo(@RequestParam(value = "ids") String ids);

    @GetMapping(value = "/findListSpareInfo")
    Result findListSpareInfo(@RequestParam(value = "code",required = false) String code,
                             @RequestParam(value = "name",required = false) String name,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    @PostMapping(value = "/mouldIn")
    Result mouldIn(@RequestBody MouldSpareIn mouldSpareIn);

    @PostMapping(value = "/mouldOut")
    Result mouldOut(@RequestBody MouldSpareOut mouldSpareOut);


    @GetMapping(value = "/getInOutInfo")
    Result getInOutInfo(@RequestParam(value = "outInNo" , required = false) String outInNo,
                        @RequestParam(value = "operate", required = false) Integer operate,
                        @RequestParam(value = "startDate", required = false) String startDate,
                        @RequestParam(value = "endDate", required = false)String endDate,
                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    @GetMapping(value = "findMouldSpareStoreList")
    Result findMouldSpareStoreList(@RequestParam(value = "name") String name);

    @GetMapping(value = "/findMouldSpareStoreAndUse")
    Result findMouldSpareStoreAndUse(@RequestParam(value = "code") String code);


    @GetMapping(value = "findOutInDetail")
    public Result findOutInDetail(@RequestParam(value = "id") Integer id,@RequestParam(value = "operate") Integer operate);

}
