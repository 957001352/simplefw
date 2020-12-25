package com.fw.service.craft.dao;

import com.fw.entity.craft.CardUpdateParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardUpdateParamsDao {
    /**
     * 新增
     */
    Integer insert(CardUpdateParam cardUpdateParam);

    /**
     * 查询
     */
    List<CardUpdateParam> findList(@Param("deviceCode")String deviceCode);
    /**
     * 查询最新一条
     */
    List<CardUpdateParam> findNewLog();
}
