package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesExtension;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesExtensionService;
import org.springframework.stereotype.Service;

@Service
public class DevicesExtensionServiceFbk implements DevicesExtensionService {
    @Override
    public Result save(DevicesExtension devicesExtension) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String devicesCode, String devicesClassify, Integer status, String startTime, String endTime) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
