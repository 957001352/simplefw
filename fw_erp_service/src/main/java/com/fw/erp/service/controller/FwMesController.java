package com.fw.erp.service.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsProduct;
import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;
import com.fw.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Erp给Mes数据同步接口服务
 * @Author lpsong
 * @Date 2020/11/5
 */
@RestController
@RequestMapping("/fwMesController")
@Api(description = "MES与ERP数据同步", value = "LogisticsStorageHouseController")
public class FwMesController {

    /**-----------------------MES获取ERP数据接口-----------------------**/
    @PostMapping(value = "/productDevicesSpare")
    @ApiOperation("设备备品备件")
    public Result devicesSpare(@RequestBody LogisticsStorageHouse storageHouse) {


        return ResultUtils.success();
    }
    @PostMapping(value = "/mouldDevicesSpare")
    @ApiOperation("模具备品备件")
    public Result mouldDevicesSpare(@RequestBody LogisticsStorageHouse storageHouse) {


        return ResultUtils.success();
    }

    @PostMapping(value = "/mouldDevicesSpare1")
    @ApiOperation("委外订单")
    public Result mouldDevicesSpare1(@RequestBody LogisticsStorageHouse storageHouse) {


        return ResultUtils.success();
    }

    @PostMapping(value = "/mouldDevicesSpare1")
    @ApiOperation("采购订单")
    public Result mouldDevicesSpare2(@RequestBody LogisticsStorageHouse storageHouse) {


        return ResultUtils.success();
    }
    @PostMapping(value = "/logisticsStorage")
    @ApiOperation("物流仓库数据")
    public Result logisticsStorage(@RequestBody LogisticsStorageHouse storageHouse) {


        return ResultUtils.success();
    }
    @PostMapping(value = "/logisticsLocation")
    @ApiOperation("物流库位数据")
    public Result logisticsLocation(@RequestBody LogisticsStorageHouse storageHouse) {


        return ResultUtils.success();
    }

    @PostMapping(value = "/logisticsMaterial")
    @ApiOperation("物流物料数据")
    public Result logisticsMaterial(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }

    @PostMapping(value = "/logisticsMaterialDetail")
    @ApiOperation("物料明细数据")
    public Result logisticsMaterialDetail(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }


    @PostMapping(value = "/pickingOrder")
    @ApiOperation("领料单")
    public Result pickingOrder1(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }

    @PostMapping(value = "/pickingOrder")
    @ApiOperation("获取排查计划生产指令")
    public Result pickingOrder2(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }
    @PostMapping(value = "/pickingOrder")
    @ApiOperation("获取二次加工生产指令")
    public Result pickingOrder(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }



    /**-----------------------MES发送ERP数据接口-----------------------**/

    @PostMapping(value = "/reworkInjection")
    @ApiOperation("发送物流入库明细")
    public Result reworkInjection3(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }

    @PostMapping(value = "/reworkInjection")
    @ApiOperation("发送物流出库明细")
    public Result reworkInjection4(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }


    @PostMapping(value = "/injectionMolding")
    @ApiOperation("发送注塑排查计划")
    public Result injectionMolding(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }

    @PostMapping(value = "/reworkInjection")
    @ApiOperation("发送二次加工计划")
    public Result reworkInjection(@RequestBody LogisticsProduct material) {


        return ResultUtils.success();
    }
}