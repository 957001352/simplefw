package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingMonitor;
import com.fw.web.produce.service.ProduceMoldingMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "注塑过程监控", value = "ProduceMoldingMonitorController")
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
    @ApiOperation("调试")
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
    @ApiOperation("开始生产")
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
    @ApiOperation("完成生产")
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
    @ApiOperation("开始调试")
    public Result startDebug(@RequestBody ProduceMoldingMonitor produceMoldingMonitor) {
        return produceMoldingMonitorService.startDebug(produceMoldingMonitor);
    }

}
