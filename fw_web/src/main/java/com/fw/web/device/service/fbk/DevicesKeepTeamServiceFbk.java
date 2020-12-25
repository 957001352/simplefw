package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTeam;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepTeamService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @des 设备保养项目Feign接口调用失败
 * @date 2020/10/21
 */
@Service
public class DevicesKeepTeamServiceFbk implements DevicesKeepTeamService {

    @Override
    public Result save(DevicesKeepTeam devicesKeepTeam) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesKeepTeam(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getAllKeepItem() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, String devicesClassify, String devicesCode, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getKeepItemByCycle(Integer cycle) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
