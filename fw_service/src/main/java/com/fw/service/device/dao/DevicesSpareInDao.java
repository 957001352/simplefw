package com.fw.service.device.dao;

import com.fw.entity.device.DevicesSpareIn;

import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/27
 * Time:8:53
 * @Description: 备品备件入库
 */
public interface DevicesSpareInDao {

    /**
     * 新增入库
     * @param devicesSpareIn
     * @return
     */
    Integer insert(DevicesSpareIn devicesSpareIn);

    /**
     * 根据id查询入库信息
     * @param id
     * @return
     */
    DevicesSpareIn selectById(Integer id);

    /**
     * 列表 查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DevicesSpareIn> findAll(Integer pageNum, Integer pageSize);

    String findCode(String in_no);
}
