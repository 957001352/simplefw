package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.mould.MouldUseRecord;
import com.fw.entity.produce.ProduceDuty;
import com.fw.enums.ResultEnum;
import com.fw.service.produce.dao.ProduceDutyDao;
import com.fw.service.produce.service.ProduceDutyService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 15:54
 **/


@Service
public class ProduceDutyServiceImpl implements ProduceDutyService {

    @Autowired
    private ProduceDutyDao produceDutyDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;


    @Override
    public Result save(ProduceDuty produceDuty) {
        if (produceDuty == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        produceDuty.setCreateUser(authUserUtil.userId());
        return produceDutyDao.insert(produceDuty) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProduceDuty> list = produceDutyDao.findList();
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(list)) {
            if (!CollectionUtils.isEmpty(userList)) {
                for (ProduceDuty produceDuty : list) {
                    for (User user : userList) {
                        if (produceDuty.getOperateUser() == user.getId()) {
                            produceDuty.setOperateUserName(user.getName());
                        }
                        if (produceDuty.getCreateUser() == user.getId()) {
                            produceDuty.setCreateUserName(user.getName());
                        }
                    }
                }
            }
            if (!CollectionUtils.isEmpty(productDevices)) {
                for (ProduceDuty produceDuty : list) {
                    for (DevicesItemVo vo : productDevices) {
                        if (produceDuty.getProductDeviceId() == Integer.parseInt(vo.getId())) {
                            produceDuty.setProductDeviceCode(vo.getName());
                        }
                    }
                }
            }
        }
            PageInfo<ProduceDuty> pageInfo = new PageInfo<>(list);
            return ResultUtils.success(pageInfo);
        }
    }
