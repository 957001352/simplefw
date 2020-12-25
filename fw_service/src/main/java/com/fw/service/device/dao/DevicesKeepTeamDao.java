package com.fw.service.device.dao;

import com.fw.entity.device.DevicesKeepTeam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 保养项目组 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Repository
public interface DevicesKeepTeamDao {

    /**
     * 新增
     *
     * @param devicesKeepTeam
     * @return
     */
    Integer insert(DevicesKeepTeam devicesKeepTeam);

    /**
     * 根据id获取DevicesKeepTeam对象
     *
     * @param id
     * @return
     */
    DevicesKeepTeam selectById(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param devicesKeepTeam
     * @return
     */
    Integer update(DevicesKeepTeam devicesKeepTeam);

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
     * @param productDevicesIds
     * @return
     */
    List<DevicesKeepTeam> findList(@Param("name") String name, @Param("devicesClassify") String devicesClassify, @Param("productDevicesIds") List<String> productDevicesIds);


    /**
     * 获取保养项目组
     *
     * @return
     */
    List<DevicesKeepTeam> getAllKeepTeam();

    /**
     * 校验名字重复
     *
     * @param name
     * @return
     */
    boolean verifyName(String name);
}
