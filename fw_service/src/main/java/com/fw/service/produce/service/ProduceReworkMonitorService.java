package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceReworkMonitor;

/**
 * @des: 二次加工监控 服务类
 * @author: xkliu
 * @date: 2020/12/15
 */
public interface ProduceReworkMonitorService {

    /**
     * 开始生产
     *
     * @param produceReworkMonitor
     * @return
     */
    Result startProduce(ProduceReworkMonitor produceReworkMonitor);

    /**
     * 完成生产
     *
     * @param produceReworkMonitor
     * @return
     */
    Result endProduce(ProduceReworkMonitor produceReworkMonitor);

    /**
     * 报检
     *
     * @param produceReworkMonitor
     * @return
     */
    Result customsInspection(ProduceReworkMonitor produceReworkMonitor);

    /**
     * 入库检验完调用的接口,修改二次加工的检验数据
     *
     * @return
     */
    Integer checkUser(String productOrder);
}
