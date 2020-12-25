package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldStorageHouse;
import com.fw.entity.mould.MouldStorageLocation;
import com.fw.entity.mould.MouldUseRecord;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldStorageHouseService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MouldStorageHouseServiceFbk implements MouldStorageHouseService {
    @Override
    public Result saveStorage(MouldStorageHouse mouldStorageHouse) {
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
    public Result saveLocation(MouldStorageLocation mouldStorageLocation) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result deleteLocation(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findLocationList(Integer storageHouseId, String location, String mould,String mouldCode,
                                   Integer status, Integer isBand,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result boundMould(MouldDevices mouldDevices) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result unbundMoule(MouldDevices mouldDevices) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findEmptyLocationList(String name) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
