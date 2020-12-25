package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsOutHouse;
import com.fw.entity.logistics.LogisticsPicking;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.enums.ResultEnum;
import com.fw.service.logistics.dao.LogisticsOutHouseDao;
import com.fw.service.logistics.dao.LogisticsPickingDao;
import com.fw.service.logistics.service.LogisticsPickingService;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 领料
 * @author lpsong
 * @since 2020-12-15
 */
@Service
public class LogisticsPickingServiceImpl implements LogisticsPickingService {

    @Autowired
    private LogisticsPickingDao logisticsPickingDao;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private LogisticsOutHouseDao logisticsOutHouseDao;
    @Autowired
    private ProduceMaterialMonitorDao produceMaterialMonitorDao;
    @Override
    @Transactional
    public Result save(LogisticsPicking logisticsPicking) {
        int flag = 0;
        if (logisticsPicking == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        logisticsPicking.setCreateUser(authUserUtil.userId());
        flag=logisticsPickingDao.insert(logisticsPicking);
        if(flag>0){
            //更新出库单状态为已领料
            LogisticsOutHouse logisticsOutHouse=new LogisticsOutHouse();
            logisticsOutHouse.setId(logisticsPicking.getOutHouseId());
            logisticsOutHouse.setStatus(2);//已领料
            flag=logisticsOutHouseDao.update(logisticsOutHouse);
        }
        return flag> 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer outHouseId, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<LogisticsPicking> listPage = new PageInfo<>(logisticsPickingDao.findList(outHouseId));
        return ResultUtils.success(listPage);
    }
}
