package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsTakeOrder;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsTakeOrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-13 09:26
 **/
@Service
public class LogisticsTakeOrderServiceFbk implements LogisticsTakeOrderService {
    @Override
    public Result update(LogisticsTakeOrder logisticsTakeOrder) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String taskNo, Integer status,Integer kind,String startTime,String stopTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
