package com.fw.service.jbpm.dao;

import com.fw.entity.device.DevicesCheck;
import com.fw.entity.device.DevicesCheckDetail;
import com.fw.entity.jbpm.JbpmDeployment;
import com.fw.entity.jbpm.JbpmDeployprop;
import com.fw.entity.jbpm.JbpmForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-22
 */
@Repository
public interface JbpmDeploymentDao {

    /**
     * 新增
     *
     * @param jbpmDeployment
     * @return
     */
    Integer insert(JbpmDeployment jbpmDeployment);

    /**
     * 修改
     *
     * @param jbpmDeployment
     * @return
     */
    Integer update(JbpmDeployment jbpmDeployment);


    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);
    /**
     * 根据id获取JbpmDeployment
     * @param id
     * @return
     */
    JbpmDeployment selectById(@Param("id") Integer id);

    /**
     * 根据审核对象code获取JbpmDeployment
     * @param formCode
     * @return
     */
    JbpmDeployment findDeploymentByCode(@Param("formCode") String formCode);

    /**
     * 列表查询
     * @param code
     * @param name
     * @return
     */
    List<JbpmDeployment> findList(@Param("code") String code,
                                @Param("name") String name);

    /**
     * 新增流程节点
     *
     * @param jbpmDeployprop
     * @return
     */
    Integer insertJbpmNode(JbpmDeployprop jbpmDeployprop);

    /**
     * 修改流程节点
     * @param jbpmDeployprop
     * @return
     */
    Integer updateJbpmNode(JbpmDeployprop jbpmDeployprop);


    /**
     * 删除流程节点
     * @param list
     * @return
     */
    Integer deleteJbpmNode(List<String> list);


    /**
    * 查询流程节点
     * @param jbpmId
    * @return
    */
    List<JbpmDeployprop> findJbpmNodeList(@Param("jbpmId") Integer jbpmId);


    /**
    * 审核对象查询
     * @param
    * @return
    */
    List<JbpmForm> findJbpmFormList();



}
