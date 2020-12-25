package com.fw.service.device.dao;

import com.fw.entity.device.DevicesScrap;
import com.fw.entity.jbpm.JbpmExecution;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    设备报废记录Dao层
* @Author:         gchen
* @CreateDate:     2020/10/23 11:26
*/
@Repository
public interface DevicesScrapDao {

    /**
     * 新增
     * @param devicesScrap
     */
    Integer insert(DevicesScrap devicesScrap);

    /**
     * 修改
     * @param devicesScrap
     */
    Integer update(DevicesScrap devicesScrap);


    /**
     * 删除
     *
     * @param list
     */
    Integer delete(List<String> list);

    /**
     * 列表查询
     */
    List<DevicesScrap> findList(@Param("formCode") String formCode,
                                @Param("devicesScrapId") Integer devicesScrapId,
                                @Param("ids") String ids);

    /**
     * 根据Id查询报废详情
     */
    DevicesScrap findById(@Param("devicesScrapId") Integer devicesScrapId);

    /**
     * 根据履历id查询报废详情
     */
    List<DevicesScrap> findByDeviceExtensionId(@Param("deviceExtensionId") Integer deviceExtensionId,
                                                @Param("formCode") String formCode);
}
