package com.fw.service.device.dao;

import com.fw.entity.device.DevicesOutinDetail;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/22
 * Time:12:10
 * @Description:
 */
public interface DevicesOutInDetailDao {

    /**
     * 新增
     *
     * @param devicesOutinDetail
     * @return
     */
    Integer insert(DevicesOutinDetail devicesOutinDetail);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    DevicesOutinDetail selectById(Integer id);

    /**
     * 根据出入库对象查询
     *
     * @param outIn
     * @return
     */
    List<DevicesOutinDetail> selectByOutin(Integer outIn,String operate);

    /**
     * 列表查询
     *
     * @param outOrInNo 出入库单号
     * @param operate   操作 0：入库  1 ：出库
     * @param startTime 查询开始时间
     * @param stopTime 查询结束时间
     * @return
     */
    List<LinkedHashMap<String,Object>> findAll(
                                @Param("outOrInNo") String outOrInNo,
                                @Param("operate") String operate,
                                @Param("startTime")String startTime,
                                @Param("stopTime")String stopTime);
}
