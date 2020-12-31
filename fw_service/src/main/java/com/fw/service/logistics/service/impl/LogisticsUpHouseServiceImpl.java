package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.*;
import com.fw.enums.ResultEnum;
import com.fw.service.logistics.dao.*;
import com.fw.service.logistics.service.LogisticsUpHouseService;
import com.fw.service.util.E2CServicesUtil;
import com.fw.service.util.HeaderUtil;
import com.fw.utils.CheckUtils;
import com.fw.utils.DateUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 上架服务实现类
 *
 * @author xkliu
 * @date 2020/11/10
 */
@Service
public class LogisticsUpHouseServiceImpl implements LogisticsUpHouseService {

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private LogisticsUpHouseDao logisticsUpHouseDao;

    @Autowired
    private LogisticsUpHouseDetailDao logisticsUpHouseDetailDao;

    @Autowired
    private LogisticsDownHouseDao logisticsDownHouseDao;

    @Autowired
    private LogisticsDownHouseDetailDao logisticsDownHouseDetailDao;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private LogisticsStorageDetailDao logisticsStorageDetailDao;

    @Override
    @Transactional
    public Result downHouse(LogisticsDownHouse logisticsDownHouse) {
        Integer flag = 0;
        if (logisticsDownHouse == null) {
            return ResultUtils.failure();
        }
        User user = headerUtil.loginUser();
        logisticsDownHouse.setCreateUser(user.getId());
        logisticsDownHouse.setCreateTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        flag = logisticsDownHouseDao.insert(logisticsDownHouse);
        if (flag > 0) {
            List<LogisticsDownHouseDetail> logisticsDownHouseDetails = logisticsDownHouse.getLogisticsDownHouseDetails();
            if (!CollectionUtils.isEmpty(logisticsDownHouseDetails)) {
                logisticsDownHouseDetails.forEach(item ->{
                    item.setDownHouseId(logisticsDownHouse.getId());
                    item.setStorageLocationId(logisticsDownHouse.getStorageLocationId());
                });
                flag = logisticsDownHouseDetailDao.batchInsert(logisticsDownHouseDetails);
                logisticsStorageDetailDao.updateDownLocation(logisticsDownHouseDetails);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result upHouse(LogisticsUpHouse logisticsUpHouse) {
        Integer flag = 0;
        if (logisticsUpHouse == null) {
            return ResultUtils.failure();
        }
        User user = headerUtil.loginUser();
        logisticsUpHouse.setCreateUser(user.getId());
        logisticsUpHouse.setCreateTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        flag = logisticsUpHouseDao.insert(logisticsUpHouse);
        if (flag > 0) {
            List<LogisticsUpHouseDetail> logisticsUpHouseDetails = logisticsUpHouse.getLogisticsUpHouseDetails();
            List<LogisticsStorageDetail> insertStorageDetails = new ArrayList<>();
            List<LogisticsUpHouseDetail> updateStorageDetails = new ArrayList<>();
            if (!CollectionUtils.isEmpty(logisticsUpHouseDetails)) {
                logisticsUpHouseDetails.forEach(item -> {
                    item.setUpHouseId(logisticsUpHouse.getId());
                    item.setStorageLocationId(logisticsUpHouse.getStorageLocationId());
                    //查询库中待上架物料数量
                    LogisticsStorageDetail entity= logisticsStorageDetailDao.selectById(item.getStorageDetailId());
                    //如果待上架物料数小于库中物料数，说明上架拆包了，则新插入一条库存明细
                    if(item.getStorageCount()<entity.getStorageCount()){
                        //更新存储中待上架物料数量，并保持库位为暂存区
                        int storageCount=entity.getStorageCount();
                        entity.setStorageCount(item.getStorageCount());
                        entity.setStorageId(item.getStorageLocationId());
                        item.setStorageCount(storageCount-item.getStorageCount());
                        item.setStorageLocationId(0);
                        insertStorageDetails.add(entity);
                    }else{
                        updateStorageDetails.add(item);
                    }
                });
                //插入拆包上架物料
                if (!CollectionUtils.isEmpty(insertStorageDetails)) {
                    logisticsStorageDetailDao.inStorage(insertStorageDetails);
                    insertStorageDetails.forEach(item -> {
                        LogisticsUpHouseDetail detail=new LogisticsUpHouseDetail();
                        detail.setStorageCount(item.getStorageCount());
                        detail.setStorageLocationId(item.getStorageId());
                        detail.setStorageDetailId(item.getId());
                        detail.setBatch(item.getProviderBatch());
                        detail.setUpHouseId(logisticsUpHouse.getId());
                        updateStorageDetails.add(detail);
                    });
                }
                logisticsStorageDetailDao.updateUpLocation(logisticsUpHouseDetails);
                flag = logisticsUpHouseDetailDao.batchInsert(updateStorageDetails);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findUpList(String code,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsUpHouse> lists = logisticsUpHouseDao.findUpList(code);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //操作人名字
                    if (!CheckUtils.isNull(item.getCreateUser())) {
                        if (item.getCreateUser().equals(vo.getId())) {
                            item.setCreateUserName(vo.getName());
                        }
                    }
                });
            });
        }
        PageInfo<LogisticsUpHouse> upHouse = new PageInfo<>(lists);
        return ResultUtils.success(upHouse);
    }

    @Override
    public Result findDownList( String code,Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsDownHouse> lists = logisticsDownHouseDao.findDownList(code);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //操作人名字
                    if (!CheckUtils.isNull(item.getCreateUser())) {
                        if (item.getCreateUser().equals(vo.getId())) {
                            item.setCreateUserName(vo.getName());
                        }
                    }
                });
            });
        }
        PageInfo<LogisticsDownHouse> downHouse = new PageInfo<>(lists);
        return ResultUtils.success(downHouse);
    }

    @Override
    public Result getUpHouse(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        LogisticsUpHouse logisticsUpHouse = logisticsUpHouseDao.getUpHouse(id);
        return ResultUtils.success(logisticsUpHouse);
    }

    @Override
    public Result getDownHouse(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        LogisticsDownHouse logisticsDownHouse = logisticsDownHouseDao.getDownHouse(id);
        return ResultUtils.success(logisticsDownHouse);
    }

    @Override
    public Result findAllList(String code, String storageName, String startCreateTime,String endCreateTime, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsUpHouseDetail> lists = logisticsUpHouseDetailDao.findAllList(code, storageName, startCreateTime,endCreateTime);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //操作人名字
                    if (!CheckUtils.isNull(item.getCreateUser())) {
                        if (item.getCreateUser().equals(vo.getId())) {
                            item.setCreateUserName(vo.getName());
                        }
                    }
                });
            });
        }
        PageInfo<LogisticsUpHouseDetail> detail = new PageInfo<>(lists);
        return ResultUtils.success(detail);
    }
}
