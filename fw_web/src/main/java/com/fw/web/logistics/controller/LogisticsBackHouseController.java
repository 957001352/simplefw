package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsBackHouse;
import com.fw.web.logistics.service.LogisticsBackHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 退库管理
 *
 * @author xkliu
 * @date 2020/11/12
 */
@RestController
@RequestMapping("/logisticsBackHouse")
@Api(description = "退库管理", value = "LogisticsBackHouseController")
public class LogisticsBackHouseController {


    @Autowired
    private LogisticsBackHouseService logisticsBackHouseService;

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(
            @RequestParam(value = "houseNo", required = false) String houseNo,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "productOrder", required = false) String productOrder,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsBackHouseService.findList(houseNo, status, productOrder,pageNum, pageSize);
    }


    /**
     * 退库明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getBackHouseDetail")
    @ApiOperation("退库明细")
    public Result getBackHouseDetail(@RequestParam(value = "id") Integer id) {
        return logisticsBackHouseService.getBackHouseDetail(id);
    }


    /**
     * 执行退库
     *
     * @param logisticsBackHouse
     * @return
     */
    @PostMapping("/cancellingStocks")
    @ApiOperation("执行退库")
    public Result cancellingStocks(@RequestBody LogisticsBackHouse logisticsBackHouse) {
        return logisticsBackHouseService.cancellingStocks(logisticsBackHouse);
    }

    /**
     * 历史列表查询
     *
     * @param code
     * @param productCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findHistoryList")
    @ApiOperation("历史列表查询")
    public Result findHistoryList(@RequestParam(value = "houseNo", required = false) String houseNo,
                                  @RequestParam(value = "code", required = false) String code,
                                  @RequestParam(value = "productCode", required = false) String productCode,
                                  @RequestParam(value = "startExecuteTime", required = false) String startExecuteTime,
                                  @RequestParam(value = "endExecuteTime", required = false) String endExecuteTime,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsBackHouseService.findHistoryList(houseNo, code, productCode, startExecuteTime, endExecuteTime, pageNum, pageSize);
    }


    /**
     * 查看退库明细带分页
     *
     * @param id
     * @return
     */
    @GetMapping("/backHouseDetailPage")
    @ApiOperation("查看退库明细带分页")
    public Result backHouseDetailPage(@RequestParam(value = "id") Integer id,
                                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsBackHouseService.backHouseDetailPage(id, pageNum, pageSize);
    }

    /**
     * 生产新增退库任务
     *
     * @param logisticsBackHouse
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("生产新增退库任务")
    public Result save(@RequestBody LogisticsBackHouse logisticsBackHouse) {
        return logisticsBackHouseService.save(logisticsBackHouse);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @ApiOperation("删除")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return logisticsBackHouseService.delete(ids);
    }
}
