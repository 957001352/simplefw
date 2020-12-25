package com.fw.service.produce.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.enums.ResultEnum;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.produce.service.ProduceMaterialMonitorService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
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
 * @create: 2020-12-17 10:02
 **/

@Service
public class ProduceMaterialMonitorServiceImpl implements ProduceMaterialMonitorService {

    @Autowired
    private ProduceMaterialMonitorDao produceMaterialMonitorDao;
    @Autowired
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private HeaderUtil headerUtil;

    @Override
    public Result findList(String productCode, String productName, String startTime, String stopTime, Integer timeType, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<ProduceMaterialMonitor> list = produceMaterialMonitorDao.findList(productCode, productName, startTime, stopTime, timeType);
        return ResultUtils.success(new PageInfo<>(list));
    }
}
