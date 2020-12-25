package com.fw.service.device.service;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetail;
import com.fw.entity.device.DevicesOutinDetailDTO;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/22
 * Time:11:28
 * @Description:
 */
public interface DevicesOutInDetailService {



    /**
     * 根据id查询出入库信息
     *
     * @param id
     * @return
     */
    Result selectById(Integer id);

    /**
     * 列表查询
     *
     * @param outOrInNo
     * @param operate
     * @param startTime
     * @param stopTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findAll(String outOrInNo,
                   String operate,
                   String startTime,
                   String stopTime,
                   Integer pageNum,
                   Integer pageSize);
}
