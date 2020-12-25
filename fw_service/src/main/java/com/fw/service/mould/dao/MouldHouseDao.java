package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldHouse;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模具入库、出库、移库
 * @author lpsong
 * @since 2020-10-26
 */
@Repository
public interface MouldHouseDao {

    /**
     * 新增 入库、出库、移库
     * @param mouldHouse
     * @return
     */
    Integer insert(MouldHouse mouldHouse);

    /**
     * 修改 入库、出库、移库
     * @param mouldHouse
     * @return
     */
    Integer update(MouldHouse mouldHouse);


    /**
    * 更改模具状态
    * @return
    */
    Integer updateMouldStatus(MouldDevices mouldDevices);

    /**
     * 删除入库、出库、移库
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);
    /**
     * 根据id获取仓库信息
     *
     * @param id
     * @return
     */
    MouldHouse selectById(@Param("id") Integer id);


    /**
     * 根据id获取模具信息
     *
     * @param id
     * @return
     */
    MouldDevices selectMouldDevicesById(@Param("id") Integer id);

    /**
     * 入库、出库、移库记录查询
     * @param code
     * @return
     */
    List<MouldHouse> findList(@Param("code") String code,
                              @Param("mouldCode") String mouldCode,
                              @Param("operate") Integer operate,
                              @Param("startTime") String startTime,
                              @Param("endTime") String endTime,
                              @Param("status") Integer status,
                              @Param("rootPath") String rootPath);
    /**
     * 编码查询
     * @param
     * @return
     */
    String findCode(String  code);

    /**
     * 批量插入
     */
    Integer insertList(@Param("mouldHouses") List<MouldHouse> mouldHouses);
}
