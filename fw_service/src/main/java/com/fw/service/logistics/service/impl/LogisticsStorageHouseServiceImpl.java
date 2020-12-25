package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;
import com.fw.enums.ResultEnum;
import com.fw.service.logistics.dao.LogisticsStorageHouseDao;
import com.fw.service.logistics.service.LogisticsStorageHouseService;
import com.fw.service.mould.dao.MouldDevicesDao;
import com.fw.service.mould.dao.MouldUseRecordDao;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 物流仓库 服务实现
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
@Service
public class LogisticsStorageHouseServiceImpl implements LogisticsStorageHouseService {

    @Autowired
    private LogisticsStorageHouseDao logisticsStorageHouseDao;

    @Override
    public Result saveStorage(LogisticsStorageHouse logisticsStorageHouse) {
        Integer flag = 0;
        if (logisticsStorageHouseDao.isRepeatStorageName(logisticsStorageHouse) > 0) {
            return ResultUtils.error("仓库名称重复");
        }
        //id为空新增
        if (CheckUtils.isNull(logisticsStorageHouse.getId())) {
            flag = logisticsStorageHouseDao.insertStorage(logisticsStorageHouse);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            LogisticsStorageHouse entrty = logisticsStorageHouseDao.selectStorageById(logisticsStorageHouse.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = logisticsStorageHouseDao.updateStorage(logisticsStorageHouse);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    public Result deleteStorage(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        if (logisticsStorageHouseDao.findStorageIsEableDelete(list) > 0) {
            return ResultUtils.error("仓库不为空，不能删除");
        }
        flag = logisticsStorageHouseDao.deleteStorage(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findStorageList(String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsStorageHouse> lists = logisticsStorageHouseDao.findStorageList(name);
        return ResultUtils.success(new PageInfo<>(lists));
    }


    @Override
    public Result saveLocation(LogisticsStorageLocation logisticsStorageLocation) {
        Integer flag = 0;
        if (logisticsStorageHouseDao.isRepeatLocationName(logisticsStorageLocation) > 0) {
            return ResultUtils.error("库位名称重复");
        }
        //id为空新增
        if (CheckUtils.isNull(logisticsStorageLocation.getId())) {
            flag = logisticsStorageHouseDao.insertLocation(logisticsStorageLocation);
        } else {
            //修改时先查询数据是否存在,多个窗口操作问题
            LogisticsStorageLocation entrty = logisticsStorageHouseDao.selectLocationById(logisticsStorageLocation.getId());
            if (CheckUtils.isNull(entrty)) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = logisticsStorageHouseDao.updateLocation(logisticsStorageLocation);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result deleteLocation(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> list = Arrays.asList(ids.split(","));
        flag = logisticsStorageHouseDao.deleteLocation(list);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findLocationList(String name, String productCode, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsStorageLocation> lists = logisticsStorageHouseDao.findLocationList(name, productCode);
        return ResultUtils.success(new PageInfo<>(lists));
    }

    @Override
    public Result findStorageLocationList(Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsStorageLocation> lists = logisticsStorageHouseDao.findStorageLocationList();
        return ResultUtils.success(new PageInfo<>(lists));
    }
}
