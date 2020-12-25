package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldScrap;
import com.fw.entity.mould.MouldStop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    模具停用Dao层
* @Author:         gchen
* @CreateDate:     2020/10/26 10:37
*/
@Repository
public interface MouldStopDao {


    /**
     * 新增
     * @param mouldStop
     * @return
     */
    Integer insert(MouldStop mouldStop);

    /**
     * 更新
     * @param mouldStop
     */
    Integer update(MouldStop mouldStop);

    /**
     * 查询
     */
    List<MouldStop> findList();
}
