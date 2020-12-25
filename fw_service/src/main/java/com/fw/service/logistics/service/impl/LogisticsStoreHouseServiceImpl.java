package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsStoreHouse;
import com.fw.entity.logistics.LogisticsStoreHouseDetail;
import com.fw.entity.produce.ProduceMaterialMonitor;
import com.fw.entity.produce.ProduceMoldingMonitor;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsStoreHouseDao;
import com.fw.service.logistics.dao.LogisticsStoreHouseDetailDao;
import com.fw.service.logistics.service.LogisticsStoreHouseService;
import com.fw.service.logistics.util.StorageProductUtil;
import com.fw.service.produce.dao.ProduceMaterialMonitorDao;
import com.fw.service.produce.dao.ProduceMoldingMonitorDao;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.util.AuthUserUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LogisticsStoreHouseServiceImpl implements LogisticsStoreHouseService {
    @Resource
    private LogisticsStoreHouseDao logisticsStoreHouseDao;
    @Resource
    private LogisticsStoreHouseDetailDao logisticsStoreHouseDetailDao;
    @Resource
    private HeaderUtil headerUtil;
    @Resource
    private StorageProductUtil storageProductUtil;
    @Resource
    private E2CServicesUtil e2CServicesUtil;
    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private ProduceMaterialMonitorDao produceMaterialMonitorDao;
    @Autowired
    private ProduceMoldingMonitorDao produceMoldingMonitorDao;

    @Override
    public Result findList(String orderNo, String houseNo, String houseType, Integer status, String startTime, String endTime, String partsType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String[] houseTypes = null;
        if (houseType != null && !"".equals(houseType)) {
            houseTypes = houseType.split(",");
        }
        List<LogisticsStoreHouse> list = logisticsStoreHouseDao.findList(orderNo, houseNo, houseTypes, status, startTime, endTime, partsType);
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken());
        for (LogisticsStoreHouse logisticsStoreHouse : list) {
            if (!CheckUtils.isNull(logisticsStoreHouse.getExecuteUser())) {
                for (User user : userList) {
                    if (logisticsStoreHouse.getExecuteUser().equals(user.getId())) {
                        logisticsStoreHouse.setUser(user);
                    }
                }
            }
        }
        PageInfo<LogisticsStoreHouse> pageInfo = new PageInfo<>(list);
        return ResultUtils.success(pageInfo);
    }

    @Override
    public Result findByOrderNo(String storeHouseId) {
        if (CheckUtils.isNull(storeHouseId)) {
            return ResultUtils.error("参数错误");
        }
        return ResultUtils.success(logisticsStoreHouseDetailDao.findByOrderNo(storeHouseId));
    }

    @Override
    @Transactional
    public Result storeHouseIn(Integer storeHouseId) {
        if (CheckUtils.isNull(storeHouseId)) {
            return ResultUtils.error("参数错误");
        }
        Integer integer = logisticsStoreHouseDetailDao.storeHouseIn(storeHouseId, 3);
        if (integer > 0) {
            integer = inStorage(storeHouseId);
        }

        //入库完成修改注塑过程监控
        List<LogisticsStoreHouseDetail> details = logisticsStoreHouseDetailDao.findByStoreHouseId(storeHouseId);
        if (!CollectionUtils.isEmpty(details)) {
            //根据入库id将明细分组
            Map<Integer, List<LogisticsStoreHouseDetail>> map = details.stream().collect(Collectors.groupingBy(LogisticsStoreHouseDetail::getStoreHouseId));
            //循环map中的value
            map.values().forEach(value -> {
                //循环value中的LogisticsStoreHouseDetail对象
                value.forEach(item -> {
                    String productCode = item.getProductCode();
                    //根据生产指令获取ProduceMoldingMonitor对象
                    ProduceMoldingMonitor monitor = produceMoldingMonitorDao.getMoldingMonitor(productCode);
                    //monitor不为空,而且入库时间为空,才设置入库人员和入库时间
                    if (monitor != null && CheckUtils.isNull(monitor.getStoreTime())) {
                        //根据明细中的storeHouseId获取LogisticsStoreHouse对象
                        LogisticsStoreHouse storeHouse = logisticsStoreHouseDao.findByStoreHouseId(item.getStoreHouseId());
                        monitor.setStoreTime(storeHouse.getExecuteTime());
                        monitor.setStoreUser(e2CServicesUtil.findUserNameById(String.valueOf(storeHouse.getExecuteUser())));
                        produceMoldingMonitorDao.update(monitor);
                    }
                });
            });
        }
        return integer > 0 ? ResultUtils.success() : ResultUtils.failure();//入库到 3：暂存区
    }

    @Override
    @Transactional
    public Result batchBound(List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails) {
        if (logisticsStoreHouseDetails == null) {
            return ResultUtils.error("参数错误");
        }
        Integer update = logisticsStoreHouseDetailDao.batchBound(logisticsStoreHouseDetails);
        int size = logisticsStoreHouseDetails.size();
        if (update > 0 && size > 0) {
            Integer storeHouseId = logisticsStoreHouseDetails.get(0).getStoreHouseId();
            update = inStorage(storeHouseId);
            List<ProduceMaterialMonitor> produceMaterialMonitorList = new ArrayList<>();
            if (logisticsStoreHouseDetails != null && logisticsStoreHouseDetails.size() > 0) {
                for (LogisticsStoreHouseDetail logisticsStoreHouseDetail : logisticsStoreHouseDetails) {
                    ProduceMaterialMonitor produceMaterialMonitor = new ProduceMaterialMonitor();
                    //produceMaterialMonitor.setProductId(logisticsStoreHouseDetail.getProductId());
                    produceMaterialMonitor.setId(logisticsStoreHouseDetail.getOrderDetailId()); //收货明细ID，收货明细id=物料监控
                    produceMaterialMonitor.setStoreUser(headerUtil.loginUser().getName());
                    produceMaterialMonitor.setStoreTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                    produceMaterialMonitorList.add(produceMaterialMonitor);
                }
                //记录物料过程监控入库人、和入库时间
                produceMaterialMonitorDao.update(produceMaterialMonitorList);
            }
        }
        return update > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    private Integer inStorage(Integer storeHouseId) {
        Integer update = -1;
        LogisticsStoreHouse logisticsStoreHouse = logisticsStoreHouseDao.findByStoreHouseId(storeHouseId);
        String today = DateUtils.getToday("yyyy-MM-dd HH:mm:ss");
        logisticsStoreHouse.setExecuteTime(today);
        logisticsStoreHouse.setStoreDate(today);
        logisticsStoreHouse.setExecuteUser(headerUtil.loginUser().getId());
        logisticsStoreHouse.setStatus(1);
        storageProductUtil.inStorage(logisticsStoreHouse);//修改库存数量
        update = logisticsStoreHouseDao.update(logisticsStoreHouse);//修改入库单的状态
        return update;
    }

    @Override
    @Transactional
    public Result saveStoreHouse(LogisticsStoreHouse logisticsStoreHouse) {
        if (logisticsStoreHouse == null) {
            return ResultUtils.error("参数错误");
        }
        logisticsStoreHouse.setCreateTime(DateUtils.getTodayTime());
        Integer insert = logisticsStoreHouseDao.insertOne(logisticsStoreHouse);
        List<LogisticsStoreHouseDetail> logisticsStoreHouseDetailList = logisticsStoreHouse.getLogisticsStoreHouseDetailList();
        if (insert > 0 && logisticsStoreHouseDetailList != null && logisticsStoreHouseDetailList.size() > 0) {
            //给明细插入关联入库单id
            logisticsStoreHouseDetailList.forEach(logisticsStoreHouseDetail -> logisticsStoreHouseDetail.setStoreHouseId(logisticsStoreHouse.getId()));
            insert = logisticsStoreHouseDetailDao.insert(logisticsStoreHouseDetailList);
        }
        return insert > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result save(LogisticsStoreHouse logisticsStoreHouse) {
        //查询需要生成的订单号
        logisticsStoreHouse.setHouseNo(logisticsStoreHouseDao.findCode(CodeEnum.LOGISTICS_08.getCode()));
        logisticsStoreHouse.setCreateTime(DateUtils.getTodayTime());
        Integer insert = logisticsStoreHouseDao.insertOne(logisticsStoreHouse);
        List<LogisticsStoreHouseDetail> logisticsStoreHouseDetails = logisticsStoreHouse.getLogisticsStoreHouseDetailList();
        if (insert > 0 && logisticsStoreHouseDetails != null && logisticsStoreHouseDetails.size() > 0) {
            for (LogisticsStoreHouseDetail logisticsStoreHouseDetail : logisticsStoreHouseDetails) {
                logisticsStoreHouseDetail.setStoreHouseId(logisticsStoreHouse.getId());
            }
            insert = logisticsStoreHouseDetailDao.insert(logisticsStoreHouseDetails);
        }
        return insert >= 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result delete(LogisticsStoreHouse logisticsStoreHouse) {
        if (logisticsStoreHouse.getStatus() == 1) {
            return ResultUtils.error("已完成的生产任务不得删除");
        }
        Integer delete = logisticsStoreHouseDao.delete(logisticsStoreHouse.getId());
        if (delete > 0) {
            delete = logisticsStoreHouseDetailDao.delete(logisticsStoreHouse.getId());
        }
        return delete >= 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findByStoreHouseId(Integer storeHouseId) {
        return ResultUtils.success(logisticsStoreHouseDetailDao.findByStoreHouseId(storeHouseId));
    }
}
