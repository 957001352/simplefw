package com.fw.service.device.dao;

import com.fw.entity.device.DevicesKeepPlan;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备保养计划制定 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-19
 */
@Repository
public interface DevicesKeepPlanDao {

    /**
     * 新增
     *
     * @param devicesKeepPlan
     * @return
     */
    Integer insert(DevicesKeepPlan devicesKeepPlan);

    /**
     * 修改
     *
     * @param devicesKeepPlan
     * @return
     */
    Integer update(DevicesKeepPlan devicesKeepPlan);

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
    List<DevicesKeepPlan> findList(@Param("name") String name, @Param("keepTeamName") String keepTeamName, @Param("productDevicesIds") List<String> productDevicesIds);

    /**
     * 根据id获取DevicesKeepPlan
     *
     * @param id
     * @return
     */
    DevicesKeepPlan selectById(@Param("id") Integer id);

    /**
     * 获取所有的计划定制
     * @return
     */
    List<DevicesKeepPlan> findAll();

    /**
     * 获取所有下发失败的任务
     * @return
     */
    List<DevicesKeepPlan> findAllfail();

    /**
     * 校验名字重复
     *
     * @param name
     * @return
     */
    boolean verifyName(String name);
}
