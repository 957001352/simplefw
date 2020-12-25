package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingMonitor;

/**
 * @des: 注塑过程监控 服务类
 * @author: xkliu
 * @date: 2020/12/16
 */
public interface ProduceMoldingMonitorService {

    /**
     * 完成调试
     *
     * @param produceMoldingMonitor
     * @return
     */
    Result debug(ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 开始生产
     *
     * @param produceMoldingMonitor
     * @return
     */
    Result startProduct(ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 完成生产
     *
     * @param produceMoldingMonitor
     * @return
     */
    Result accomplishProduct(ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 开始调试
     * @param produceMoldingMonitor
     * @return
     */
    Result startDebug(ProduceMoldingMonitor produceMoldingMonitor);
}
