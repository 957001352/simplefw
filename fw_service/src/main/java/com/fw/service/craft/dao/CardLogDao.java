package com.fw.service.craft.dao;

import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CraftCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工艺卡管理
 * @author gchen
 * @since 2020-11-6
 */
@Repository
public interface CardLogDao {

    /**
     * 新增
     * @param cardLog
     * @return
     */
    Integer insert(CardLog cardLog);
    /**
     * 修改
     * @param cardLog
     * @return
     */
    Integer update(CardLog cardLog);
    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Integer delete(@Param("ids") String[] ids);
    /**
     * 列表查询
     * @return
     */
    List<CardLog> findList(@Param("cardLogId")Integer cardLogId,
                           @Param("paramsId")Integer paramsId,
                           @Param("fromCode")String fromCode);

    /**
     * 根据id查变更记录
     * @return
     */
    CardLog findById(Integer id);
}
