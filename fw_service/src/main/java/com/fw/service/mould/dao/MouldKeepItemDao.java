package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldKeepItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模具保养项目 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-27
 */
@Repository
public interface MouldKeepItemDao {

    /**
     * 新增
     *
     * @param mouldKeepItem
     * @return
     */
    Integer insert(MouldKeepItem mouldKeepItem);

    /**
     * 修改
     *
     * @param mouldKeepItem
     * @return
     */
    Integer update(MouldKeepItem mouldKeepItem);

    /**
     * 根据id获取MouldKeepItem
     *
     * @param id
     * @return
     */
    MouldKeepItem selectById(@Param("id") Integer id);

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
     * @param name
     * @return
     */
    List<MouldKeepItem> findList(@Param("name") String name);

    /**
     * 获取所有保养项目
     *
     * @return
     */
    List<MouldKeepItem> findAll();

    /**
     * 根据ids获取保养设别名字的集合
     *
     * @param ids
     * @return
     */
    List<String> findItemByIds(List<String> ids);

    /**
     * 根据ids获取MouldKeepItem集合
     *
     * @param ids
     * @return
     */
    List<MouldKeepItem> findKeepItemByIds(List<String> ids);


    /**
     * 校验名字重复
     *
     * @param name
     * @return
     */
    boolean verifyName(String name);

    /**
     * 根据保养周期获取保养项目
     *
     * @param cycle
     * @return
     */
    List<MouldKeepItem> getKeepItemByCycle(Integer cycle);
}
