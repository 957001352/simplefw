package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.entity.mould.MouldKeepPlan;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldKeepPlanService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @des 模具保养计划制定Feign接口调用失败
 * @date 2020/10/27
 */
@Service
public class MouldKeepPlanServiceFbk implements MouldKeepPlanService {

    @Override
    public Result save(MouldKeepPlan MouldKeepPlan) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getMouldKeepPlan(Integer id) {
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
    public Result findList(String name, String keepTeamName, String mouldCode, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
