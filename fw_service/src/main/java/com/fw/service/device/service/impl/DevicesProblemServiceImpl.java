package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesProblem;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesProblemDao;
import com.fw.service.device.service.DevicesProblemService;
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
 * @description: 设备问题业务逻辑层
 * @author: wqiang
 * @create: 2020-10-22 09:16
 **/

@Service
public class DevicesProblemServiceImpl implements DevicesProblemService {

    @Autowired
    private DevicesProblemDao devicesProblemDao;

    @Override
    public Result save(DevicesProblem devicesProblem) {
        int flag = 0;
        if(CheckUtils.isNull(devicesProblem.getId())){
            flag = devicesProblemDao.insert(devicesProblem);
        }else{
            DevicesProblem dp = devicesProblemDao.findProblemById(devicesProblem.getId());
            if(dp == null){
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = devicesProblemDao.update(devicesProblem);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        if(CheckUtils.isNull(ids)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> listIds = Arrays.asList(ids.split(","));

        return devicesProblemDao.delete(listIds) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, String content, Integer status, Integer type, Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DevicesProblem> list = devicesProblemDao.findList(name, content, status, type);
        PageInfo<DevicesProblem> devicesRepairPage = new PageInfo<>(list);
        return ResultUtils.success(devicesRepairPage);
    }
}
