package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.produce.ProduceDuty;
import com.fw.entity.produce.ProduceShift;
import com.fw.enums.ResultEnum;
import com.fw.service.produce.dao.ProduceShiftDao;
import com.fw.service.produce.service.ProduceDutyService;
import com.fw.service.produce.service.ProduceShiftService;
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

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 16:51
 **/


@Service
public class ProduceShiftServiceImpl implements ProduceShiftService {

    @Autowired
    private ProduceShiftDao produceShiftDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;


    @Override
    public Result save(ProduceShift produceShift) {
        if (produceShift == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        produceShift.setCreateUser(authUserUtil.userId());
        return produceShiftDao.insert(produceShift) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer planMoldingId,Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProduceShift> list = produceShiftDao.findList(planMoldingId);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(list)) {
            if (!CollectionUtils.isEmpty(userList)) {
                for (ProduceShift produceShift : list) {
                    for(User user :userList){
                        if(produceShift.getOperateUser() == user.getId()){
                            produceShift.setOperateUserName(user.getName());
                        }
                        if(produceShift.getCreateUser() == user.getId()){
                            produceShift.setCreateUserName(user.getName());
                        }
                    }
                }
            }
        }

        PageInfo<ProduceShift> pageInfo = new PageInfo<>(list);
        return ResultUtils.success(pageInfo);
    }
}
