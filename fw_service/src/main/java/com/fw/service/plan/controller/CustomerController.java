package com.fw.service.plan.controller;

import com.fw.domain.Result;
import com.fw.entity.plan.Customer;
import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.service.plan.service.CustomerService;
import com.fw.service.plan.service.InjectionMoldingService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 排产客户管理
 * @author lpsong
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 新增/修改
     * @param customer
     * @return
     */
    @PostMapping(value = "/save")
    //@RequiresPermissions("customer:save")
    public Result save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    //@RequiresPermissions("customer:delete")
    public Result delete(@RequestParam(value = "ids", required = false) String ids) {
        return customerService.delete(ids);
    }
    /**
     * 列表查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    //@RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return customerService.findList(name, pageNum, pageSize);
    }
}
