package com.fw.service.audit.service.impl;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditTeam;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.enums.ResultEnum;
import com.fw.service.audit.dao.AuditItemDao;
import com.fw.service.audit.dao.AuditTeamDao;
import com.fw.service.audit.service.AuditItemService;
import com.fw.service.audit.service.AuditTeamService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: 分层审核项目业务层
 * @author: wqiang
 * @create: 2020-10-29 10:50
 **/
@Service
public class AuditTeamServiceImpl implements AuditTeamService {

    @Autowired
    private AuditTeamDao auditTeamDao;

    @Override
    public Result save(AuditTeam auditTeam) {

        int flag = 0;
        if (CheckUtils.isNull(auditTeam.getId())) {
            AuditTeam at = auditTeamDao.findTeamByIdOrName(null, auditTeam.getName());
            if (at != null) {
                return ResultUtils.error("表单名称已存在,请重新输入！");
            }
            flag = auditTeamDao.insert(auditTeam);
        } else {
            //更新之前，判断是否别的串口已经删除
            AuditTeam auTeam = auditTeamDao.findTeamByIdOrName(auditTeam.getId(), null);
            if (auTeam == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            if (auditTeamDao.findTeamByIdAndName(auditTeam.getId(), auditTeam.getName()) > 0) {
                return ResultUtils.error("表单名称已存在,请重新输入！");
            }
            flag = auditTeamDao.update(auditTeam);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return auditTeamDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AuditTeam> lists = auditTeamDao.findList(name);
        PageInfo<AuditTeam> AuditTeamPage = new PageInfo<>(lists);
        return ResultUtils.success(AuditTeamPage);
    }
}
