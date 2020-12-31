package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingMonitor;
import com.fw.entity.produce.ProduceReworkMonitor;
import com.fw.service.produce.service.ProduceMoldingMonitorService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @des: 注塑过程监控控制器
 * @author: xkliu
 * @date: 2020/12/16
 */
@RequestMapping(value = "/produceMoldingMonitor")
@RestController
public class ProduceMoldingMonitorController {

    @Autowired
    private ProduceMoldingMonitorService produceMoldingMonitorService;

    /**
     * 调试
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/debug")
    @RequiresAuthentication
    public Result debug(@RequestBody ProduceMoldingMonitor produceMoldingMonitor) {
        return produceMoldingMonitorService.debug(produceMoldingMonitor);
    }

    /**
     * 开始生产
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/startProduct")
    @RequiresAuthentication
    public Result startProduct(@RequestBody ProduceMoldingMonitor produceMoldingMonitor) {
        return produceMoldingMonitorService.startProduct(produceMoldingMonitor);
    }


    /**
     * 完成生产
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/accomplishProduct")
    @RequiresAuthentication
    public Result accomplishProduct(@RequestBody ProduceMoldingMonitor produceMoldingMonitor) {
        return produceMoldingMonitorService.accomplishProduct(produceMoldingMonitor);
    }

    /**
     * 开始调试
     *
     * @param produceMoldingMonitor
     * @return
     */
    @PostMapping(value = "/startDebug")
    @RequiresAuthentication
    public Result startDebug(@RequestBody ProduceMoldingMonitor produceMoldingMonitor) {
        return produceMoldingMonitorService.startDebug(produceMoldingMonitor);
    }

}
