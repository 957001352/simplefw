package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetail;
import com.fw.entity.device.DevicesOutinDetailDTO;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.entity.e2c.User;
import com.fw.enums.ResultEnum;
import com.fw.service.device.dao.DevicesOutInDetailDao;
import com.fw.service.device.service.DevicesOutInDetailService;
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

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/22
 * Time:11:31
 * @Description:
 */
@Service
public class DevicesOutInDetailServiceImpl implements DevicesOutInDetailService {

    @Autowired
    private DevicesOutInDetailDao devicesOutInDetailDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;


    @Override
    public Result selectById(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        DevicesOutinDetail devicesOutinDetail = devicesOutInDetailDao.selectById(id);
        if (!CheckUtils.isNull(devicesOutinDetail)) {
            devicesOutinDetail = devicesOutInDetailDao.selectById(id);
        } else {
            ResultUtils.error("结果不存在");
        }
        return ResultUtils.success(devicesOutinDetail);
    }

    /**
     * @param outOrInNo
     * @param operate
     * @param startTime
     * @param stopTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result findAll(String outOrInNo, String operate, String startTime, String stopTime, Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);


        List<LinkedHashMap<String, Object>> linkedHashMaps = devicesOutInDetailDao.findAll(outOrInNo, operate, startTime, stopTime);

        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        if (!CollectionUtils.isEmpty(linkedHashMaps)) {
            for (int i = 0; i < linkedHashMaps.size(); i++) {
                for (User user : userList) {
                    if (user.getId() == Integer.parseInt(linkedHashMaps.get(i).get("createUser").toString())) {
                        linkedHashMaps.get(i).put("createUserName", user.getName());
                    }
                }
            }
        }

        PageInfo<LinkedHashMap<String, Object>> pageInfo = new PageInfo<>(linkedHashMaps);

        return ResultUtils.success(pageInfo);
    }


}
