package com.fw.service.device.dao;


import com.fw.entity.device.DevicesRepair;
import com.fw.entity.device.DevicesRepairSpare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface DevicesRepairSpareDao {

    /**
     * 新增
     *
     * @param devicesRepairSpare
     * @return
     */
    Integer insert(DevicesRepairSpare devicesRepairSpare);


    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertAll(List<DevicesRepairSpare> list);

    /**
     * 更新
     */
    Integer update(DevicesRepairSpare devicesRepairSpare);


    /**
     * 根据设备维修ID和设备维修备品备件是否存在
     *
     * @param devicesRepairId
     * @param devicesSpareId
     * @return
     */
    DevicesRepairSpare findReparSpare(Integer devicesRepairId, Integer devicesSpareId);


    List<DevicesRepairSpare> findReparSpareList(Integer devicesRepairId);

    /**
     * 删除维修单下所有的备品备件
     * @param devicesRepairIdList
     * @return
     */
    Integer delete(List<String> devicesRepairIdList);

    /**
     * 删除维修单下的备品备件
     * @param devicesRepairId
     * @return
     */
    Integer deleteByDevicesId(@Param(value = "devicesRepairId") Integer devicesRepairId);

    List<DevicesRepair> findSpareStoreAndUse(@Param(value = "code") String code);
}
