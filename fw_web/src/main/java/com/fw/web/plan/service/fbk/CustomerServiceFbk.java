package com.fw.web.plan.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldHouse;
import com.fw.entity.plan.Customer;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldHouseService;
import com.fw.web.plan.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceFbk implements CustomerService {
    @Override
    public Result save(Customer customer) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
