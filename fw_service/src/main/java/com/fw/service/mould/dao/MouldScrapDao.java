package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldScrap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    模具报废Dao层
* @Author:         gchen
* @CreateDate:     2020/10/26 10:37
*/
@Repository
public interface MouldScrapDao {


    /**
     * 新增
     * @param mouldScrap
     * @return
     */
    Integer insert(MouldScrap mouldScrap);

    /**
     * 更新
     * @param mouldScrap
     */
    Integer update(MouldScrap mouldScrap);

    /**
     * 查询
     */
    List<MouldScrap> findList();
}
