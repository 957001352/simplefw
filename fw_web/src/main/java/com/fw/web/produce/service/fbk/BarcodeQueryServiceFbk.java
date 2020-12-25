package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBake;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.BarcodeQueryService;
import com.fw.web.produce.service.ProduceBakeService;
import org.springframework.stereotype.Service;

/**
 * 生产过程监控
 * @author lpsong
 * @since 2020-12-17
 */
@Service
public class BarcodeQueryServiceFbk implements BarcodeQueryService {


    @Override
    public Result findProductProcessList() {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findMoldingInjectionList(String productOrder, String partsCode, String partsName, Integer pageNum, Integer pageSize) {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findNowProductOrder(Integer productDevicesId) {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findProductStatusList(String productDevicesCode) {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findBadProductList(Integer planMoldingId) {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findFeedingDetailList(String productOrder) {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findMoldingMonitorList(Integer planMoldingId, String productOrder,String status) {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findReworkInjectionList(String productOrder, String partsCode, String partsName, Integer pageNum, Integer pageSize) {
       return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findDevicesStateByCode(String devicesCode) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
