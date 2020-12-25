package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.*;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldDevicesService;
import org.springframework.stereotype.Service;

@Service
public class MouldDevicesServiceFbk implements MouldDevicesService {
    @Override
    public Result save(MouldDevices mouldDevices) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String code, String mouldName, Integer status, Integer turnStatus, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveScrap(MouldScrap mouldScrap) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveStop(MouldStop mouldStop) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveTurn(MouldTurn mouldTurn) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findTurn(Integer mouldDeviceId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findUnBoundMould() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveUseRecord(MouldUseRecord mouldUseRecord) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findListUseRecord(String mouldCode,Integer status,Integer pageNum,Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findOneById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
