package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepItemService;
import org.springframework.stereotype.Service;

/**
 * @des 设备保养项目Feign接口调用失败
 * @author xkliu
 * @date 2020/10/20
 */
@Service
public class DevicesKeepItemServiceFbk implements DevicesKeepItemService {

    @Override
    public Result save(DevicesKeepItem devicesKeepItem) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesKeepItem(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, String devicesClassify, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesClassify() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
