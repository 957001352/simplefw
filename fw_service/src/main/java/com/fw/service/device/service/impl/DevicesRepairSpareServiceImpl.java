package com.fw.service.device.service.impl;

import com.fw.domain.Result;
import com.fw.service.device.dao.DevicesRepairSpareDao;
import com.fw.service.device.service.DevicesRepairSpareService;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description: 设备维修备品备件业务逻辑层
 * @author: wqiang
 * @create: 2020-10-27 10:04
 **/
@Service
public class DevicesRepairSpareServiceImpl implements DevicesRepairSpareService {

    @Autowired
    private DevicesRepairSpareDao devicesRepairSpareDao;
    @Override
    public Result findSpareStoreAndUse(String code) {
        return ResultUtils.success(devicesRepairSpareDao.findSpareStoreAndUse(code));
    }
}
