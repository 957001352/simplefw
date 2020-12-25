package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.ProductDevicesSpareService;
import org.springframework.stereotype.Service;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description: 备品备件web service实现类
 */
@Service
public class ProductDevicesSpareServiceFbk implements ProductDevicesSpareService {
    @Override
    public Result save(ProductDevicesSpare productDevicesSpare) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getProductDevicesSpare(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String code,String name,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
