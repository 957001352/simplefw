package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.ProductDevicesSpare;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description: 备品备件 service
 */
public interface ProductDevicesSpareService {

    /**
     * 新增
     *
     * @param productDevicesSpare
     * @return
     */
    Result save(ProductDevicesSpare productDevicesSpare);

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    Result getProductDevicesSpare(Integer id);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 列表查询
     * @param code 物料代码
     * @param name 物料名称
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String code,String name, Integer pageNum, Integer pageSize);


}
