package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsPickingService;
import org.springframework.stereotype.Service;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
@Service
public class LogisticsPickingServiceFbk implements LogisticsPickingService {
    @Override
    public Result save(LogisticsPicking logisticsPicking) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer outHouseId, Integer pageNum, Integer pageSize) {
            return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
