package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.logistics.LogisticsPickingOrder;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsPickingOrderService;
import com.fw.web.logistics.service.LogisticsPickingService;
import org.springframework.stereotype.Service;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
@Service
public class LogisticsPickingOrderServiceFbk implements LogisticsPickingOrderService {
    @Override
    public Result save(LogisticsPickingOrder logisticsPickingOrder) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer outHouseId,String  productOrder) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByOutHouseId(Integer outHouseId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
