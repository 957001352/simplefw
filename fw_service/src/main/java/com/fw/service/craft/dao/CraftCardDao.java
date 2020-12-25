package com.fw.service.craft.dao;

import com.fw.entity.craft.CraftCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工艺卡管理
 *
 * @author gchen
 * @since 2020-11-6
 */
@Repository
public interface CraftCardDao {

    /**
     * 新增
     *
     * @param craftCard
     * @return
     */
    Integer insert(CraftCard craftCard);

    /**
     * 修改
     *
     * @param craftCard
     * @return
     */
    Integer update(CraftCard craftCard);

    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);

    /**
     * 列表查询
     *
     * @return
     */
    List<CraftCard> findList();

    /**
     * 根据产品code,设备id 查询CraftCard
     *
     * @return
     */
    CraftCard getCraftCard(@Param("partCode") String partCode, @Param("mouldId") Integer devicesId);
}
