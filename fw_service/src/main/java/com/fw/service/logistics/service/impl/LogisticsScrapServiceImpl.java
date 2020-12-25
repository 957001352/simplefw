package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.DevicesClassify;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsScrap;
import com.fw.enums.ResultEnum;
import com.fw.service.logistics.dao.LogisticsScrapDao;
import com.fw.service.logistics.service.LogisticsScrapService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: 报废管理业务逻辑层
 * @author: wqiang
 * @create: 2020-12-16 10:09
 **/
@Service
public class LogisticsScrapServiceImpl implements LogisticsScrapService {

    @Autowired
    private LogisticsScrapDao logisticsScrapDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private HeaderUtil headerUtil;


    @Transactional
    @Override
    public Result save(LogisticsScrap logisticsScrap) {

        int flag = 0;
        if(logisticsScrap == null){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        //新增
        if(CheckUtils.isNull(logisticsScrap.getId())){
            logisticsScrap.setCreateUser(authUserUtil.userId());
            flag = logisticsScrapDao.insert(logisticsScrap);
        }else{
            Integer count = logisticsScrapDao.findById(logisticsScrap.getId());
            if(count <= 0){
                return ResultUtils.error("更新失败，请刷新页面！");
            }
            flag = logisticsScrapDao.update(logisticsScrap);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        if(CheckUtils.isNull(ids)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return logisticsScrapDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String productCode, String productName, String productOrder,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsScrap> list = logisticsScrapDao.findList(productCode, productName, productOrder);
        List<DevicesItemVo> productDevicesList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if(!CollectionUtils.isEmpty(list)){
            for(LogisticsScrap logisticsScrap : list){
                //获取设备编码
                if(!CollectionUtils.isEmpty(productDevicesList)){
                    for(DevicesItemVo devicesItemVo:productDevicesList){
                        if(Integer.parseInt(devicesItemVo.getId()) == logisticsScrap.getProductDevicesId()){
                            logisticsScrap.setProductDevicesCode(devicesItemVo.getName());
                        }
                    }
                }
                //获取用户
                if(!CollectionUtils.isEmpty(userList)){
                    for(User user:userList){
                        if(user.getId() == logisticsScrap.getCreateUser()){
                            logisticsScrap.setCreateUserName(user.getName());
                        }
                    }
                }
            }
        }

        return ResultUtils.success(new PageInfo<>(list));
    }
}
