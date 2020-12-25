package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareOut;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/22
 * Time:14:51
 * @Description:
 */
public interface DevicesSpareOutService {

    /**
     * 新增出库
     *
     * @return
     */
    Result insert(DevicesSpareOut devicesSpareOut) throws Exception;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Result selectByOut(Integer id);

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findAll(Integer pageNum, Integer pageSize);
}
