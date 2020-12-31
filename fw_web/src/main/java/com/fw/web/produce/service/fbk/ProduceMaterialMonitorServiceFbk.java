package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceMaterialMonitorService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-17 10:13
 **/
@Service
public class ProduceMaterialMonitorServiceFbk implements ProduceMaterialMonitorService {
    @Override
    public Result findList(String productCode, String productName, String startTime, String stopTime, Integer timeType, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findPlantUseMaterialList(String productCode, String startTime, String stopTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }


}
