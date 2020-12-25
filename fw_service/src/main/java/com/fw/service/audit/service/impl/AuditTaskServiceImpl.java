package com.fw.service.audit.service.impl;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditItemDetail;
import com.fw.entity.audit.AuditTask;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.service.audit.dao.AuditQuestionDao;
import com.fw.service.audit.dao.AuditTaskDao;
import com.fw.service.audit.service.AuditTaskService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-31 11:25
 **/

@Service
@Transactional
public class AuditTaskServiceImpl implements AuditTaskService {

    @Autowired
    private AuditTaskDao auditTaskDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private  AuditQuestionDao auditQuestionDao;
    /**
     * 获取任务信息
     *
     * @param taskNo
     * @param exeUser
     * @param status
     * @param executeTime
     * @return
     */
    @Override
    public Result findList(String taskNo, String exeUser, Integer status, String executeTime, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LinkedHashMap<String, Object>> lists = auditTaskDao.findList(taskNo, status, executeTime);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());

        if (!CollectionUtils.isEmpty(lists)) {
            for (int i = 0; i < lists.size(); i++) {
                for (User user : userList) {
                    if (user.getId().toString().equals(lists.get(i).get("exeUser").toString())) {
                        lists.get(i).put("userName",user.getName());
                    }
                }
            }

            //过滤用户名称
            if (!CheckUtils.isNull(exeUser)) {
                List<LinkedHashMap<String, Object>> taskList = lists.stream().filter(e ->
                        e.get("userName").toString().contains(exeUser)).collect(Collectors.toList());
                PageInfo<LinkedHashMap<String, Object>> ItemPage = new PageInfo<>(taskList);
                return ResultUtils.success(ItemPage);
            }
        }
        PageInfo<LinkedHashMap<String, Object>> ItemPage = new PageInfo<>(lists);
        return ResultUtils.success(ItemPage);
    }

    /**
     * 根据任务ID获取详情
     *
     * @param id
     * @return
     */
    @Override
    public Result findTaskDetails(Integer id,Integer status) {
        List<LinkedHashMap<String, Object>> taskDetails = null;
        if(0 == status){ //未完成
            taskDetails = auditTaskDao.findUnfinishedTaskDetails(id);
        }else{ // 1已完成
            taskDetails = auditTaskDao.findTaskDetails(id);
        }
        return ResultUtils.success(taskDetails);
    }


    /**
     * 执行任务
     *
     * @param auditTask
     * @return
     */
    @Override
    public Result exeTask(AuditTask auditTask) {
        //获取审核项目明细，插入明细
        List<AuditItemDetail> auditItemDetailList = auditTask.getAuditItemDetailList();
        if (!CollectionUtils.isEmpty(auditItemDetailList)) {
            Integer count = auditTaskDao.insertItemDetails(auditItemDetailList);
            if (count > 0) {
                //更新任务执行时间和状态
                auditTask.setExecuteTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                auditTask.setStatus(1); //0未执行 1已执行
                auditTaskDao.update(auditTask);
                //过滤出审核不通过的审核项目，插入审核问题中
                List<AuditItemDetail> questionList = auditItemDetailList.stream().filter(s->s.getAuditItemResult() ==0).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(questionList)) {
                    auditQuestionDao.insertBatch(questionList,authUserUtil.userId());
                }
                return ResultUtils.success();
            }
        }
        return ResultUtils.error(ResultEnum.PARAM_ERR);
    }
}
