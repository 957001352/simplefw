package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepItem;
import com.fw.entity.mould.MouldKeepTeam;
import com.fw.enums.ResultEnum;
import com.fw.service.mould.dao.MouldDevicesDao;
import com.fw.service.mould.dao.MouldKeepItemDao;
import com.fw.service.mould.dao.MouldKeepTeamDao;
import com.fw.service.mould.service.MouldKeepTeamService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 模具保养项目组 实现类
 *
 * @author xkliu
 * @date 2020/10/27
 */
@Service
public class MouldKeepTeamServiceImpl implements MouldKeepTeamService {

    @Autowired
    private MouldKeepTeamDao mouldKeepTeamDao;

    @Autowired
    private MouldKeepItemDao mouldKeepItemDao;

    @Autowired
    private MouldDevicesDao mouldDevicesDao;

    @Override
    @Transactional
    public Result save(MouldKeepTeam mouldKeepTeam) {
        Integer flag = 0;
        //新增
        if (CheckUtils.isNull(mouldKeepTeam.getId())) {
            //名称不为空校验是否重复
            if (!CheckUtils.isNull(mouldKeepTeam.getName())) {
                boolean res = mouldKeepTeamDao.verifyName(mouldKeepTeam.getName());
                if (res) {
                    return ResultUtils.error("保养表单名称重复");
                }
            }
            if(mouldKeepTeam.getKeepType().equals(2) || mouldKeepTeam.getKeepType().equals(3) || mouldKeepTeam.getKeepType().equals(4)){
                boolean result = mouldKeepTeamDao.verifyKeepType(mouldKeepTeam.getId(),mouldKeepTeam.getKeepType());
                if (result) {
                    return ResultUtils.error("保养类型已存在");
                }
            }
            flag = mouldKeepTeamDao.insert(mouldKeepTeam);
        } else {
            MouldKeepTeam keepTeam = mouldKeepTeamDao.selectById(mouldKeepTeam.getId());
            if (keepTeam == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            //修改时表单名称与传过来名称不同的时候进行校验
            if (!mouldKeepTeam.getName().equals(keepTeam.getName())) {
                //校验是否有重复的表单名称
                boolean res = mouldKeepTeamDao.verifyName(mouldKeepTeam.getName());
                if (res) {
                    return ResultUtils.error("保养表单名称重复");
                }
            }
            if(mouldKeepTeam.getKeepType().equals(2) || mouldKeepTeam.getKeepType().equals(3) || mouldKeepTeam.getKeepType().equals(4)){
                boolean result = mouldKeepTeamDao.verifyKeepType(mouldKeepTeam.getId(),mouldKeepTeam.getKeepType());
                if (result) {
                    return ResultUtils.error("保养类型已存在");
                }
            }
            flag = mouldKeepTeamDao.update(mouldKeepTeam);
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
        List<String> lists = Arrays.asList(ids.split(","));
        flag = mouldKeepTeamDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result getAllKeepItem() {
        return ResultUtils.success(mouldKeepItemDao.findAll());
    }

    @Override
    public Result findList(String name, String mouldCode, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldKeepTeam> lists = mouldKeepTeamDao.findList(name, mouldCode);
        PageInfo<MouldKeepTeam> keepItemPage = new PageInfo<>(lists);
        return ResultUtils.success(keepItemPage);
    }

    @Override
    public Result getMouldCode() {
        return ResultUtils.success(mouldDevicesDao.getMouldDevices());
    }

    @Override
    public Result getMouldKeepTeam(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        MouldKeepTeam keepTeam = mouldKeepTeamDao.selectById(id);
        if (keepTeam == null) {
            return ResultUtils.success(keepTeam);
        }
        String keepItemIds = keepTeam.getKeepItemIds();
        //设备保养项目ids不为空,则获取设备保养项目的集合
        if (!CheckUtils.isNull(keepItemIds)) {
            List<String> ids = Arrays.asList(keepItemIds.split(","));
            List<MouldKeepItem> items = mouldKeepItemDao.findKeepItemByIds(ids);
            keepTeam.setKeepItems(items);
        }
        return ResultUtils.success(keepTeam);
    }

    @Override
    public Result getKeepItemByCycle(Integer cycle) {
        if (CheckUtils.isNull(cycle)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<MouldKeepItem> lists = mouldKeepItemDao.getKeepItemByCycle(cycle);
        return ResultUtils.success(lists);
    }
}
