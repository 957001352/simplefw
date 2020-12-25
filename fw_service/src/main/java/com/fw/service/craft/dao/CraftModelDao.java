package com.fw.service.craft.dao;

import com.fw.entity.craft.CraftModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-06 10:49
 **/

@Repository
public interface CraftModelDao {

    Integer insert(CraftModel craftModel);

    Integer update(CraftModel craftModel);

    List<CraftModel> findList(@Param(value = "productCode") String productCode, @Param(value = "productName") String productName, @Param(value = "modelType") Integer modelType);

    Integer delete(List<String> list);

    Integer findCaftModelById(@Param(value = "id") Integer id);

    CraftModel findByProductCode(@Param(value = "productCode") String productCode);

    CraftModel findByProductId(@Param(value = "productId") String productId);

    Integer excludeExist(@Param(value = "id") Integer id,@Param(value = "productCode") String productCode);

    CraftModel findProductCodeAndDevices(@Param(value = "productCode") String productCode,@Param(value = "devicesId")Integer devicesId);
}
