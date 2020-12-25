package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDownHouse;
import com.fw.entity.logistics.LogisticsUpHouse;
import com.fw.web.logistics.service.LogisticsUpHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 上下架管理
 *
 * @author xkliu
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/logisticsUpHouse")
@Api(description = "上下架管理", value = "LogisticsUpHouseController")
public class LogisticsUpHouseController {


    @Autowired
    private LogisticsUpHouseService logisticsUphouseService;


    /**
     * 新增上架
     *
     * @param logisticsUpHouse
     * @return
     */
    @PostMapping(value = "/upHouse")
    @ApiOperation("新增上架")
    public Result upHouse(@RequestBody LogisticsUpHouse logisticsUpHouse) {
        return logisticsUphouseService.upHouse(logisticsUpHouse);
    }


    /**
     * 新增下架
     *
     * @param logisticsDownHouse
     * @return
     */
    @PostMapping(value = "/downHouse")
    @ApiOperation("新增下架")
    public Result downHouse(@RequestBody LogisticsDownHouse logisticsDownHouse) {
        return logisticsUphouseService.downHouse(logisticsDownHouse);
    }

    /**
     * 上架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findUpList")
    @ApiOperation("上架列表查询")
    public Result findUpList(@RequestParam(value = "code", required = false) String code,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsUphouseService.findUpList(code, pageNum, pageSize);
    }

    /**
     * 下架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findDownList")
    @ApiOperation("下架列表查询")
    public Result findDownList(@RequestParam(value = "code", required = false) String code,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsUphouseService.findDownList(code, pageNum, pageSize);
    }

    /**
     * 查看上架明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getUpHouse")
    @ApiOperation("查看上架明细")
    public Result getUpHouse(@RequestParam(value = "id") Integer id) {
        return logisticsUphouseService.getUpHouse(id);
    }

    /**
     * 查看下架明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getDownHouse")
    @ApiOperation("查看下架明细")
    public Result getDownHouse(@RequestParam(value = "id") Integer id) {
        return logisticsUphouseService.getDownHouse(id);
    }


    /**
     * 上下架记录
     *
     * @param code
     * @param storageName
     * @param startCreateTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findAllList")
    @ApiOperation("上下架记录")
    public Result findAllList(@RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "storageName", required = false) String storageName,
                              @RequestParam(value = "startCreateTime", required = false) String startCreateTime,
                              @RequestParam(value = "endCreateTime", required = false) String endCreateTime,
                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsUphouseService.findAllList(code, storageName, startCreateTime, endCreateTime,pageNum, pageSize);
    }

}
