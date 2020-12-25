package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.produce.ProduceBake;
import com.fw.entity.produce.ProduceDuty;
import com.fw.enums.ResultEnum;
import com.fw.service.produce.dao.ProduceBakeDao;
import com.fw.service.produce.service.ProduceBakeService;
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

import java.util.LinkedHashMap;
import java.util.List;


@Service
public class ProduceBakeServiceImpl implements ProduceBakeService {

    @Autowired
    private ProduceBakeDao produceBakeDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Override
    public Result save(ProduceBake produceBake) {
        if (produceBake == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        produceBake.setCreateUser(authUserUtil.userId());
        return produceBakeDao.insert(produceBake) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LinkedHashMap<String, Object>> list = produceBakeDao.findList();
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(list)) {
            for (LinkedHashMap<String, Object> map : list) {
                //比对用户名称
                if (!CollectionUtils.isEmpty(userList)) {
                    for (User user : userList) {
                        if (Integer.parseInt(map.get("createUser").toString()) == user.getId()) {
                            map.put("createUserName", user.getName());
                        }
                    }

                }
                //比对设备名称
                if (!CollectionUtils.isEmpty(productList)) {
                    for (DevicesItemVo devicesItemVo : productList) {
                        if (devicesItemVo.getId().equals(map.get("deviceId").toString())) {
                            map.put("deviceCode", devicesItemVo.getName());
                        }
                    }
                }
            }
        }

        PageInfo<LinkedHashMap<String, Object>> pageInfo = new PageInfo<>(list);
        return ResultUtils.success(pageInfo);
    }
}
