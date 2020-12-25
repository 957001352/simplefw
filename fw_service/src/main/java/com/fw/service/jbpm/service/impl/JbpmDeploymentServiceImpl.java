package com.fw.service.jbpm.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.jbpm.JbpmDeployment;
import com.fw.entity.jbpm.JbpmDeployprop;
import com.fw.enums.ResultEnum;
import com.fw.service.jbpm.dao.JbpmDeploymentDao;
import com.fw.service.jbpm.service.JbpmDeploymentService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 审核流程定义
 * @author lpsong
 * @since 2020-10-22
 */
@Service
public class JbpmDeploymentServiceImpl implements JbpmDeploymentService {

    @Autowired
    private JbpmDeploymentDao jbpmDeploymentDao;
    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private AuthUserUtil authUserUtil;
    @Override
    @Transactional
    public Result save(JbpmDeployment jbpmDeployment) {
        Integer flag = 0;
        //id为空新增设备保养项目
        if (CheckUtils.isNull(jbpmDeployment.getId())) {
           jbpmDeployment.setCreateUser(authUserUtil.userId());
            flag = jbpmDeploymentDao.insert(jbpmDeployment);
            if(flag>0){
                //新增点检项目
                List<JbpmDeployprop> checkDetailList=jbpmDeployment.getNoteList();
                if(checkDetailList!=null&&checkDetailList.size()>0){
                    for(JbpmDeployprop jbpmNode:checkDetailList){
                        jbpmNode.setDeploymentId(jbpmDeployment.getId());
                        jbpmDeploymentDao.insertJbpmNode(jbpmNode);
                    }
                }
            }
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            JbpmDeployment jbppm = jbpmDeploymentDao.selectById(jbpmDeployment.getId());
            if (CheckUtils.isNull(jbppm)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            jbpmDeployment.setUpdateUser(authUserUtil.userId());
            jbpmDeployment.setUpdateTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            flag = jbpmDeploymentDao.update(jbpmDeployment);
            if(flag>0){
                //先删除，在添加
                List<String> ids=new ArrayList<>();
                ids.add(jbpmDeployment.getId().toString());
                jbpmDeploymentDao.deleteJbpmNode(ids);
                //新增点检项目
                List<JbpmDeployprop> checkDetailList=jbpmDeployment.getNoteList();
                if(checkDetailList!=null&&checkDetailList.size()>0){
                    for(JbpmDeployprop jbpmNode:checkDetailList){
                        jbpmNode.setDeploymentId(jbpmDeployment.getId());
                        jbpmDeploymentDao.insertJbpmNode(jbpmNode);
                    }
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        flag = jbpmDeploymentDao.delete(list);
        //删除点检项目
        if(flag>0){
            jbpmDeploymentDao.deleteJbpmNode(list);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String code, String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<JbpmDeployment> list = jbpmDeploymentDao.findList(code,name);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(userList)) {
            list.forEach(item -> {
                List<JbpmDeployprop> noteList=item.getNoteList();
                if (!CollectionUtils.isEmpty(noteList)) {
                    noteList.forEach(note -> {
                        List<String> userNames=new ArrayList<>();
                        for(String userId:note.getAuditUsers().split(",")){
                            userList.forEach(vo -> {
                                if (userId.equals(vo.getId().toString())) {
                                    userNames.add(vo.getName());
                                }
                            });
                        }
                        note.setAuditUserNames(StringUtils.join(userNames.toArray(), ","));
                    });
                }
            });
        }
        return ResultUtils.success(new PageInfo<>(list));
    }

    @Override
    public Result findJbpmFormList() {
        return   ResultUtils.success(jbpmDeploymentDao.findJbpmFormList());
    }


    @Override
    public Result findUserList() {
        return ResultUtils.success(e2CServicesUtil.getUserList(headerUtil.cloudToken()));
    }
}
