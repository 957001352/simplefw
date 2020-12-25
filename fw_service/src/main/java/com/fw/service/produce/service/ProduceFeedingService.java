package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceFeeding;

/**
 * @des: 投料 服务类
 * @author: xkliu
 * @date: 2020/12/14
 */
public interface ProduceFeedingService {


    /**
     * 新增
     *
     * @param produceFeeding
     * @return
     */
    Result save(ProduceFeeding produceFeeding);

    /**
     * 列表查询
     *
     * @param productOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String productOrder, Integer pageNum, Integer pageSize);

    /**
     * 扫码获取出库数量
     *
     * @param storageDetailId
     * @return
     */
    Result getStorageCount(Integer storageDetailId);
}
