package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldTurn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    模具转段Dao层
* @Author:         gchen
* @CreateDate:     2020/10/26 10:37
*/
@Repository
public interface MouldTurnDao {


    /**
     * 新增
     * @param mouldTurn
     * @return
     */
    Integer insert(MouldTurn mouldTurn);

    /**
     * 更新
     * @param mouldTurn
     */
    Integer update(MouldTurn mouldTurn);

    /**
     * 查询
     */
    List<MouldTurn> findList();

    /**
     * 查询转段履历
     */
    List<MouldTurn> findByMouldDeviceId(@Param("formCode")String formCode,
                                        @Param("mouldDeviceId")Integer mouldDeviceId);
    /**
     * 根据id查询转段详情
     */
    MouldTurn findOneById(@Param("id")Integer id);
}
