package com.fw.web.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingRecord;
import com.fw.web.produce.service.fbk.ProduceDutyServiceFbk;
import com.fw.web.produce.service.fbk.ProduceMoldingRecordServiceFbk;
import com.fw.web.quality.service.fbk.QualityFirstendCheckServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-14 14:20
 **/
@FeignClient(value = "fw-service/produceMoldingRecord", fallback =  ProduceMoldingRecordServiceFbk.class)
public interface ProduceMoldingRecordService {

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceMoldingRecord produceMoldingRecord);

    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "ofNo", required = false) String ofNo,
            @RequestParam(value = "userName", required = false) String userName ,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    @GetMapping(value = "/callMaterialOrCastMaterial")
    public Result callMaterialOrCastMaterial(@RequestParam(value = "productDeviceCode",required = true) String productDeviceCode,
                                             @RequestParam(value = "callType",required = true) Integer callType,
                                             @RequestParam(value = "CastType",required = false) String CastType);

    @GetMapping(value = "/findCallAndCastList")
    public Result findCallAndCastList();
}
