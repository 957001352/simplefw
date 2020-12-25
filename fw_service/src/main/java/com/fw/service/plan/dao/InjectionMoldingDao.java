package com.fw.service.plan.dao;

import com.fw.entity.plan.InjectionMolding;
import com.fw.entity.plan.InjectionStopList;
import com.fw.entity.plan.PlanList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 注塑排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@Repository
public interface InjectionMoldingDao {

    /**
     * 新增
     * @param injectionMolding
     * @return
     */
    Integer insert(InjectionMolding injectionMolding);

    /**
     * 修改
     * @param injectionMolding
     * @return
     */
    Integer update(InjectionMolding injectionMolding);


    /**
     *取消
     * @param id
     * @return
     */
    Integer cancel(Integer id);
    /**
     *暂停
     * @param id
     * @return
     */
    Integer stop(Integer id);
    /**
     * 根据id获取仓库信息
     *
     * @param id
     * @return
     */
    InjectionMolding selectById(@Param("id") Integer id);

    /**
    *  列表查询
     * @param productCode 生产指令
     * @param partsCode 零件编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param status  状态
    * @return
    */
    List<InjectionMolding> findList(@Param("productDevicesId") Integer productDevicesId,
                                    @Param("productCode") String productCode,
                                    @Param("partsCode") String partsCode,
                                    @Param("startTime") String startTime,
                                    @Param("endTime") String endTime,
                                    @Param("status") String status);

    /**
     * 新增暂停
     * @param lnjectionStopList
     * @return
     */
    Integer insertStopList(InjectionStopList lnjectionStopList);

    /**
    * 查询暂停时间
     * @param injectionId
    * @return 
    */
    Double findAllStopTime(Integer injectionId);

    /**
     * 根据模具id获取模具最后一次生产指令信息
     * @return
     */
    InjectionMolding findByMouldId(@Param("mouldId") Integer mouldId);

    /**
     * 根据设备id获取设备最后一次生产指令信息
     * @return
     */
    InjectionMolding findByDeviceId(@Param("deviceId") Integer deviceId);

    /**
     * 根据id查询操作的两个生产指令
     */
    List<InjectionMolding> findByTime(@Param("startTime")String startTime,
                                      @Param("endTime")String endTime,
                                      @Param("productDeviceId")Integer productDeviceId);

    /**
     * 批量修改生产指令的开始时间
     */
    Integer updateList(@Param("injm")List<InjectionMolding> injm);

    /**
     * 修改生产指令状态
     */
    Integer updateStatus(InjectionMolding upPlan);

    /**
     * 根据id查询生产指令
     */
    InjectionMolding findById(Integer id);


    /**
     * 根据设备id查询生产中的生产指令
     * @return
     */
    InjectionMolding findIngByDeviceId(@Param("deviceId") Integer deviceId);

    /**
     * 根据设备id查询最新一个生产指令
     * @return
     */
    InjectionMolding findLatestByDeviceId(@Param("deviceId") Integer deviceId);
    /**
     * 根据生产指令查询模具数据
     * @return
     */
    InjectionMolding findMouldDevices(@Param("productCode") String productCode);

    /**
     * 查询未来24小时内要执行的生产指令
     * @return
     */
    List<InjectionMolding> findStartOneDay();
}
