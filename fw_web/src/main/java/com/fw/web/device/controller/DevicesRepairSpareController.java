package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.enums.ResultEnum;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesRepairSpareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-27 10:16
 **/


@RestController
@RequestMapping(value = "/devicesRepairSpare")
@Api(description = "设备维修备品备件", value = "DevicesRepairSpareController")
public class DevicesRepairSpareController {

    @Autowired
    private DevicesRepairSpareService devicesRepairSpareService;

    @ApiOperation("根据设备维修编码查询备品备件库存及使用情况")
    @GetMapping("/findSpareStoreAndUse")
    Result findSpareStoreAndUse(@RequestParam(value = "code", required = true) String code) {
        if(CheckUtils.isNull(code)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return devicesRepairSpareService.findSpareStoreAndUse(code);
    }
}
