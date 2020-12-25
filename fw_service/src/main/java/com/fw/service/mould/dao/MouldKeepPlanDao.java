package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldKeepPlan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模具保养计划制定 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-27
 */
@Repository
public interface MouldKeepPlanDao {

    /**
     * 新增
     *
     * @param mouldKeepPlan
     * @return
     */
    Integer insert(MouldKeepPlan mouldKeepPlan);

    /**
     * 修改
     *
     * @param mouldKeepPlan
     * @return
     */
    Integer update(MouldKeepPlan mouldKeepPlan);

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
     * @return
     */
    List<MouldKeepPlan> findList(@Param("name") String name, @Param("keepTeamName") String keepTeamName, @Param("mouldCode") String mouldCode);

    /**
     * 根据id获取DevicesKeepPlan
     *
     * @param id
     * @return
     */
    MouldKeepPlan selectById(@Param("id") Integer id);

    /**
     * 获取所有的计划定制
     *
     * @return
     */
    List<MouldKeepPlan> findAll();

    /**
     * 获取所有下发失败的任务
     *
     * @return
     */
    List<MouldKeepPlan> findAllfail();

    /**
     * 校验名字重复
     *
     * @param name
     * @return
     */
    boolean verifyName(String name);

    /**
     * 获取开合模次数保养
     *
     * @return
     */
    List<MouldKeepPlan> findNonOpening();
}
