package com.fw.service.produce.dao;

import com.fw.entity.produce.ProduceReworkMonitor;
import org.springframework.stereotype.Repository;

/**
 * 二次加工过程监控 Dao 接口
 *
 * @author xkliu
 * @since 2020-12-15
 */
@Repository
public interface ProduceReworkMonitorDao {

    /**
     * 新增
     *
     * @param produceReworkMonitor
     * @return
     */
    Integer insert(ProduceReworkMonitor produceReworkMonitor);

    /**
     * 修改
     *
     * @param produceReworkMonitor
     * @return
     */
    Integer update(ProduceReworkMonitor produceReworkMonitor);

    /**
     * 根据生产指令获取ProduceReworkMonitor
     *
     * @param productOrder
     * @return
     */
    ProduceReworkMonitor getProduceReworkMonitor(String productOrder);

    /**
     * 根据生产质量获取ProduceReworkMonitor
     *
     * @param id
     * @return
     */
    ProduceReworkMonitor selectById(Integer id);


}
