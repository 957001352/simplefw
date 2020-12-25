package com.fw.service.device.dao;

import com.fw.entity.device.DevicesKeepItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 保养项目 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Repository
public interface DevicesKeepItemDao {

    /**
     * 新增
     *
     * @param devicesKeepItem
     * @return
     */
    Integer insert(DevicesKeepItem devicesKeepItem);

    /**
     * 修改
     *
     * @param devicesKeepItem
     * @return
     */
    Integer update(DevicesKeepItem devicesKeepItem);

    /**
     * 根据id获取DevicesKeepItem
     *
     * @param id
     * @return
     */
    DevicesKeepItem selectById(@Param("id") Integer id);

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
     * @param devicesClassify
     * @return
     */
    List<DevicesKeepItem> findList(@Param("name") String name, @Param("devicesClassify") String devicesClassify);

    /**
     * 获取所有保养项目
     *
     * @return
     */
    List<DevicesKeepItem> findAll();

    /**
     * 根据ids获取保养设别名字的集合
     *
     * @param ids
     * @return
     */
    List<String> findItemByIds(List<String> ids);

    /**
     * 根据ids获取DevicesKeepItem集合
     *
     * @param ids
     * @return
     */
    List<DevicesKeepItem> findKeepItemByIds(List<String> ids);

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
    List<DevicesKeepItem> getKeepItemByCycle(Integer cycle);
}
