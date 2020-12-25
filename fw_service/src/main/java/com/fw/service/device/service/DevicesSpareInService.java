package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareIn;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/27
 * Time:8:57
 * @Description:
 */
public interface DevicesSpareInService {

    /**
     * 新增出库
     *
     * @return
     */
    Result insert(DevicesSpareIn devicesSpareIn) throws Exception;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Result selectByIn(Integer id);

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findAll(Integer pageNum, Integer pageSize);
}
