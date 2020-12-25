package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareIn;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesSpareInService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/27
 * Time:9:34
 * @Description:
 */
@Service
public class DevicesSpareInServiceFbk implements DevicesSpareInService {

    @Override
    public Result save(DevicesSpareIn spareIn) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesSpareOut(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}
