package com.fw.service.mould.dao;

import com.fw.entity.device.DevicesRepair;
import com.fw.entity.mould.MouldRepair;
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
public interface MouldRepairDao {


    /**
     * 新增
     * @param MouldRepair
     * @return
     */
    Integer insert(MouldRepair MouldRepair);

    /**
     * 删除
     * @param list
     * @return
     */
    Integer delete(List<String> list);

    /**
     * 查询
     * @param code  模具单编码
     * @param mouldId  模具编码
     * @param mouldName   模具名称
     * @return
     */
    List<MouldRepair> findList(@Param(value = "id") Integer id,
                               @Param(value = "code") String code,
                               @Param(value = "mouldId") String mouldId,
                               @Param(value = "mouldName") String mouldName,
                               @Param(value = "rootPath") String rootPath,
                               @Param(value = "priority") String priority,
                               @Param(value = "repairStatus") Integer repairStatus,
                               @Param(value = "repairExeStatus")Integer repairExeStatus
    );

    /**
     * 更新
     * @param MouldRepair
     * @return
     */
    Integer update(MouldRepair MouldRepair);

    /**
     * 根据ID查询维修单信息
     * @param id
     * @return
     */
    MouldRepair findMouldRePairInfoById(@Param(value = "id") Integer id);

    /**
     * 查询距当前时间最近的未完成维修单
     */
    MouldRepair findByNearNow(@Param("mouldId")Integer mouldId,
                              @Param("nowTime")String nowTime);

    String findCode(@Param(value = "code") String code);

    int updateMouldCavity(@Param("mouldId") String mouldId,@Param("cavity") Integer cavity);
}
