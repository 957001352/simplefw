package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingRecord;
import com.fw.web.produce.service.ProduceMoldingRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-14 14:22
 **/

@RestController
@RequestMapping("/produceMoldingRecord")
@Api(description = "生产报工记录", value = "ProduceMoldingRecordController")
public class ProduceMoldingRecordController {

    @Autowired
    private ProduceMoldingRecordService produceMoldingRecordService;


    @ApiOperation(value = "报工")
    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceMoldingRecord produceMoldingRecord) {
        return produceMoldingRecordService.save(produceMoldingRecord);
    }

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "ofNo", required = false) String ofNo,
            @RequestParam(value = "userName", required = false) String userName ,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceMoldingRecordService.findList(ofNo,userName,pageNum,pageSize);
    }

    @ApiOperation(value = "叫料投料")
    @GetMapping(value = "/callMaterialOrCastMaterial")
    public Result callMaterialOrCastMaterial(@RequestParam(value = "productDeviceCode",required = true) String productDeviceCode,
                                             @RequestParam(value = "callType",required = true) Integer callType,
                                             @RequestParam(value = "CastType",required = false) String CastType) {
        return produceMoldingRecordService.callMaterialOrCastMaterial(productDeviceCode,callType,CastType);
    }

    @ApiOperation(value = "获取叫料投料集合")
    @GetMapping(value = "/findCallAndCastList")
    public Result findCallAndCastList() {
        return produceMoldingRecordService.findCallAndCastList();
    }


}
