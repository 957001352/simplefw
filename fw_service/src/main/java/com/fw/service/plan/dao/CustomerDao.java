package com.fw.service.plan.dao;

import com.fw.entity.plan.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 排产客户管理
 * @author lpsong
 * @since 2020-11-26
 */
@Repository
public interface CustomerDao {

    /**
     * 新增
     * @param customer
     * @return
     */
    Integer insert(Customer customer);

    /**
     * 修改
     * @param customer
     * @return
     */
    Integer update(Customer customer);


    /**
     *取消
     * @param list
     * @return
     */
    Integer delete(List<String> list);
    /**
     * 根据id获取仓库信息
     *
     * @param id
     * @return
     */
    Customer selectById(@Param("id") Integer id);

    /**
    *  列表查询
     * @param name 客户名称
    * @return
    */
    List<Customer> findList(@Param("name") String name);

    /**
     * 判断名称是否重复
     * @param customer
     * @return
     */
    Integer isRepeatName(Customer customer);
}
