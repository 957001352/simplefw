package com.fw.service.logistics.service.impl;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsProduct;
import com.fw.entity.logistics.LogisticsStorageDetail;
import com.fw.enums.ResultEnum;
import com.fw.service.logistics.dao.LogisticsProductDao;
import com.fw.service.logistics.dao.LogisticsStorageDetailDao;
import com.fw.service.logistics.service.LogisticsProductService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 库存管理 服务实现类
 *
 * @author xkliu
 * @date 2020/11/5
 */
@Service
public class LogisticsProductServiceImpl implements LogisticsProductService {

    @Autowired
    private LogisticsProductDao logisticsProductDao;

    @Autowired
    private LogisticsStorageDetailDao logisticsStorageDetailDao;

    @Override
    public Result exceedWarn(String name, String code, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsProduct> lists = logisticsProductDao.findExceedWarn(name, code);
        PageInfo<LogisticsProduct> productPage = new PageInfo<>(lists);
        return ResultUtils.success(productPage);
    }

    @Override
    public Result getProductDetail(Integer id) {
        if(CheckUtils.isNull(id)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        LogisticsStorageDetail logisticsStorageDetail = logisticsStorageDetailDao.getProductDetail(id);
        return ResultUtils.success(logisticsStorageDetail);
    }

    @Override
    public Result storeWarn(String name, String code, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsProduct> lists = logisticsProductDao.findStoreWarn(name, code);
        PageInfo<LogisticsProduct> productPage = new PageInfo<>(lists);
        return ResultUtils.success(productPage);
    }

    @Override
    public Result findList(String code, String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsProduct> lists = logisticsProductDao.findList(code, name);
        PageInfo<LogisticsProduct> productPage = new PageInfo<>(lists);
        return ResultUtils.success(productPage);
    }

    @Override
    @Transactional
    public Result setLogisticsExceed(String ids, Integer warnTime) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        for (String str : lists) {
            LogisticsProduct logisticsProduct = logisticsProductDao.getLogisticsProduct(Integer.valueOf(str));
            if (!CheckUtils.isNull(logisticsProduct)) {
                logisticsProduct.setWarnTime(warnTime);
                flag = logisticsProductDao.update(logisticsProduct);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result setStore(String ids, Integer maxStore, Integer minStore) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        for (String str : lists) {
            LogisticsProduct logisticsProduct = logisticsProductDao.getLogisticsProduct(Integer.valueOf(str));
            if (logisticsProduct != null) {
                logisticsProduct.setMaxStore(maxStore);
                logisticsProduct.setMinStore(minStore);
                flag = logisticsProductDao.update(logisticsProduct);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findDetailList(String code, String name, String storageCode, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LogisticsProduct> lists = logisticsProductDao.findDetailList(code,name,storageCode);
        PageInfo<LogisticsProduct> productPage = new PageInfo<>(lists);
        return ResultUtils.success(productPage);
    }

    @Override
    public Result getAllCode() {
        return ResultUtils.success(logisticsProductDao.getAllCode());
    }

    @Override
    public Result getNameByCode(String code) {
        if (CheckUtils.isNull(code)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        LogisticsProduct logisticsProduct = logisticsProductDao.getNameByCode(code);
        return ResultUtils.success(logisticsProduct);
    }

    @Override
    public Result findListByCode(String codes) {
        if (CheckUtils.isNull(codes)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return ResultUtils.success(logisticsProductDao.findListByCode(Arrays.asList(codes.split(","))));
    }
}
