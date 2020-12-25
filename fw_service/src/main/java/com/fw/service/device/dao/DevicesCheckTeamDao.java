package com.fw.service.device.dao;

import com.fw.entity.device.DevicesCheckDetail;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.entity.device.DevicesCheckTeam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@Repository
public interface DevicesCheckTeamDao {

    /**
     * 新增
     *
     * @param devicesCheckTeam
     * @return
     */
    Integer insert(DevicesCheckTeam devicesCheckTeam);

    /**
     * 根据id获取DevicesCheckTeam对象
     *
     * @param id
     * @return
     */
    DevicesCheckTeam selectById(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param devicesCheckTeam
     * @return
     */
    Integer update(DevicesCheckTeam devicesCheckTeam);

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
    List<DevicesCheckTeam> findList(@Param("name") String name,
                                    @Param("devicesClassify") String devicesClassify,
                                    @Param("productDevicesIds") List<String> productDevicesIds,
                                    @Param("checkItem")  String checkItem);


    /**
     * 判断名称是否重复
     * @param devicesCheckTeam
     * @return
     */
    Integer isRepeatName(DevicesCheckTeam devicesCheckTeam);
    /**
     * 判断设备是否重复
     * @param id
     * @return
     */
    Integer isRepeatProduct(@Param("id") Integer id,@Param("productDevicesIds") List<String> productDevicesIds);
    /**
    *  根据设备Id查询保养项目
     * @param
    * @return
    */
    List<DevicesCheckDetail> findCheckItemListByDevicve(@Param("devicesId") Integer devicesId);
}
