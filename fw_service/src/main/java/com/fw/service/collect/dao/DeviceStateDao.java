package com.fw.service.collect.dao;

import com.fw.entity.collect.DeviceState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gchen
 * @since 2020-12-16
 */
@Repository
public interface DeviceStateDao {

    /**
     * 根据设备code获取设备状态
     */
    DeviceState findDevice(@Param("deviceCode") String deviceCode);

}
