package com.fw.service.plan.service;

import com.fw.domain.Result;
import com.fw.entity.plan.Customer;
import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.ReworkStopList;

/**
 * 排产客户管理
 * @author lpsong
 * @since 2020-11-26
 */
public interface CustomerService {

    /**
     * 新增
     * @param customer
     * @return
     */
    Result save(Customer customer);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result delete(String ids);
    /**
    *  列表查询
     * @param name
     * @param pageNum
     * @param pageSize
    * @return
    */
    Result findList(String name, Integer pageNum, Integer pageSize);


}
