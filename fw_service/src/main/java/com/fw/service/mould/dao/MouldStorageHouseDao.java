package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldStorageHouse;
import com.fw.entity.mould.MouldStorageLocation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模具库位管理
 * @author lpsong
 * @since 2020-10-26
 */
@Repository
public interface MouldStorageHouseDao {

    /**
     * 新增仓库
     * @param mouldStorageHouse
     * @return
     */
    Integer insertStorage(MouldStorageHouse mouldStorageHouse);

    /**
     * 修改仓库
     * @param mouldStorageHouse
     * @return
     */
    Integer updateStorage(MouldStorageHouse mouldStorageHouse);


    /**
     * 删除仓库
     * @param list
     * @return
     */
    Integer deleteStorage(List<String> list);
    /**
     * 根据id获取仓库信息
     *
     * @param id
     * @return
     */
    MouldStorageHouse selectStorageById(@Param("id") Integer id);
    /**
     * 仓库列表查询
     * @param name
     * @return
     */
    List<MouldStorageHouse> findStorageList(@Param("name") String name);


    /**
    * 判断名称是否重复
     * @param mouldStorageHouse
    * @return
    */
    Integer isRepeatStorageName(MouldStorageHouse mouldStorageHouse);

    /**
    * 查询仓库下是否有库位
     * @param list
    * @return
    */
    Integer findStorageIsEableDelete(List<String> list);

    /**
     * 新增库位
     * @param mouldStorageLocation
     * @return
     */
    Integer insertLocation(MouldStorageLocation mouldStorageLocation);

    /**
     * 修改库位
     * @param mouldStorageLocation
     * @return
     */
    Integer updateLocation(MouldStorageLocation mouldStorageLocation);


    /**
     * 删除库位
     * @param lists
     * @return
     */
    Integer deleteLocation(List<String> lists);
    /**
     * 根据id获取库位信息
     *
     * @param id
     * @return
     */
    MouldStorageLocation selectLocationById(@Param("id") Integer id);
    /**
     * 库位列表查询
     * @param storageHouseId 仓库
     * @param location 库位名称
     * @param mould  模具名称
     * @return
     */
    List<MouldStorageLocation> findLocationList(@Param("storageHouseId") Integer storageHouseId,
                                                @Param("location") String location,
                                                @Param("mould") String mould,
                                                @Param("mouldCode") String mouldCode,
                                                @Param("status") Integer status,
                                                @Param("isBand") Integer isBand);

    /**
    * 判断库位是否重复
     * @param mouldStorageLocation
    * @return
    */
    Integer isRepeatLocationName(MouldStorageLocation mouldStorageLocation);

    /**
    * 查询库位是否绑定了模具
     * @param list
    * @return
    */
    Integer findLocationIsEableDelete(List<String> list);


    /**
    * 空库位查询
     * @param name
    * @return
    */
    List<MouldStorageLocation> findEmptyLocationList( @Param("name") String name);
}
