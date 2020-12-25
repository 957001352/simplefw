package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDownHouse;
import com.fw.entity.logistics.LogisticsUpHouse;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsUpHouseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 上架管理Feign接口调用失败
 *
 * @author xkliu
 * @date 2020/11/10
 */
@Service
public class LogisticsUpHouseServiceFbk implements LogisticsUpHouseService {

    @Override
    public Result downHouse(LogisticsDownHouse logisticsDownHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findUpList(String code,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findDownList(String code,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getUpHouse(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDownHouse(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result upHouse(LogisticsUpHouse logisticsUpHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findAllList(String code, String storageName,String startCreateTime,String endCreateTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}
