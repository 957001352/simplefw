package com.fw.service.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.MouldScrap;

public interface MouldScrapService {

    /**
     * 新增/编辑
     * @param mouldScrap
     */
    Result save(MouldScrap mouldScrap);

    /**
     * 查询
     */
    Result findList();
}
