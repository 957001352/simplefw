package com.fw.service.collect.dao;

import com.fw.entity.collect.Device;
import org.springframework.stereotype.Repository;

/**
 * @author gchen
 * @since 2020-12-16
 */
@Repository
public interface DeviceDao {

    /**
     * 根据设备code获取Device
     *
     * @param deviceCode
     * @return
     */
    Device findDevice(String deviceCode);

}
