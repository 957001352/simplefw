package com.fw.service.logistics.service;

import com.fw.domain.Result;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存管理 服务类
 *
 * @author xkliu
 * @date 2020/11/5
 */
public interface LogisticsProductService {

    /**
     * 设置安全库存
     *
     * @param ids
     * @return
     */
    Result setStore(String ids, Integer maxStore, Integer minStore);

    /**
     * 物料超期设置
     *
     * @param ids
     * @param warnTime
     * @return
     */
    Result setLogisticsExceed(String ids, Integer warnTime);

    /**
     * 列表查询
     *
     * @param code
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String code, String name, Integer pageNum, Integer pageSize);

    /**
     * 超期预警
     *
     * @param name
     * @param code
     * @return
     */
    Result exceedWarn(String name, String code, Integer pageNum, Integer pageSize);

    /**
     * 库存预警
     *
     * @param name
     * @param code
     * @return
     */
    Result storeWarn(String name, String code, Integer pageNum, Integer pageSize);

    /**
     * 扫码获取物料详情
     *
     * @param id
     * @return
     */
    Result getProductDetail(Integer id);
    /**
     * 扫码获取上架物料详情
     *
     * @param id
     * @return
     */
    Result  getUpProductDetail(Integer id);


    /**
     * 扫码获取下架物料详情
     *
     * @param id
     * @return
     */
    Result getDownProductDetail(Integer id,Integer storageLocationId);
    /**
     * 物料明细列表
     *
     * @param code
     * @param name
     * @param storageCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findDetailList(String code, String name, String storageCode, Integer pageNum, Integer pageSize);

    /**
     * 获取所有的物料code
     *
     * @return
     */
    Result getAllCode();

    /**
     * 根据code获取物料名称
     *
     * @param code
     * @return
     */
    Result getNameByCode(String code);

    /**
     * 根据code获取物料信息
     * @param codes
     * @return
     */
    Result findListByCode(String codes);

}
