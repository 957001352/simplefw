package com.fw.service.jbpm.service.impl;

import com.alibaba.fastjson.JSON;
import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.jbpm.*;
import com.fw.enums.ResultEnum;
import com.fw.service.jbpm.dao.JbpmDeploymentDao;
import com.fw.service.jbpm.dao.JbpmExecutionDao;
import com.fw.service.jbpm.service.JbpmExecutionService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 审核任务办理
 * @author lpsong
 * @since 2020-10-22
 */
@Service
public class JbpmExecutionServiceImpl implements JbpmExecutionService {

    @Autowired
    private JbpmExecutionDao jbpmExecutionDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private JbpmDeploymentDao jbpmDeploymentDao;
    @Override
    @Transactional
    public Result startExecution(JbpmExecution jbpmExecution) {
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
                jbpmExecution.setCreateUser(authUserUtil.userId());
                flag = jbpmExecutionDao.insertExecution(jbpmExecution);
            } else {
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
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result doTask(JbpmExecution jbpmExecution) {
        Integer flag = 0;
        JbpmTask jbpmTask=null;
        Integer userId=authUserUtil.userId();
        String idea="审核通过";
        if(jbpmExecution.getAuditResult()==2){
            idea="审核不通过";
        }
        //id为空新增
        if (!CheckUtils.isNull(jbpmExecution.getId())) {
            JbpmDeployprop deployprop= jbpmExecutionDao.findNextJbpmNode(jbpmExecution.getDeploymentId(),jbpmExecution.getDeploypropId());
            /**
            * 如果deployprop不为空，说明还有下级流程，则更新任务状态为下级审核人，并给代办任务插入数据
            * 如果deployprop为空，说明流程结束，则更新任务状态为 审核通过
            * @return
            */
            if(idea.equals("审核通过")){
                if(deployprop!=null) {
                    //设置待审核状态
                    jbpmExecution.setAuditStatus("待" + e2CServicesUtil.findUserNameById(deployprop.getAuditUsers()) + "审核");
                    jbpmTask = new JbpmTask();
                    jbpmTask.setDataId(jbpmExecution.getDataId());
                    jbpmTask.setDeploymentId(jbpmExecution.getDeploymentId());
                    jbpmTask.setExecutionId(jbpmExecution.getId());
                    jbpmTask.setDeploypropId(deployprop.getId());
                    jbpmTask.setAuditUser(deployprop.getAuditUsers());
                }else{
                    jbpmExecution.setAuditStatus(idea);
                    jbpmExecution.setEndTime(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
                }
            }else{
                // xxx审核不通过
                jbpmExecution.setEndTime(DateUtils.getToday("yyyy-MM-dd HH:mm:ss"));
                if(!CheckUtils.isNull(userId)){
                    jbpmExecution.setAuditStatus(e2CServicesUtil.findUserNameById(userId.toString())+idea);
                }
            }
            //开启流程实例
            flag = jbpmExecutionDao.updateExecution(jbpmExecution);
            if (flag > 0) {
                jbpmExecutionDao.deleteWaitTask(jbpmExecution.getId());
                //当有下级审核节点，并且当前节点为审核通过，则插入待办任务
                if (jbpmTask != null && idea.equals("审核通过")) {
                    jbpmExecutionDao.insertTask(jbpmTask);
                }
                //如果是最后一个节点，且审核状态为通过是，更新被审核数据状态
                if (jbpmTask == null && idea.equals("审核通过")) {
                    JbpmDeployment deployment = jbpmDeploymentDao.selectById(jbpmExecution.getDeploymentId());
                    if (deployment != null&&!CheckUtils.isNull(deployment.getFormUrl())) {
                        this.updateFormData(deployment.getFormUrl(), jbpmExecution.getDataId());
                    }
                }
               //插入已办任务
                JbpmHistTask jbpmHistTask = new JbpmHistTask();
                jbpmHistTask.setDeploymentId(jbpmExecution.getDeploymentId());
                jbpmHistTask.setExecutionId(jbpmExecution.getId());
                jbpmHistTask.setAuditUser(userId);
                jbpmHistTask.setAuditResult(jbpmExecution.getAuditResult());
                jbpmHistTask.setAuditIdea(jbpmExecution.getAduitIdea());
                jbpmHistTask.setCreateTime(jbpmExecution.getTaskCreateTime());
                if(jbpmExecution.getAuditResult()==2){
                    idea="审核不通过";
                }
                if(!CheckUtils.isNull(userId)){
                    jbpmHistTask.setAuditNode(e2CServicesUtil.findUserNameById(userId.toString()) + idea);
                }
                jbpmExecutionDao.insertHistHask(jbpmHistTask);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
    /**
    * 更新被审核数据状态
     * @param url
     * @param id
    * @return
    */
    public Integer updateFormData(String url,String id) {
        Integer flag=0;
        Map<String, String> params=new HashMap<String, String>();
        params.put("id",id);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", headerUtil.cloudToken());
        //获取E2C设备数据
        try {
            HttpClientResult clientResult = HttpClientUtils.doGet("http://127.0.0.1:10101/" + url,headers, params);
            if (clientResult.getCode() == 200) {
                Result result = JSON.parseObject(clientResult.getContent(), Result.class);
                if (result.getCode() == 0) {
                    flag=100;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    @Override
    public Result findWaitTaskList(String jbpmNo, String createUser, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> params=new HashMap<String, String>();
        params.put("name",createUser);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(),params);
        List<Integer> userIds=null;
        if(!CheckUtils.isNull(createUser)){
            //如果申请人查询条件不为空，根据申请人查询结果为空，则直接返回空，如果不为空，则根据申请人进行数据查询
            if(userList==null||userList.size()==0){
                return ResultUtils.success(new PageInfo<>());
            }else{
                userIds=  userList.stream().map(User::getId).collect(Collectors.toList());
            }
        }
        List<JbpmExecution> list = jbpmExecutionDao.findWaitTaskList(authUserUtil.userId(),jbpmNo,userIds,startTime,endTime);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(userList)) {
            list.forEach(item -> {
                userList.forEach(vo -> {
                    if (!CheckUtils.isNull(item.getCreateUser())&&item.getCreateUser().equals(vo.getId())) {
                        item.setCreateUserName(vo.getName());
                    }
                });
                //审核历史记录
                List<JbpmHistTask> histTaskList= item.getHistTaskList();
                if(histTaskList!=null&&histTaskList.size()>0){
                    histTaskList.forEach(histTask -> {
                        userList.forEach(vo -> {
                            if (!CheckUtils.isNull(histTask.getAuditUser())&&histTask.getAuditUser().equals(vo.getId())) {
                                histTask.setAuditUserName(vo.getName());
                            }
                        });
                    });
                    item.setHistTaskList(histTaskList);
                }
            });
        }
        return ResultUtils.success(new PageInfo<>(list));
    }

    @Override
    public Result findHistTaskList(String jbpmNo, String createUser, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> params=new HashMap<String, String>();
        params.put("name",createUser);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(),params);
        List<Integer> userIds=null;
        if(!CheckUtils.isNull(createUser)){
            //如果申请人查询条件不为空，根据申请人查询结果为空，则直接返回空，如果不为空，则根据申请人进行数据查询
            if(userList==null||userList.size()==0){
                return ResultUtils.success(new PageInfo<>());
            }else{
                userIds=  userList.stream().map(User::getId).collect(Collectors.toList());
            }
        }
        List<JbpmExecution> list = jbpmExecutionDao.findHistTaskList(authUserUtil.userId(),jbpmNo,userIds,startTime,endTime);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(userList)) {
            list.forEach(item -> {
                userList.forEach(vo -> {
                    if (item.getCreateUser().equals(vo.getId())) {
                        item.setCreateUserName(vo.getName());
                    }
                });
                //设置审核历史记录审核人员姓名
                //审核历史记录
                List<JbpmHistTask> histTaskList= item.getHistTaskList();
                if(histTaskList!=null&&histTaskList.size()>0) {
                    histTaskList.forEach(histTask -> {
                        userList.forEach(vo -> {
                            if (histTask.getAuditUser().equals(vo.getId())) {
                                histTask.setAuditUserName(vo.getName());
                            }
                        });
                    });
                    item.setHistTaskList(histTaskList);
                }
            });
        }
        return ResultUtils.success(new PageInfo<>(list));
    }
    @Override
    public Result findHistTaskDeatilList(Integer executionId) {
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<JbpmHistTask> list = jbpmExecutionDao.findHistTaskDeatilList(executionId);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(userList)) {
            list.forEach(item -> {
                userList.forEach(vo -> {
                    if (item.getAuditUser().equals(vo.getId())) {
                        item.setAuditUserName(vo.getName());
                    }
                });
            });
        }
        return ResultUtils.success(list);
    }
}
