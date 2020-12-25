package com.fw.service.task;

import com.fw.entity.audit.AuditPlan;
import com.fw.entity.audit.AuditTask;
import com.fw.service.audit.dao.AuditTaskDao;
import com.fw.service.enums.CodeEnum;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: dhlk_fw_plat
 * @description:定时创建
 * @author: wqiang
 * @create: 2020-10-30 15:02
 **/
public class ToAuditTask implements Runnable {

    private AuditPlan auditPlan;
    private AuditTaskDao auditTaskDao;

    public ToAuditTask(AuditPlan auditPlan,AuditTaskDao auditTaskDao){
        this.auditPlan = auditPlan;
        this.auditTaskDao=auditTaskDao;
    }


    @Override
    public void run() {
        String auditUsersId = auditPlan.getAuditUsers();
        if(!CheckUtils.isNull(auditUsersId)){
            //计划分配多少人，就对应多个任务
            for(String userId : auditUsersId.split(",")){
                AuditTask auditTask = new AuditTask();
                //auditTask.setTaskNo("SH"+ DateUtils.getNowTime()+"_planId_"+auditPlan.getId()+"_userID_"+userId); //测试
                auditTask.setTaskNo(auditTaskDao.findCode(CodeEnum.AUDIT_01.getCode()));
                auditTask.setAuditPlanId(auditPlan.getId());
                auditTask.setAuditTeamId(auditPlan.getAuditTeamId());
                auditTask.setExecuteUser(Integer.parseInt(userId));
                auditTaskDao.insert(auditTask);
            }
        }
    }
}
