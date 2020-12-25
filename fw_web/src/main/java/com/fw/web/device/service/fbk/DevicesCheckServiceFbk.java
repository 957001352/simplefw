package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesCheckService;
import org.springframework.stereotype.Service;

/**
 * 设备点检
 * @author lpsong
 * @since 2020-10-21
 */
@Service
public class DevicesCheckServiceFbk implements DevicesCheckService {

    @Override
    public Result save(DevicesCheck devicesCheck) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String code, String devicesClassify,String devicesCode, Integer status,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findCheckTask(Integer executeUser, Integer productDevicesId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
