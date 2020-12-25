package com.fw.service.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.MouldTurn;

public interface MouldTurnService {

    /**
     * 新增/编辑
     * @param mouldTurn
     */
    Result save(MouldTurn mouldTurn);

    /**
     * 查询
     */
    Result findList();

    /**
     * 转段履历
     */
    Result findTurn(Integer mouldDeviceId);

    /**
     * 根据id查询转段详情
     */
    Result findOneById(Integer id);

    /**
     * 转段申请通过
     */
    Result mouldTurnPass(Integer id);
}
