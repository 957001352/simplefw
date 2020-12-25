package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.InfoBox;
import com.fw.entity.logistics.LogisticsDeliveryPlan;
import com.fw.entity.logistics.LogisticsOutHouseDetail;
import com.fw.entity.logistics.LogisticsOutSubpackage;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsOutHouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsOutHouseServiceFbk implements LogisticsOutHouseService {
    @Override
    public Result findList(String houseNo, String houseType, String status, String startTime, String endTime,Integer pickStatus, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByQrCode(String qrCode,Integer outHouseId,Integer type) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveOutSubPack(InfoBox<LogisticsOutHouseDetail> infoBox) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findOutSubpackage(String outHouseId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveOutHouseDetail(InfoBox<LogisticsDeliveryPlan> infoBox) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findDetailByProductOrder(String productOrder) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
