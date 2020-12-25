package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsDeliveryPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发货计划
 * @author lpsong
 * @since 2020-12-04
 */
@Service
public class LogisticsDeliveryPlanServiceFbk implements LogisticsDeliveryPlanService {

    @Override
    public Result insert(List<LogisticsDeliveryPlan> logisticsDeliveryPlans) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(LogisticsDeliveryPlan logisticsDeliveryPlan) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String customer, String productCode, String deliverTime, Integer status, Integer outHouseId,Integer queryType, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByOutHouseId(Integer outHouseId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
