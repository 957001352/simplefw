package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepPlan;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.entity.e2c.User;
import com.fw.entity.logistics.LogisticsBackHouse;
import com.fw.entity.logistics.LogisticsBackHouseDetail;
import com.fw.enums.ResultEnum;
import com.fw.service.enums.CodeEnum;
import com.fw.service.logistics.dao.LogisticsBackHouseDao;
import com.fw.service.logistics.dao.LogisticsBackHouseDetailDao;
import com.fw.service.logistics.service.LogisticsBackHouseService;
import com.fw.service.logistics.util.StorageProductUtil;
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

import java.util.Arrays;
import java.util.List;

/**
 * 退库管理 服务实现类
 *
 * @author xkliu
 * @date 2020/11/12
 */
@Service
public class LogisticsBackHouseServiceImpl implements LogisticsBackHouseService {

    @Autowired
    private LogisticsBackHouseDao logisticsBackHouseDao;

    @Autowired
    private LogisticsBackHouseDetailDao logisticsBackHouseDetailDao;

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private E2CServicesUtil e2CServicesUtil;

    @Autowired
    private StorageProductUtil storageProductUtil;

    @Override
    public Result findList(String houseNo, Integer status,String productOrder, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        List<DevicesItemVo> productList = e2CServicesUtil.getProductDevices(headerUtil.cloudToken());
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsBackHouse> lists = logisticsBackHouseDao.findList(status, houseNo,productOrder);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //执行人名字
                    if (!CheckUtils.isNull(item.getExecuteUser())) {
                        if (item.getExecuteUser().equals(vo.getId())) {
                            item.setExecuteUserName(vo.getName());
                        }
                    }
                });
                //生产设备
                if (!CheckUtils.isNull(item.getProductDevicesId()) && !CollectionUtils.isEmpty(productList)) {
                    productList.forEach(vo -> {
                        if (String.valueOf(item.getProductDevicesId()).equals(vo.getId())) {
                            item.setProductDevicesName(vo.getName());
                        }
                    });
                }
            });
        }
        PageInfo<LogisticsBackHouse> productPage = new PageInfo<>(lists);
        return ResultUtils.success(productPage);
    }


    @Override
    public Result getBackHouseDetail(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        LogisticsBackHouse logisticsBackHouses = logisticsBackHouseDao.getBackHouse(id);
        return ResultUtils.success(logisticsBackHouses);
    }

    @Override
    @Transactional
    public Result cancellingStocks(LogisticsBackHouse logisticsBackHouse) {
        Integer flag = 0;
        if (logisticsBackHouse == null) {
            return ResultUtils.error("退库失败");
        }
        logisticsBackHouse.setExecuteTime(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        flag = logisticsBackHouseDao.updateStatus(logisticsBackHouse);
        if (flag > 0 && !CollectionUtils.isEmpty(logisticsBackHouse.getLogisticsBackHouseDetails())) {
            flag = logisticsBackHouseDetailDao.cancellingStocks(logisticsBackHouse.getLogisticsBackHouseDetails());
        }
        if (flag > 0) {
            storageProductUtil.backStorage(logisticsBackHouse);//修改库存数量
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findHistoryList(String houseNo, String code, String productCode, String startExecuteTime, String endExecuteTime, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<User> userList = e2CServicesUtil.getUserList(headerUtil.cloudToken(), null);
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsBackHouse> lists = logisticsBackHouseDao.findHistoryList(houseNo, code, productCode, startExecuteTime, endExecuteTime);
        if (!CollectionUtils.isEmpty(lists)) {
            lists.forEach(item -> {
                userList.forEach(vo -> {
                    //执行人名字
                    if (!CheckUtils.isNull(item.getExecuteUser())) {
                        if (item.getExecuteUser().equals(vo.getId())) {
                            item.setExecuteUserName(vo.getName());
                        }
                    }
                });
            });
        }
        PageInfo<LogisticsBackHouse> productPage = new PageInfo<>(lists);
        return ResultUtils.success(productPage);
    }

    @Override
    public Result backHouseDetailPage(Integer id, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsBackHouseDetail> lists = logisticsBackHouseDetailDao.getBackHouseDetail(id);
        PageInfo<LogisticsBackHouseDetail> detailPage = new PageInfo<>(lists);
        return ResultUtils.success(detailPage);
    }

    @Override
    @Transactional
    public Result save(LogisticsBackHouse logisticsBackHouse) {
        Integer flag = 0;
        //新增
        if (CheckUtils.isNull(logisticsBackHouse.getId())) {
            logisticsBackHouse.setStatus(0);
            logisticsBackHouse.setHouseNo(logisticsBackHouseDao.findCode(CodeEnum.LOGISTICS_07.getCode()));
            flag = logisticsBackHouseDao.insert(logisticsBackHouse);
            if (flag > 0 && !CollectionUtils.isEmpty(logisticsBackHouse.getLogisticsBackHouseDetails())) {
                List<LogisticsBackHouseDetail> details = logisticsBackHouse.getLogisticsBackHouseDetails();
                if (!CollectionUtils.isEmpty(details)) {
                    details.forEach(item -> item.setBackHouseId(logisticsBackHouse.getId()));
                }
                flag = logisticsBackHouseDetailDao.batchInsert(logisticsBackHouse.getLogisticsBackHouseDetails());
            }
        } else {//修改
            LogisticsBackHouse house = logisticsBackHouseDao.selectById(logisticsBackHouse.getId());
            if (house == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = logisticsBackHouseDao.update(logisticsBackHouse);
            if (flag > 0) {
                logisticsBackHouseDetailDao.delteByHouseId(logisticsBackHouse.getId());
                List<LogisticsBackHouseDetail> details = logisticsBackHouse.getLogisticsBackHouseDetails();
                if (!CollectionUtils.isEmpty(details)) {
                    details.forEach(item -> item.setBackHouseId(logisticsBackHouse.getId()));
                }
                flag = logisticsBackHouseDetailDao.batchInsert(logisticsBackHouse.getLogisticsBackHouseDetails());
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        for (String str : lists) {
            LogisticsBackHouse logisticsBackHouse = logisticsBackHouseDao.selectById(Integer.valueOf(str));
            if (logisticsBackHouse == null) {
                return ResultUtils.error("删除失败,请刷新页面");
            }
            //删除明细数据
            logisticsBackHouseDetailDao.delteByHouseId(Integer.valueOf(str));
        }
        //删除退库信息
        flag = logisticsBackHouseDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
