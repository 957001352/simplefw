package com.fw.web.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingMonitor;
import com.fw.web.produce.service.fbk.ProduceMoldingMonitorServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @des: 注塑过程监控Feign接口
 * @author: xkliu
 * @date: 2020/12/16
 */
@FeignClient(value = "fw-service/produceMoldingMonitor", fallback = ProduceMoldingMonitorServiceFbk.class)
public interface ProduceMoldingMonitorService {

    /**
     * 调试
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/debug")
    Result debug(@RequestBody ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 开始生产
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/startProduct")
    Result startProduct(@RequestBody ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 完成生产
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/accomplishProduct")
    Result accomplishProduct(@RequestBody ProduceMoldingMonitor produceMoldingMonitor);

    /**
     * 开始调试
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/startDebug")
    Result startDebug(@RequestBody ProduceMoldingMonitor produceMoldingMonitor);
}
