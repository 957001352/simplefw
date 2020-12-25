package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepPlanService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @des 设备保养计划制定Feign接口调用失败
 * @date 2020/10/21
 */
@Service
public class DevicesKeepPlanServiceFbk implements DevicesKeepPlanService {

    @Override
    public Result save(DevicesKeepPlan devicesKeepPlan) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesKeepPlan(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getAllKeepTeam() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, String keepTeamName, String devicesCode, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
