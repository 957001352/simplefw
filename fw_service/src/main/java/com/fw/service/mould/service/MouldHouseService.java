package com.fw.service.mould.service;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldHouse;

/**
 * 模具入库、出库、移库
 * @author lpsong
 * @since 2020-10-26
 */
public interface MouldHouseService {

    /**
     * 新增
     *
     * @param mouldHouse
     * @return
     */
    Result save(MouldHouse mouldHouse);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 列表查询
     * @param code
     * @param operate
     * @param startTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String code,
                    String mouldCode,
                    Integer operate,
                    String startTime,
                    String endTime,
                    Integer status,
                    Integer pageNum,
                    Integer pageSize);


}
