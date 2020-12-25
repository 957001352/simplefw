package com.fw.service.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.MouldStop;

public interface MouldStopService {

    /**
     * 新增/编辑
     * @param mouldStop
     */
    Result save(MouldStop mouldStop);

    /**
     * 查询
     */
    Result findList();
}
