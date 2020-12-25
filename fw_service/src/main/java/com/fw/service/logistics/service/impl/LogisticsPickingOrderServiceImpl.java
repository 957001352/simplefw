package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsPickingOrder;
import com.fw.service.logistics.dao.LogisticsPickingOrderDao;
import com.fw.service.logistics.service.LogisticsPickingOrderService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 领料单
 * @author lpsong
 * @since 2020-12-15
 */
@Service
public class LogisticsPickingOrderServiceImpl implements LogisticsPickingOrderService {

    @Autowired
    private LogisticsPickingOrderDao logisticsPickingOrderDao;



    @Override
    @Transactional
    public Result save(LogisticsPickingOrder logisticsPickingOrder){

        return ResultUtils.success();
    }

    @Override
    public Result findList(Integer outHouseId,String  productOrder) {
        return ResultUtils.success(logisticsPickingOrderDao.findList(outHouseId,productOrder));
    }

    @Override
    public Result findByOutHouseId(Integer outHouseId) {
        if(CheckUtils.isNull(outHouseId)){
            return ResultUtils.error("参数错误");
        }
        return ResultUtils.success(logisticsPickingOrderDao.findList(outHouseId,null));
    }
}
