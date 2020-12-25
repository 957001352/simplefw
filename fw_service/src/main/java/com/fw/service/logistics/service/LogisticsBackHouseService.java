package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsBackHouse;

/**
 * 退库管理 服务类
 *
 * @author xkliu
 * @date 2020/11/12
 */
public interface LogisticsBackHouseService {

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String houseNo, Integer status, String productOrder, Integer pageNum, Integer pageSize);

    /**
     * 获取退库明细
     *
     * @param id
     * @return
     */
    Result getBackHouseDetail(Integer id);

    /**
     * 退库
     *
     * @param logisticsBackHouse
     * @return
     */
    Result cancellingStocks(LogisticsBackHouse logisticsBackHouse);

    /**
     * 历史退库列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findHistoryList(String houseNo, String code, String productCode, String startExecuteTime, String endExecuteTime, Integer pageNum, Integer pageSize);

    /**
     * 查看退库明细带分页
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result backHouseDetailPage(Integer id, Integer pageNum, Integer pageSize);

    /**
     * 新增
     *
     * @return
     */
    Result save(LogisticsBackHouse logisticsBackHouse);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);
}
