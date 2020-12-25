package com.fw.service.produce.dao;

import com.fw.entity.produce.ProduceMoldingMonitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 物料过程监控 Dao 接口
 *
 * @author xkliu
 * @since 2020-12-16
 */
@Repository
public interface ProduceMoldingMonitorDao {

    /**
     * 新增
     *
     * @param produceMoldingMonitor
     * @return
     */
    Integer insert(ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 修改
     *
     * @param produceMoldingMonitor
     * @return
     */
    Integer update(ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 根据生产指令获取ProduceMoldingMonitor对象
     *
     * @param productOrder
     * @return
     */
    ProduceMoldingMonitor getMoldingMonitor(String productOrder);

    /**
     * 根据生产指令和 status 获取ProduceMoldingMonitor对象
     *
     * @param productOrder
     * @return
     */
    ProduceMoldingMonitor getMoldingMonitorAndStatus(@Param("productOrder") String productOrder,@Param("status")Integer status);

}
