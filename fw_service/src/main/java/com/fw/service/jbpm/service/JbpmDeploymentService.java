package com.fw.service.jbpm.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheck;
import com.fw.entity.jbpm.JbpmDeployment;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-22
 */
public interface JbpmDeploymentService {

    /**
     * 新增
     *
     * @param jbpmDeployment
     * @return
     */
    Result save(JbpmDeployment jbpmDeployment);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 列表查询
     *
     * @param code
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String code, String name,Integer pageNum, Integer pageSize);


    /**
    * 审核对象查询
     * @param
    * @return
    */
    Result findJbpmFormList();



    /**
     * 用户列表查询
     */
    Result findUserList();
}
