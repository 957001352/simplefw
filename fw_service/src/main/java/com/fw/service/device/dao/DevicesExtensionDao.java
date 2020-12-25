package com.fw.service.device.dao;

import com.fw.entity.device.DevicesExtension;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    设备履历Dao层
* @Author:         gchen
* @CreateDate:     2020/10/23 11:26
*/
@Repository
public interface DevicesExtensionDao {

    /**
     * 新增
     * @param devicesExtension
     */
    Integer insert(DevicesExtension devicesExtension);

    /**
     * 修改
     * @param devicesExtension
     */
    Integer update(DevicesExtension devicesExtension);


    /**
     * 删除
     *
     * @param list
     */
    Integer delete(List<String> list);

    /**
     * 列表查询
     * @param status startTime endTime
     */
    List<DevicesExtension> findList(@Param("status") Integer status,
                                    @Param("startTime") String startTime,
                                    @Param("endTime") String endTime,
                                    @Param("formCode") String formCode);
}
