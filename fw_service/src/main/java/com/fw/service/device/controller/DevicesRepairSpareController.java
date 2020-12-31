package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepairSpare;
import com.fw.enums.ResultEnum;
import com.fw.service.device.service.DevicesRepairSpareService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-27 10:07
 **/
@RestController
@RequestMapping("/devicesRepairSpare")
public class DevicesRepairSpareController {

    @Autowired
    private DevicesRepairSpareService devicesRepairSpareService;

    @RequestMapping("/findSpareStoreAndUse")
    @RequiresAuthentication
    Result findSpareStoreAndUse(@RequestParam(value = "code", required = true) String code) {
        if(CheckUtils.isNull(code)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return devicesRepairSpareService.findSpareStoreAndUse(code);
    }
}
