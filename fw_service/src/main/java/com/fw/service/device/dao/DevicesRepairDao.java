package com.fw.service.device.dao;

import com.fw.entity.device.DevicesRepair;
import com.fw.entity.device.DevicesRepairSpare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-21 16:14
 **/
@Repository
public interface DevicesRepairDao {


    /**
     * 新增
     *
     * @param devicesRepair
     * @return
     */
    Integer insert(DevicesRepair devicesRepair);

    /**
     * 删除
     *
     * @param list
     * @return
     */
    Integer delete(List<String> list);

    /**
     * 查询
     *
     * @param code              维修单编码
     * @param productDevicesIds 设备编码
     * @param devicesClassify   设备类型
     * @return
     */
    List<DevicesRepair> findList(Integer id, String code, String devicesClassify,
                                 @Param("productDevicesIds") List<String> productDevicesIds,
                                 @Param(value = "rootPath") String rootPath,
                                 @Param(value = "priority") String priority,
                                 @Param(value = "repairStatus") Integer repairStatus,
                                 @Param(value = "repairExeStatus")Integer repairExeStatus
    );

    /**
     * 更新
     *
     * @param devicesRepair
     * @return
     */
    Integer update(DevicesRepair devicesRepair);

    /**
     * 根据ID查询维修单信息
     *
     * @param id
     * @return
     */
    DevicesRepair findDevicesPairInfoById(Integer id);

    List<DevicesRepairSpare> findRepairItemById(Integer devicesRepairId);

    /**
     * 查询距当前时间最近的未完成维修单
     */
    DevicesRepair findByNearNow(@Param("productDeviceId")Integer productDeviceId,
                                @Param("nowTime")String nowTime);

    String findCode(@Param(value = "code") String code);
}
