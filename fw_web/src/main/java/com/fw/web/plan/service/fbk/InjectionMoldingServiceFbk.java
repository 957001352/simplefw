package com.fw.web.plan.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.PlanSwap;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.plan.service.InjectionMoldingService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class InjectionMoldingServiceFbk implements InjectionMoldingService {
    @Override
    public Result insert(InjectionMolding injectionMolding) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(InjectionMolding injectionMolding) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result stop(InjectionStopList injectionStopList) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer productDevicesId,String productDeviceCode,String productCode, String partsCode, String startTime, String endTime, String status, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findPlanList(InjectionMolding injectionMolding) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result moveUpDownCancel(PlanSwap planSwap) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
