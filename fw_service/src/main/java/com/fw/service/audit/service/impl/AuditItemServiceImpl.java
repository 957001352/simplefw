package com.fw.service.audit.service.impl;

import com.fw.domain.Result;
import com.fw.entity.audit.AuditItem;
import com.fw.entity.audit.AuditPlan;
import com.fw.enums.ResultEnum;
import com.fw.service.audit.dao.AuditItemDao;
import com.fw.service.audit.service.AuditItemService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: 分层审核项目业务层
 * @author: wqiang
 * @create: 2020-10-29 10:50
 **/
@Service
public class AuditItemServiceImpl implements AuditItemService {

    @Autowired
    private AuditItemDao auditItemDao;
    @Override
    public Result save(AuditItem auditItem) {

        int flag = 0;
        if(CheckUtils.isNull(auditItem.getId())){
            if(auditItemDao.findItemByNameOrid(null,auditItem.getName()) != null ){
                return ResultUtils.error("项目名称已存在,请重新输入");
            }
            flag = auditItemDao.insert(auditItem);
        }else {
            if(auditItemDao.findItemByNameOrid(auditItem.getId(),null) == null ){
                return ResultUtils.error("更新失败,请刷新页面");
            }
            if(auditItemDao.findItemByIdAndName(auditItem.getId(),auditItem.getName()) > 0){
                return ResultUtils.error("项目名称已存在,请重新输入");
            }
            flag = auditItemDao.update(auditItem);
        }
        return flag > 0 ? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        if(CheckUtils.isNull(ids)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return auditItemDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(String name,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AuditItem> lists = auditItemDao.findList(name);
        PageInfo<AuditItem> AuditItemPage = new PageInfo<>(lists);
        return ResultUtils.success(AuditItemPage);
    }
}
