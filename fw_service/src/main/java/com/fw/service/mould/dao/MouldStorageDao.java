package com.fw.service.mould.dao;

import com.fw.entity.mould.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description:模具备品备件出入库管理
 * @Author: fchai
 * @Date: 2020/10/26 15:27
 */
@Repository
public interface MouldStorageDao {

    /**
     *新建模具备品备件基础信息
     * @param mouldStorageInfo
     * @return
     */
    Integer insertSpareInfo(MouldStorageInfo mouldStorageInfo);

    /**
     * 更新模具备品备件基础信息
     * @param mouldStorageInfo
     * @return
     */
    Integer updateSpareInfo(MouldStorageInfo mouldStorageInfo);

    /**
     * 删除模具备品备件基础信息
     * @param list
     * @return
     */
    Integer deleteSpareInfo(List<String> list);


    List<MouldStorageInfo> findListSpareInfo(@Param("code") String code,@Param("name") String name);


    /**
     * 新建入库单
     * @param mouldSpareIn
     * @return
     */
    Integer insertIn(MouldSpareIn mouldSpareIn);


    /**
     * 新建出库单
     * @param mouldSpareOut
     * @return
     */
    Integer insertOut(MouldSpareOut mouldSpareOut);

    /**
     * 新建出入库明细
     * @param List
     * @return
     */
    Integer insertInOrOutDetail(List<MouldStorageOutInDetail> List);


    /**
     * 添加库存数量
     * @param inCount
     * @param deviceSpareId
     * @return
     */
    Integer updateIn(@Param(value = "inCount") Integer inCount,@Param(value = "deviceSpareId") Integer deviceSpareId);


    /**
     * 减少库存数量
     * @param outCount
     * @param deviceSpareId
     * @return
     */
    Integer updateOut(@Param(value = "outCount") Integer outCount,@Param(value = "deviceSpareId") Integer deviceSpareId);


    /**
     * 查询备品备件库存数量
     * @param deviceSpareId
     * @return
     */
    Integer selectMouldStoreCount(@Param(value = "deviceSpareId") Integer deviceSpareId);

    /**
     * 查询出入库信息
     * @param outInNo
     * @param operate
     * @param startDate
     * @param endDate
     * @return
     */
    List<LinkedHashMap<String,Object>> selectInOutInfo(@Param(value = "outInNo") String outInNo,
                                                       @Param(value = "operate") Integer operate,
                                                       @Param(value = "startDate") String startDate,
                                                       @Param(value = "endDate") String endDate);


    /**
     * 查询模具备品备件库存信息
     * @param name
     * @return
     */
    List<MouldStorageInfo> findMouldSpareStoreList(@Param(value = "name") String name);


    MouldStorageInfo findStorageById(@Param("id") Integer id);

    List<MouldStorageInfo> findStorageByNotInId(@Param("id") Integer id,@Param(value = "name") String name ,@Param(value = "code") String code);

    List<MouldRepair> findMouldSpareStoreAndUse(@Param("code") String code);


    MouldSpareOut selectOutDetailById(@Param("id") Integer id);

    MouldSpareIn selectInDetailById (@Param("id") Integer id);

    Integer selectSpareByCodeAndName(@Param(value = "code") String code,@Param(value = "name") String name);

    Integer selectSpareById(@Param(value = "id") Integer id);

    //获取入库单号
    String findInCode(@Param(value = "code") String code);

    //获取出库单号
    String findOutCode(@Param(value = "code") String code);




}
