package com.fw.service.device.dao;

import com.fw.entity.device.DevicesCheckItem;
import com.fw.entity.device.DevicesCheckItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@Repository
public interface DevicesCheckItemDao {

    /**
     * 新增
     *
     * @param devicesCheckItem
     * @return
     */
    Integer insert(DevicesCheckItem devicesCheckItem);

    /**
     * 修改
     *
     * @param devicesCheckItem
     * @return
     */
    Integer update(DevicesCheckItem devicesCheckItem);


    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);
    /**
     * 根据id获取DevicesCheckItem
     *
     * @param id
     * @return
     */
    DevicesCheckItem selectById(@Param("id") Integer id);
    /**
     * 列表查询
     * @param name
     * @param devicesClassify
     * @return
     */
    List<DevicesCheckItem> findList(@Param("name") String name,
                                    @Param("devicesClassify") String devicesClassify,
                                    @Param("content") String content);

   /**
   * 判断名称是否重复
    * @param devicesCheckItem
   * @return
   */
   Integer isRepeatName(DevicesCheckItem devicesCheckItem);
}
