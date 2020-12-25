package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 物流库位 Dao 接口
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
@Repository
public interface LogisticsStorageHouseDao {

    /**
     * 新增仓库
     *
     * @param logisticsStorageHouse
     * @return
     */
    Integer insertStorage(LogisticsStorageHouse logisticsStorageHouse);

    /**
     * 修改仓库
     *
     * @param logisticsStorageHouse
     * @return
     */
    Integer updateStorage(LogisticsStorageHouse logisticsStorageHouse);


    /**
     * 删除仓库
     *
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
    LogisticsStorageHouse selectStorageById(@Param("id") Integer id);

    /**
     * 仓库列表查询
     *
     * @param name
     * @return
     */
    List<LogisticsStorageHouse> findStorageList(@Param("name") String name);


    /**
     * 判断名称是否重复
     *
     * @param logisticsStorageHouse
     * @return
     */
    Integer isRepeatStorageName(LogisticsStorageHouse logisticsStorageHouse);

    /**
     * 查询仓库下是否有库位
     *
     * @param list
     * @return
     */
    Integer findStorageIsEableDelete(List<String> list);

    /**
     * 新增库位
     *
     * @param logisticsStorageLocation
     * @return
     */
    Integer insertLocation(LogisticsStorageLocation logisticsStorageLocation);

    /**
     * 修改库位
     *
     * @param logisticsStorageLocation
     * @return
     */
    Integer updateLocation(LogisticsStorageLocation logisticsStorageLocation);


    /**
     * 删除库位
     *
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
    LogisticsStorageLocation selectLocationById(@Param("id") Integer id);

    /**
     * 库位列表查询
     *
     * @param name
     * @param productCode
     * @return
     */
    List<LogisticsStorageLocation> findLocationList(@Param("name") String name,
                                                    @Param("productCode") String productCode);

    /**
     * 判断库位是否重复
     *
     * @param logisticsStorageLocation
     * @return
     */
    Integer isRepeatLocationName(LogisticsStorageLocation logisticsStorageLocation);

    /**
     * 查询库位
     * @return
     */
    List<LogisticsStorageLocation> findStorageLocationList();

    /**
     * 查询库位
     * @return
     */
    List<LogisticsStorageLocation> findLocationListByHouseId(@Param("houseId") Integer houseId,@Param("name") String name);
}
