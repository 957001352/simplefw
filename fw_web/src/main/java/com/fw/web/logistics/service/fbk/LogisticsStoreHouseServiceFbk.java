package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsStoreHouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsStoreHouseServiceFbk implements LogisticsStoreHouseService {
    @Override
    public Result findList(String orderNo,String houseNo, String houseType, Integer status, String startTime, String endTime,String partsType, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByOrderNo(String storeHouseId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result storeHouseIn(Integer storeHouseId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result batchBound(List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result save(LogisticsStoreHouse logisticsStoreHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(LogisticsStoreHouse logisticsStoreHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByStoreHouseId(Integer storeHouseId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
