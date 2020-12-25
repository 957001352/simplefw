package com.fw.service.plan.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.plan.Customer;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.enums.ResultEnum;
import com.fw.service.plan.dao.CustomerDao;
import com.fw.service.plan.dao.InjectionMoldingDao;
import com.fw.service.plan.service.CustomerService;
import com.fw.service.plan.service.InjectionMoldingService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 排产客户管理
 * @author lpsong
 * @since 2020-11-25
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Result save(Customer customer) {
        Integer flag = 0;
        if (customerDao.isRepeatName(customer)>0) {
            return ResultUtils.error("客户名称重复");
        }
        //id为空新增设备保养项目
        if (CheckUtils.isNull(customer.getId())) {
            flag = customerDao.insert(customer);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            Customer entrty = customerDao.selectById(customer.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = customerDao.update(customer);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        flag = customerDao.delete(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        return ResultUtils.success(new PageInfo<>(customerDao.findList(name)));
    }
}
