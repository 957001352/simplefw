package com.fw.service.device.dao;


import com.fw.entity.device.ProductDevicesSpare;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description:  备品备件Dao层接口
 */
public interface ProductDevicesSpareDao {

    /**
     * 新增
     *
     * @param productDevicesSpare
     * @return
     */
    Integer insert(ProductDevicesSpare productDevicesSpare);

    /**
     * 修改
     *
     * @param productDevicesSpare
     * @return
     */
    Integer update(ProductDevicesSpare productDevicesSpare);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    ProductDevicesSpare selectById(@Param("id") Integer id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 列表查询
     *
     * @return
     */
    List<ProductDevicesSpare> findList(@Param("code") String code,@Param("name") String name);

    /**
     * 判断备件编码和名称是否已存在
     * 不能重复
     * @param code
     * @param name
     * @return
     */
    List<ProductDevicesSpare> codeOrName(@Param("code") String code,@Param("name") String name);

    List<ProductDevicesSpare> selectByNotInId(Integer id);
}
