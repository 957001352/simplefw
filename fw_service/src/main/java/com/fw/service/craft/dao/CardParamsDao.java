package com.fw.service.craft.dao;

import com.fw.entity.craft.CardParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 工艺卡参数
 * @author gchen
 * @since 2020-11-6
 */
@Repository
public interface CardParamsDao {

    /**
     * 新增
     * @param cardParams
     * @return
     */
    Integer insert(CardParams cardParams);

    /**
     * 修改
     * @param cardParams
     * @return
     */
    Integer update(CardParams cardParams);
    /**
     * 删除
     * @param ids
     * @return
     */
    Integer delete(@Param("ids") String[] ids);
    /**
     * 列表查询
     * @param
     * @return
     */
    List<CardParams> findList(@Param("partCode")String partCode,
                              @Param("partName")String partName,
                              @Param("productId")Integer productId,
                              @Param("formCode")String formCode);

    /**
     * 根据id查询
     */
    CardParams findById(@Param("paramsId")Integer paramsId);

    /**
     * 判断该零件和设备的工艺卡是否已存在
     */
    Integer isExist(CardParams cardParams);

    /**
     * 根据零件编号查询相关的工艺卡
     */
    List<CardParams> findDeviceByPartCode(String partCode);

    /**
     * 根据设备和物料查询工艺卡
     */
    CardParams findByDeviceProduct(@Param("productId")Integer productId,
                                   @Param("partCode")String partCode);
}
