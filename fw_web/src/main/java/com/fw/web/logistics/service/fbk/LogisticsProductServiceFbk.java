package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsProductService;
import org.springframework.stereotype.Service;

/**
 * 库存管理Feign接口调用失败
 *
 * @author xkliu
 * @date 2020/11/5
 */
@Service
public class LogisticsProductServiceFbk implements LogisticsProductService {

    @Override
    public Result setStore(String ids, Integer maxStore, Integer minStore) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result exceedWarn(String name, String code, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getProductDetail(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getUpProductDetail(Integer id) {
            return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDownProductDetail(Integer id, Integer storageLocationId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result storeWarn(String name, String code, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result setLogisticsExceed(String ids, Integer warnTime) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String code, String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findDetailList(String code, String name, String storageCode, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getAllCode() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getNameByCode(String code) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findListByCode(String codes) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
