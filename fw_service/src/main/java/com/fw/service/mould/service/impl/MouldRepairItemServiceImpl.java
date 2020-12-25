package com.fw.service.mould.service.impl;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldRepairItem;
import com.fw.enums.ResultEnum;
import com.fw.service.mould.dao.MouldRepairItemDao;
import com.fw.service.mould.service.MouldRepairItemService;
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
 * @description:
 * @author: wqiang
 * @create: 2020-10-22 15:04
 **/
@Service
public class MouldRepairItemServiceImpl implements MouldRepairItemService {

    @Autowired
    private MouldRepairItemDao mouldRepairItemDao;

    @Override
    public Result save(MouldRepairItem mouldRepairItem) {

        Integer flag = 0;
        if (CheckUtils.isNull(mouldRepairItem.getId())) {
            if (mouldRepairItemDao.findItemByName(mouldRepairItem.getName()) > 0) {
                return ResultUtils.error("项目名称已存在,请重新输入");
            }
            flag = mouldRepairItemDao.insert(mouldRepairItem);
        } else {
            MouldRepairItem mr = mouldRepairItemDao.findItemById(mouldRepairItem.getId());
            if (mr == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            if (mouldRepairItemDao.findItemByIdAndName(mouldRepairItem.getId(), mouldRepairItem.getName()) > 0) {
                return ResultUtils.error("项目名称已存在,请重新输入");
            }
            flag = mouldRepairItemDao.update(mouldRepairItem);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MouldRepairItem> repairItemList = mouldRepairItemDao.findList(name);
        PageInfo<MouldRepairItem> devicesRepairPage = new PageInfo<>(repairItemList);
        return ResultUtils.success(devicesRepairPage);
    }

    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        return mouldRepairItemDao.delete(lists) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
