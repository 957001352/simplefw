package com.fw.service.craft.dao;

import com.fw.entity.craft.CardParams;
import com.fw.entity.craft.CollectDevice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实时采集数据
 * @author gchen
 * @since 2020-12-14
 */
@Repository
public interface CollectDeviceDao {


    /**
     * 列表查询
     * @param
     * @return
     */
    List<CollectDevice> findByProductCode(@Param("productCode") String productCode);

    List<CollectDevice> findByParamKey(@Param("paramKey")String paramKey);
}
