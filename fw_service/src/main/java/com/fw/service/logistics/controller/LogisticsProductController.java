package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.service.logistics.service.LogisticsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 库存管理
 *
 * @author xkliu
 * @date 2020/11/5
 */
@RestController
@RequestMapping("/logisticsProduct")
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
    public Result storeWarn(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsProductService.storeWarn(name, code, pageNum, pageSize);
    }


    /**
     * 扫码获取物料详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getProductDetail")
    public Result getProductDetail(@RequestParam(value = "id") Integer id) {
        return logisticsProductService.getProductDetail(id);
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
    public Result getNameByCode(@RequestParam(value = "code") String code) {
        return logisticsProductService.getNameByCode(code);
    }
    /**
     * 根据code获取物料信息
     * @param codes
     * @return
     */
    @GetMapping(value = "/findListByCode")
    public Result findListByCode(@RequestParam(value = "codes") String codes) {
        return logisticsProductService.findListByCode(codes);
    }
}
