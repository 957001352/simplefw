package com.fw.service.mould.dao;


import com.fw.entity.mould.MouldRepairItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;

@Repository
public interface MouldRepairItemDao {

    /**
     * 新增
     *
     * @param mouldRepairItem
     * @return
     */
    Integer insert(MouldRepairItem mouldRepairItem);

    /**
     * 更新
     */
    Integer update(MouldRepairItem mouldRepairItem);

    /**
     * 获取列表
     */

    List<MouldRepairItem> findList(String name);

    /**
     * 删除
     */
    Integer delete(List<String> list);

    MouldRepairItem findItemById(@Param("id") Integer id);
    Integer findItemByName(@Param("name") String name);

    Integer findItemByIdAndName(@Param("id") Integer id,@Param("name") String name);
}
