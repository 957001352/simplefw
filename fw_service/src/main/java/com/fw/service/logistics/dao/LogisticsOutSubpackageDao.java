package com.fw.service.logistics.dao;

import com.fw.entity.logistics.LogisticsOutHouse;
import com.fw.entity.logistics.LogisticsOutSubpackage;
import com.fw.entity.logistics.LogisticsStorageDetail;
import com.fw.entity.logistics.LogisticsStoreHouse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 出库拆包明细Dao接口
 * @author gchen
 * @since 2020-11-17
 */
@Repository
public interface LogisticsOutSubpackageDao {
    /**
     * 新增
     * @param logisticsOutSubpackages
     */
    Integer insert(@Param("logisticsOutSubpackages") List<LogisticsOutSubpackage> logisticsOutSubpackages);
    /**
     * 根据出库对象id查询拆包明细
     */
    List<LogisticsOutSubpackage> findOutSubpackage(String outHouseId);
}
