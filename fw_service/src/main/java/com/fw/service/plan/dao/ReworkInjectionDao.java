package com.fw.service.plan.dao;

import com.fw.entity.plan.ReworkInjection;
import com.fw.entity.plan.ReworkStopList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 二次加工排产计划
 * @author lpsong
 * @since 2020-11-25
 */
@Repository
public interface ReworkInjectionDao {

    /**
     * 新增
     * @param reworkInjection
    * @return
     */
    Integer insert(ReworkInjection reworkInjection);

    /**
     * 修改
     * @param reworkInjection
     * @return
     */
    Integer update(ReworkInjection reworkInjection);

    /**
     * 根据id获取仓库信息
     *
     * @param id
     * @return
     */
    ReworkInjection selectById(@Param("id") Integer id);

    /**
    *  列表查询
     * @param productCode 生产指令
     * @param partsCode 零件编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param status  状态
    * @return
    */
    List<ReworkInjection> findList(@Param("productCode") String productCode,
                                   @Param("partsCode") String partsCode,
                                   @Param("startTime") String startTime,
                                   @Param("endTime") String endTime,
                                   @Param("status") String status);

    /**
     * 新增暂停
     * @param reworkStopList
     * @return
     */
    Integer insertStopList(ReworkStopList reworkStopList);

    /**
    * 查询暂停时间
     * @param injectionId
    * @return
    */
    Double findAllStopTime(Integer injectionId);

    /**
     * 查询二次加工最后一次生产指令
     */
    ReworkInjection findLastProductCode();

    /**
     * 修改生产指令的状态
     */
    Integer updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);

    /**
     * 查询上移影响的生产指令
     */
    List<ReworkInjection> findMoveUp(ReworkInjection reworkInjection);

    Integer updateListTime(@Param("reworkInjections") List<ReworkInjection> reworkInjections);

    /**
     * 查询下移影响的生产指令
     */
    List<ReworkInjection> findMoveDown(ReworkInjection reworkInjection);

    /**
     * 查询取消生产指令影响的生产指令
     */
    List<ReworkInjection> findLater(ReworkInjection reworkInjection);

}
