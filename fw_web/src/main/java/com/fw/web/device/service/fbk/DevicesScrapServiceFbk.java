package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesScrap;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesScrapService;
import org.springframework.stereotype.Service;

@Service
public class DevicesScrapServiceFbk implements DevicesScrapService {

    @Override
    public Result save(DevicesScrap devicesScrap) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String devicesCode, String devicesClassify,Integer devicesScrapId,Integer pageNum,Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByDeviceExtensionId(Integer deviceExtensionId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result selectById(Integer devicesScrapId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
