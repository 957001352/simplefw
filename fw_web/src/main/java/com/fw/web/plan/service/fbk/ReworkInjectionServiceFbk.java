package com.fw.web.plan.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.ReworkStopList;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.plan.service.ReworkInjectionService;
import org.springframework.stereotype.Service;

@Service
public class ReworkInjectionServiceFbk implements ReworkInjectionService {
    @Override
    public Result insert(ReworkInjection reworkInjection) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(ReworkInjection reworkInjection) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result moveUp(ReworkInjection reworkInjection) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result moveDown(ReworkInjection reworkInjection) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result cancel(ReworkInjection reworkInjection) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result stop(ReworkStopList reworkStopList) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String productCode, String partsCode, String startTime, String endTime, String status, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result createProductCode(Double productTime) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
