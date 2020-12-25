package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsCheckHouse;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsCheckHouseService;
import org.springframework.stereotype.Service;

/**
 * 盘库
 * @author lpsong
 * @since 2020-11-12
 */
@Service
public class LogisticsCheckHouseServiceFbk implements LogisticsCheckHouseService {
    @Override
    public Result insert(LogisticsCheckHouse logisticsCheckHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(LogisticsCheckHouse logisticsCheckHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result updateStatus(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String houseNo, String checkTime, Integer status, Integer checkResult,Integer checkUser, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findDetailList(Integer checkHouseId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findStoragePorductList(Integer locationId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findTreeList(String name) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
