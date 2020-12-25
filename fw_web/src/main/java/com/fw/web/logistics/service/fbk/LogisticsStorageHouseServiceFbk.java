package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsStorageHouseService;
import org.springframework.stereotype.Service;

/**
 * 物流库位Feign接口调用失败
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
@Service
public class LogisticsStorageHouseServiceFbk implements LogisticsStorageHouseService {

    @Override
    public Result saveStorage(LogisticsStorageHouse logisticsStorageHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result deleteStorage(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findStorageList(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveLocation(LogisticsStorageLocation logisticsStorageLocation) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result deleteLocation(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findLocationList(String name, String productCode, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findStorageLocationList(Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
