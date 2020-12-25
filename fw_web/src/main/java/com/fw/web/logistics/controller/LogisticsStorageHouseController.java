package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;
import com.fw.web.logistics.service.LogisticsStorageHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 物流仓库管理
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
@RestController
@RequestMapping("/logisticsStorageHouse")
@Api(description = "物流仓库管理", value = "LogisticsStorageHouseController")
public class LogisticsStorageHouseController {

    @Autowired
    private LogisticsStorageHouseService logisticsStorageHouseService;


    /**
     * 新增/修改物流仓库
     *
     * @param logisticsStorageHouse
     * @return
     */
    @PostMapping(value = "/saveStorage")
    @ApiOperation("新增/修改物流仓库")
    public Result saveStorage(@RequestBody LogisticsStorageHouse logisticsStorageHouse) {
        return logisticsStorageHouseService.saveStorage(logisticsStorageHouse);
    }


    /**
     * 删除物流仓库
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteStorage")
    @ApiOperation("删除物流仓库")
    public Result deleteStorage(@RequestParam(value = "ids") String ids) {
        return logisticsStorageHouseService.deleteStorage(ids);
    }

    /**
     * 物流仓库列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findStorageList")
    @ApiOperation("物流仓库列表查询")
    public Result findStorageList(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsStorageHouseService.findStorageList(name, pageNum, pageSize);
    }

    /**
     * 新增/修改物流库位
     *
     * @param logisticsStorageLocation
     * @return
     */
    @PostMapping(value = "/saveLocation")
    @ApiOperation("新增/修改物流库位")
    public Result saveLocation(@RequestBody LogisticsStorageLocation logisticsStorageLocation) {
        return logisticsStorageHouseService.saveLocation(logisticsStorageLocation);
    }


    /**
     * 删除物流库位
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteLocation")
    @ApiOperation("删除物流库位")
    public Result deleteLocation(@RequestParam(value = "ids") String ids) {
        return logisticsStorageHouseService.deleteLocation(ids);
    }

    /**
     * 物流库位列表查询
     *
     * @param name
     * @param productCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findLocationList")
    @ApiOperation("物流库位列表查询")
    public Result findLocationList(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "productCode", required = false) String productCode,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsStorageHouseService.findLocationList(name, productCode, pageNum, pageSize);
    }


    /**
     * 查询库位
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findStorageLocationList")
    @ApiOperation("查询库位")
    public Result findStorageLocationList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsStorageHouseService.findStorageLocationList(pageNum, pageSize);
    }
}
