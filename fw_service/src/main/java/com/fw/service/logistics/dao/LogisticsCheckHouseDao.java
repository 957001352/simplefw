package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsCheckHouse;
import com.fw.entity.logistics.LogisticsCheckHouseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 盘库
 * @author lpsong
 * @since 2020-11-12
 */
@Repository
public interface LogisticsCheckHouseDao {

    /**
     * 新增 
     * @param logisticsCheckHouse
     * @return
     */
    Integer insert(LogisticsCheckHouse logisticsCheckHouse);

    /**
     * 修改
     * @param logisticsCheckHouse
     * @return
     */
    Integer update(LogisticsCheckHouse logisticsCheckHouse);

    /**
     * 关闭任务
     * @param id
     * @param status
     * @return
     */
    Integer updateStatus(@Param("id") Integer id,@Param("status") Integer status);

    /**
     * 删除
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
    LogisticsCheckHouse selectById(@Param("id") Integer id);




    /**
    *  列表查询
     * @param houseNo
     * @param checkTime
     * @param status
     * @param checkUser
    * @return
    */
    List<LogisticsCheckHouse> findList(@Param("houseNo") String houseNo,
                                       @Param("checkTime") String checkTime,
                                       @Param("status") Integer status,
                                       @Param("checkResult") Integer checkResult,
                                       @Param("checkUser") Integer checkUser);
    /**
     * 编码查询
     * @param
     * @return
     */
    String findCode(String  code);
    /**
     * 新增明细
     * @param logisticsCheckHouseDetails
     * @return
     */
    Integer insertDetail(@Param("list") List<LogisticsCheckHouseDetail> logisticsCheckHouseDetails,
                         @Param("checkHouseId") Integer checkHouseId);

    Integer deleteDetail(@Param("checkHouseId") Integer checkHouseId);
    /**
    * 查询明细
     * @param checkHouseId
    * @return
    */
    List<LogisticsCheckHouseDetail> findDetailList(@Param("checkHouseId") Integer checkHouseId);

    /**
    * 根据库位查询物料库存列表
     * @param locationId
    * @return
    */
    List<LogisticsCheckHouseDetail> findStoragePorductList(@Param("locationId") Integer locationId);
}
