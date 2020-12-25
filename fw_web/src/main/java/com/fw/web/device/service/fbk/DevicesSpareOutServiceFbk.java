package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareOut;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesSpareOutService;
import org.springframework.stereotype.Service;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description:
 */
@Service
public class DevicesSpareOutServiceFbk implements DevicesSpareOutService {
    @Override
    public Result save(DevicesSpareOut spareOut) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesSpareOut(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
