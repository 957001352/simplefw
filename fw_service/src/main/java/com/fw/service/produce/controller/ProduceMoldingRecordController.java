package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingRecord;
import com.fw.entity.produce.ProduceShift;
import com.fw.service.produce.service.ProduceMoldingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-14 14:16
 **/

@RequestMapping(value = "/produceMoldingRecord")
@RestController
public class ProduceMoldingRecordController {

    @Autowired
    private ProduceMoldingRecordService produceMoldingRecordService;

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceMoldingRecord produceMoldingRecord) {
        return produceMoldingRecordService.save(produceMoldingRecord);
    }

    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "ofNo", required = false) String ofNo,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceMoldingRecordService.findList(ofNo,userName,pageNum,pageSize);
    }

    @GetMapping(value = "/callMaterialOrCastMaterial")
    public Result callMaterialOrCastMaterial(@RequestParam(value = "productDeviceCode",required = true) String productDeviceCode,
                                             @RequestParam(value = "callType",required = true) Integer callType,
                                             @RequestParam(value = "CastType",required = false) String CastType) {
        return produceMoldingRecordService.callMaterialOrCastMaterial(productDeviceCode,callType,CastType);
    }

    @GetMapping(value = "/findCallAndCastList")
    public Result findCallAndCastList() {
        return produceMoldingRecordService.findCallAndCastList();
    }
}
