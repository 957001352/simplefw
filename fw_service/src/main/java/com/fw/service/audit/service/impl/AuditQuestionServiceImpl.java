package com.fw.service.audit.service.impl;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditQuestion;
import com.fw.entity.device.DevicesCheck;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.plan.Customer;
import com.fw.enums.ResultEnum;
import com.fw.service.audit.dao.AuditQuestionDao;
import com.fw.service.audit.service.AuditQuestionService;
import com.fw.service.plan.dao.CustomerDao;
import com.fw.service.plan.service.CustomerService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 问题管理
 * @author lpsong
 * @since 2020-11-25
 */
@Service
public class AuditQuestionServiceImpl implements AuditQuestionService {

    @Autowired
    private AuditQuestionDao auditQuestionDao;
    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private AuthUserUtil authUserUtil;
    @Override
    public Result save(AuditQuestion auditQuestion) {
        Integer flag = 0;
        //id为空新增
        if (CheckUtils.isNull(auditQuestion.getId())) {
            auditQuestion.setCreateUser(authUserUtil.userId());
            flag = auditQuestionDao.insert(auditQuestion);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            AuditQuestion entrty = auditQuestionDao.selectById(auditQuestion.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            auditQuestion.setDealUser(authUserUtil.userId());
            if(!CheckUtils.isNull(auditQuestion.getStatus())){
                auditQuestion.setDealTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            }
            flag = auditQuestionDao.update(auditQuestion);
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
        flag = auditQuestionDao.delete(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String dutyUser,Integer status, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //查询E2C生产设备
        Map<String, String> params=new HashMap<String, String>();
        params.put("name",dutyUser);
        List<User> whereList  = e2CServicesUtil.getUserList(headerUtil.cloudToken(),params);
        List<Integer> createUserIds=null;
        //拼接用户查询条件
        if(!CheckUtils.isNull(dutyUser)){
            //如果责任人查询条件不为空，根据责任人查询结果为空，则直接返回空，如果不为空，则根据责任人id进行数据查询
            if(whereList==null||whereList.size()==0){
                return ResultUtils.success(new PageInfo<>());
            }else{
                createUserIds=  whereList.stream().map(User::getId).collect(Collectors.toList());
            }
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<AuditQuestion> list =auditQuestionDao.findList(createUserIds,status);
        if (!CollectionUtils.isEmpty(list)&&!CollectionUtils.isEmpty(userList)) {
            //设置责任人姓名
            list.forEach(item -> {
                if(userList!=null&&userList.size()>0){
                    userList.forEach(vo -> {
                        if (!CheckUtils.isNull(item.getDutyUser())&&item.getDutyUser().equals(vo.getId())) {
                            item.setDutyUserName(vo.getName());
                        }
                        if (!CheckUtils.isNull(item.getCreateUser())&&item.getCreateUser().equals(vo.getId())) {
                            item.setCreateUserName(vo.getName());
                        }
                    });
                }
           });
        }
        return ResultUtils.success(new PageInfo<>(list));
    }
}
