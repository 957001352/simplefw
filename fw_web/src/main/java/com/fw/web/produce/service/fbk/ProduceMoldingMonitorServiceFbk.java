package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingMonitor;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceMoldingMonitorService;
import org.springframework.stereotype.Service;

/**
 * @des: 注塑过程监控Feign接口调用失败
 * @author: xkliu
 * @date: 2020/12/16
 */
@Service
public class ProduceMoldingMonitorServiceFbk implements ProduceMoldingMonitorService {

    @Override
    public Result debug(ProduceMoldingMonitor produceMoldingMonitor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result startProduct(ProduceMoldingMonitor produceMoldingMonitor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result accomplishProduct(ProduceMoldingMonitor produceMoldingMonitor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result startDebug(ProduceMoldingMonitor produceMoldingMonitor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
