package com.fw.service.device.dao;

import com.fw.entity.device.DevicesSpareOut;

import java.util.List;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description: 备品备件出库
 */
public interface DevicesSpareOutDao {

    /**
     * 新增出库
     *
     * @return
     */
    Integer insert(DevicesSpareOut devicesSpareOut);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    DevicesSpareOut selectById(Integer id);

    /**
     * 列表 查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DevicesSpareOut> findAll(Integer pageNum, Integer pageSize);

    String findCode(String in_no);

}
