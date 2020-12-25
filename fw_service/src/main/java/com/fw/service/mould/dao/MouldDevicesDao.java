package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldDevices;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    模具Dao层
* @Author:         gchen
* @CreateDate:     2020/10/26 10:37
*/
@Repository
public interface MouldDevicesDao {


    /**
     * 新增
     * @param mouldDevices
     * @return
     */
    Integer insert(MouldDevices mouldDevices);

    /**
     * 更新
     * @param mouldDevices
     */
    Integer update(MouldDevices mouldDevices);

    /**
     * 查询
     * @param code  模具编码
     * @param mouldName   模具名称
     * @param turnStatus  阶段状态
     */
    List<MouldDevices> findList(@Param("code") String code,
                                @Param("mouldName") String mouldName,
                                @Param("turnStatus") Integer turnStatus,
                                @Param("status") Integer status,
                                @Param("rootPath") String rootPath,
                                @Param("formCode") String formCode);

    /**
     * 检查模具编号是否重复
     */
    Integer mouldCodeIsRepeat(MouldDevices mouldDevices);


    /**
     * 查询所有没有绑定库位的模具
     */
    List<MouldDevices> findMouldNoBound();


    /**
     * 获取所有可用的模具
     */
    List<MouldDevices> getMouldDevices();

    /**
     * 批量删除
     */
    Integer delete(@Param("ids") String[] ids);


    /**
     * 根据id查询模具
     */
    MouldDevices findMouldDevicesById(@Param("id") Integer id);
}
