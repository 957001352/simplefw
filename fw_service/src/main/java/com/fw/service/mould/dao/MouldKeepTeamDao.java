package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldKeepTeam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备保养项目组 Dao 接口
 *
 * @author xkliu
 * @since 2020-10-27
 */
@Repository
public interface MouldKeepTeamDao {

    /**
     * 新增
     *
     * @param mouldKeepTeam
     * @return
     */
    Integer insert(MouldKeepTeam mouldKeepTeam);

    /**
     * 根据id获取DevicesKeepTeam对象
     *
     * @param id
     * @return
     */
    MouldKeepTeam selectById(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param mouldKeepTeam
     * @return
     */
    Integer update(MouldKeepTeam mouldKeepTeam);

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
     * @param mouldCode
     * @return
     */
    List<MouldKeepTeam> findList(@Param("name") String name, @Param("mouldCode") String mouldCode);


    /**
     * 获取保养项目组
     *
     * @return
     */
    List<MouldKeepTeam> getAllKeepTeam();


    /**
     * 校验名字重复
     *
     * @param name
     * @return
     */
    boolean verifyName(String name);

    /**
     * 校验 产前 在线 产后 保养是否存在
     *
     * @param keepType
     * @return
     */
    boolean verifyKeepType(@Param("id") Integer id,@Param("keepType") Integer keepType);

    /**
     * 根据保养类型获取保养表单
     *
     * @param keepTpye
     * @return
     */
    MouldKeepTeam getKeepTeamByType(Integer keepTpye);
}
