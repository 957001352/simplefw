package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsBackHouse;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsBackHouseService;
import org.springframework.stereotype.Service;

/**
 * 退库管理Feign接口调用失败
 *
 * @author xkliu
 * @date 2020/11/12
 */
@Service
public class LogisticsBackHouseServiceFbk implements LogisticsBackHouseService {

    @Override
    public Result findList(String houseNo, Integer status, String productOrder,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getBackHouseDetail(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result cancellingStocks(LogisticsBackHouse logisticsBackHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findHistoryList(String houseNo, String code, String productCode, String startExecuteTime, String endExecuteTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result backHouseDetailPage(Integer id, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result save(LogisticsBackHouse logisticsBackHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
