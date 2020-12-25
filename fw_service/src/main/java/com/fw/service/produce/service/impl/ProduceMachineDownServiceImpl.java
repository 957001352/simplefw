package com.fw.service.produce.service.impl;


import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.produce.ProduceMachineDown;
import com.fw.entity.produce.ProduceMoldingRecord;
import com.fw.enums.ResultEnum;
import com.fw.service.RedisService;
import com.fw.service.produce.dao.ProduceMachineDownDao;
import com.fw.service.produce.service.ProduceMachineDownService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.FwConst;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 生产停机记录 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
@Service
public class ProduceMachineDownServiceImpl implements ProduceMachineDownService {

    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private ProduceMachineDownDao produceMachineDownDao;
    @Autowired
    private RedisService redisService;

    @Override
    public Result save(ProduceMachineDown produceMachineDown) {
        int flag = 0;
        if(produceMachineDown == null ){
         return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        produceMachineDown.setCreateUser(authUserUtil.userId());
        flag = produceMachineDownDao.insert(produceMachineDown);
        if(flag > 0 ){
            //记录设备已停机
            redisService.hset(FwConst.DEVICE_STATE_LOG_KEY,produceMachineDown.getRealProductDevicesCode(),"1");
        }

        return produceMachineDownDao.insert(produceMachineDown) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String ofNo, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProduceMachineDown> list = produceMachineDownDao.findList(ofNo);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        //List<DevicesItemVo> productDevices = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(list)) {
            //获取用户名称
            if (!CollectionUtils.isEmpty(userList)) {
                for (ProduceMachineDown produceMachineDown : list) {
                    for (User user : userList) {
                        if (produceMachineDown.getCreateUser() == user.getId()) {
                            produceMachineDown.setCreateUserName(user.getName());
                        }
                    }
                }
            }
            //获取设备编码
          /*  if (!CollectionUtils.isEmpty(productDevices)) {
                for (ProduceMachineDown produceMachineDown : list) {
                    for (DevicesItemVo vo : productDevices) {
                        if (produceMachineDown.getProductDevicesId() == Integer.parseInt(vo.getId())) {
                            produceMachineDown.setProductDevicesCode(vo.getName());
                        }
                    }
                }
            }*/

        }

        PageInfo<ProduceMachineDown> listPage = new PageInfo<>(list);
        return ResultUtils.success(listPage);
    }
}
