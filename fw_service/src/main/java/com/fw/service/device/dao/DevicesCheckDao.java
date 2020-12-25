package com.fw.service.device.dao;

import com.fw.entity.device.DevicesCheck;
import com.fw.entity.device.DevicesCheckDetail;
import com.fw.service.enums.CodeEnum;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备点检
 * @author lpsong
 * @since 2020-10-21
 */
@Repository
public interface DevicesCheckDao {

    /**
     * 新增
     *
     * @param devicesCheck
     * @return
     */
    Integer insert(DevicesCheck devicesCheck);

    /**
     * 修改
     *
     * @param devicesCheck
     * @return
     */
    Integer update(DevicesCheck devicesCheck);


    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);
    /**
     * 根据id获取DevicesCheck
     *
     * @param id
     * @return
     */
    DevicesCheck selectById(@Param("id") Integer id);
    /**
     * 列表查询
     * @param code
     * @param devicesClassify
     * @return
     */
    List<DevicesCheck> findList(@Param("code") String code,
                                @Param("devicesClassify") String devicesClassify,
                                @Param("productDevicesIds") List<String> productDevicesIds,
                                @Param("status") Integer status);

    /**
    * 查询当前登录用户是否有未完成点检任务
     * @param executeUser
     * @param productDevicesId
     * @param shift
    * @return
    */
   Integer findIsExistCheckTask(@Param("executeUser") Integer executeUser,
                                @Param("productDevicesId") Integer productDevicesId,
                                @Param("shift") Integer shift);

    /**
     * 查询需要点检任务
     * @param executeUser
     * @param productDevicesId
     * @return
     */
    List<DevicesCheck> findCheckTask(@Param("executeUser") Integer executeUser,
                                     @Param("productDevicesId") Integer productDevicesId,
                                     @Param("shift") Integer shift);
    /**
     * 新增点检项目
     *
     * @param devicesCheckDetail
     * @return
     */
    Integer insertCheckDetail(DevicesCheckDetail devicesCheckDetail);

    /**
     * 修改点检结果
     * @param devicesCheckDetail
     * @return
     */
    Integer updateCheckDetail(DevicesCheckDetail devicesCheckDetail);


    /**
     * 删除点检结果
     * @param list
     * @return
     */
    Integer deleteCheckDetail(List<String> list);


    /**
    * 查询点检项目
     * @param checkId
    * @return
    */
    List<DevicesCheckDetail> findCheckDetailList(@Param("checkId") Integer checkId);

    /**
    * 编码查询
     * @param
    * @return
    */
    String findCode(String  code);

}
