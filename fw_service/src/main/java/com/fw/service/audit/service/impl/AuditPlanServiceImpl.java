package com.fw.service.audit.service.impl;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditPlan;
import com.fw.entity.audit.AuditTeam;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.service.audit.dao.AuditItemDao;
import com.fw.service.audit.dao.AuditPlanDao;
import com.fw.service.audit.service.AuditItemService;
import com.fw.service.audit.service.AuditPlanService;
import com.fw.service.config.ScheduledConf;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.fw.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

/**
 * @program: dhlk_fw_plat
 * @description: 分层审核项目业务层
 * @author: wqiang
 * @create: 2020-10-29 10:50
 **/
@Service
public class AuditPlanServiceImpl implements AuditPlanService {

    @Autowired
    private AuditPlanDao auditPlanDao;

    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;


    @Override
    public Result save(AuditPlan auditPlan) {

        int flag = 0;
        if (CheckUtils.isNull(auditPlan.getId())) {
            if (!CheckUtils.isNull(auditPlan.getName())) {
                if (auditPlanDao.findPlanByIdOrName(null, auditPlan.getName()) != null) {
                    return ResultUtils.error("计划名称已存在,请重新输入");
                }
            }
            //cron表达式
            auditPlan.setCron(getCron(auditPlan.getKeepCycle()));
            flag = auditPlanDao.insert(auditPlan);
        } else {
            if (auditPlanDao.findPlanByIdOrName(auditPlan.getId(), null) == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            if (auditPlanDao.findPlanByIdAndName(auditPlan.getId(), auditPlan.getName()) > 0) {
                return ResultUtils.error("计划名称已存在,请重新输入");
            }
            flag = auditPlanDao.update(auditPlan);
            //计划禁用后,移除线程池中的计划
            if (flag > 0) {
                //设备禁用 0启用 1禁用
                if (auditPlan.getStatus() == 1) {
                    //把从map中获取任务
                    //Enumeration<String> keys = ScheduledConf.map.keys();
                    ScheduledFuture scheduledFuture = ScheduledConf.map.get("auditPlan_" + auditPlan.getId());
                    if (scheduledFuture != null) {
                        //线程取消
                        scheduledFuture.cancel(true);
                        // 查看任务是否在正常执行之前结束,正常true
                        boolean cancelled = scheduledFuture.isCancelled();
                        while (!cancelled) {
                            scheduledFuture.cancel(true);
                        }
                        //从线程池中移除
                        ScheduledConf.map.remove("auditPlan_" + auditPlan.getId());
                    }
                }
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return auditPlanDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, String auditTeamName, String userName, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AuditPlan> auditPlanList = auditPlanDao.findList(name, auditTeamName);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        //获取用户名称
        if (!CollectionUtils.isEmpty(auditPlanList)) {
            for (AuditPlan auditPlan : auditPlanList) {
                String auditUsersName = "";
                String auditUsers = auditPlan.getAuditUsers();
                if (!CheckUtils.isNull(auditUsers)) {
                    for (String userId : auditUsers.split(",")) {
                        for (User user : userList) {
                            if (userId.equals(user.getId().toString())) {
                                auditUsersName += user.getName() + ";";
                                auditPlan.setAuditUsersName(!"".equals(auditUsersName) ? auditUsersName.substring(0, auditUsersName.length() - 1) : "");
                            }
                        }
                    }
                }
            }
        }
        //过滤用户
        if (!CheckUtils.isNull(userName) && !CollectionUtils.isEmpty(auditPlanList)) {
            List<AuditPlan> list = auditPlanList.stream().filter(e ->
                    e.getAuditUsersName().contains(userName)
            ).collect(Collectors.toList());

            PageInfo<AuditPlan> AuditPlanPage = new PageInfo<>(list);
            return ResultUtils.success(AuditPlanPage);
        }
        PageInfo<AuditPlan> AuditPlanPage = new PageInfo<>(auditPlanList);
        return ResultUtils.success(AuditPlanPage);
    }

    /**
     * 获取Cron表达式
     *
     * @param cycle
     * @return
     */
    public String getCron(String cycle) {
        String cron = "";
        switch (cycle) {
            case "0": //每天
                cron = "0 0 0 * * ?";
                break;
            case "1"://每周(每星期一的凌晨0点)
                cron = "0 0 0 ? * MON";
                break;
            case "2"://每月(每月1号凌晨0点执行一次)
                cron = "0 0 0 1 * ?";
                break;
            case "3"://每季度的第一天
                cron = "0 0 0 1 1,4,7,10 ?";
                break;
            case "4"://每年(每年的一月一号)
                cron = "0 0 0 1W * ? *";
                break;
        }
        return cron;
    }
}
