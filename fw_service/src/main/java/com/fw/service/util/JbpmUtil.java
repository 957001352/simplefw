package com.fw.service.util;


import com.fw.entity.e2c.User;
import com.fw.entity.jbpm.JbpmDeployment;
import com.fw.entity.jbpm.JbpmDeployprop;
import com.fw.entity.jbpm.JbpmExecution;
import com.fw.entity.jbpm.JbpmTask;
import com.fw.service.enums.CodeEnum;
import com.fw.service.jbpm.dao.JbpmDeploymentDao;
import com.fw.service.jbpm.dao.JbpmExecutionDao;
import com.fw.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程审核工具类
 * @author lpsong
 * @since 2020-10-26
 */
@Component
@Slf4j
public class JbpmUtil {
    @Autowired
    private JbpmExecutionDao jbpmExecutionDao;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private JbpmDeploymentDao jbpmDeploymentDao;
    /**
    * 开启审核流程
     * @param executionId
     * @param dataId
     * @param createUser
     * @param formCode
    * @return
    */
    public Integer startExecution(Integer executionId, String dataId, Integer createUser, String formCode) {
        JbpmExecution jbpmExecution = new JbpmExecution(executionId, dataId, createUser, formCode);
        Integer flag = 0;
        //审核流程表单
        JbpmDeployment jbpmDeployment = jbpmDeploymentDao.findDeploymentByCode(jbpmExecution.getFormCode());
        if (jbpmDeployment != null) {
            //查询待审核节点
            JbpmDeployprop deployprop = jbpmExecutionDao.findFirstJbpmNode(jbpmDeployment.getId());
            if (deployprop != null) {
                //设置待审核状态
                jbpmExecution.setAuditStatus("待" + e2CServicesUtil.findUserNameById(deployprop.getAuditUsers()) + "审核");
            }
            jbpmExecution.setDeploymentId(jbpmDeployment.getId());
            //id为空新增
            if (CheckUtils.isNull(jbpmExecution.getId())) {
                jbpmExecution.setJbpmNo(jbpmExecutionDao.findCode(CodeEnum.JBPM_SP.getCode()));
                flag = jbpmExecutionDao.insertExecution(jbpmExecution);
            } else {
                jbpmExecution.setAuditResult(0);
                flag = jbpmExecutionDao.updateExecution(jbpmExecution);
            }
            //开启流程实例
            if (flag > 0) {
                //插入代办任务
                JbpmTask jbpmTask = new JbpmTask();
                jbpmTask.setDataId(jbpmExecution.getDataId());
                jbpmTask.setDeploymentId(jbpmExecution.getDeploymentId());
                jbpmTask.setExecutionId(jbpmExecution.getId());
                jbpmTask.setDeploypropId(deployprop.getId());
                jbpmTask.setAuditUser(deployprop.getAuditUsers());
                jbpmExecutionDao.insertTask(jbpmTask);
            }
        }
        return flag;
    }

}
