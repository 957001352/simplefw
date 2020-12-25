package com.fw.service.device.dao;

import com.fw.entity.device.DevicesRepairItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description: dao
 * @author: wqiang
 * @create: 2020-10-20 10:57
 **/
@Repository
public interface DevicesRepairItemDao {


    /**
     * 新增
     *
     * @param devicesRepairItem
     * @return
     */
    Integer insert(DevicesRepairItem devicesRepairItem);

    /**
     * 更新
     */
    Integer update(DevicesRepairItem devicesRepairItem);

    /**
     * 获取列表
     */

    List<DevicesRepairItem> findList(String name, String devicesClassify);

    /**
     *根据ids获取项目集合
     * @param list
     * @return
     */
    List<DevicesRepairItem> findListById(List<String> list);
    /**
     * 删除
     */
    Integer delete(List<String> list);

    DevicesRepairItem findItemById(@Param(value = "id") Integer id,@Param(value = "name") String name);

    Integer findItemByIdAndName(@Param(value = "id") Integer id,@Param(value = "name") String name);
}
