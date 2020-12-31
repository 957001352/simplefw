package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.web.logistics.service.LogisticsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存管理
 *
 * @author xkliu
 * @date 2020/11/5
 */
@RestController
@RequestMapping("/logisticsProduct")
@Api(description = "库存管理", value = "LogisticsProductController")
public class LogisticsProductController {

    @Autowired
    private LogisticsProductService logisticsProductService;


    /**
     * 列表查询
     *
     * @param code
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsProductService.findList(code, name, pageNum, pageSize);
    }

    /**
     * 设置安全库存
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/setStore")
    @ApiOperation("设置安全库存")
    public Result setStore(@RequestParam(value = "ids") String ids,
                           @RequestParam(value = "maxStore") Integer maxStore,
                           @RequestParam(value = "minStore") Integer minStore) {
        return logisticsProductService.setStore(ids, maxStore, minStore);
    }


    /**
     * 物料超期设置
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/setLogisticsExceed")
    @ApiOperation("物料超期设置")
    public Result setLogisticsExceed(@RequestParam(value = "ids") String ids,
                                     @RequestParam(value = "warnTime") Integer warnTime) {
        return logisticsProductService.setLogisticsExceed(ids, warnTime);
    }


    /**
     * 超期预警
     *
     * @param name
     * @param code
     * @return
     */
    @GetMapping(value = "/exceedWarn")
    @ApiOperation("超期预警")
    public Result exceedWarn(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "code", required = false) String code,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsProductService.exceedWarn(name, code, pageNum, pageSize);
    }

    /**
     * 库存预警
     *
     * @param name
     * @param code
     * @return
     */
    @GetMapping(value = "/storeWarn")
    @ApiOperation("库存预警")
    public Result storeWarn(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsProductService.storeWarn(name, code, pageNum, pageSize);
    }


    /**
     * PAD扫码获取物料详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getProductDetail")
    @ApiOperation("PAD扫码获取物料详情")
    public Result getProductDetail(@RequestParam(value = "id") Integer id) {
        return logisticsProductService.getProductDetail(id);
    }
    /**
     * 扫码获取上架物料详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getUpProductDetail")
    @ApiOperation("PAD扫码获取上架物料详情")
    public Result getUpProductDetail(@RequestParam(value = "id") Integer id) {
        return logisticsProductService.getUpProductDetail(id);
    }
    /**
     * 扫码获取下架物料详情
     *
     * @param id
     * @return
     */
    @ApiOperation("PAD扫码获取下架物料详情")
    @GetMapping(value = "/getDownProductDetail")
    public Result getDownProductDetail(@RequestParam(value = "id") Integer id,@RequestParam(value = "storageLocationId") Integer storageLocationId) {
        return logisticsProductService.getDownProductDetail(id,storageLocationId);
    }
    /**
     * 物料明细列表查询
     *
     * @param code
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findDetailList")
    @ApiOperation("物料明细列表查询")
    public Result findDetailList(@RequestParam(value = "code", required = false) String code,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "storageCode", required = false) String storageCode,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsProductService.findDetailList(code, name, storageCode, pageNum, pageSize);
    }

    /**
     * 获取所有的物料code
     *
     * @return
     */
    @GetMapping(value = "/getAllCode")
    @ApiOperation("获取所有的物料code")
    public Result getAllCode() {
        return logisticsProductService.getAllCode();
    }

    /**
     * 根据code获取物料名称
     *
     * @param code
     * @return
     */
    @GetMapping(value = "/getNameByCode")
    @ApiOperation("根据code获取物料名称")
    public Result getNameByCode(@RequestParam(value = "code") String code) {
        return logisticsProductService.getNameByCode(code);
    }
    /**
     * 根据code获取物料信息
     * @param codes
     * @return
     */
    @GetMapping(value = "/findListByCode")
    @ApiOperation("根据code获取物料信息")
    public Result findListByCode(@RequestParam(value = "codes") String codes) {
        return logisticsProductService.findListByCode(codes);
    }
}
