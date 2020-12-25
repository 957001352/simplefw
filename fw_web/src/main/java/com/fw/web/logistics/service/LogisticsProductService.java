package com.fw.web.logistics.service;

import com.fw.domain.Result;
import com.fw.web.logistics.service.fbk.LogisticsProductServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存管理Feign接口
 *
 * @author xkliu
 * @date 2020/11/5
 */
@FeignClient(value = "fw-service/logisticsProduct", fallback = LogisticsProductServiceFbk.class)
public interface LogisticsProductService {


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
    Result findList(@RequestParam(value = "code", required = false) String code,
                    @RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 设置安全库存
     *
     * @param ids
     * @param maxStore
     * @param minStore
     * @return
     */
    @GetMapping(value = "/setStore")
    Result setStore(@RequestParam(value = "ids") String ids,
                    @RequestParam(value = "maxStore") Integer maxStore,
                    @RequestParam(value = "minStore") Integer minStore);

    /**
     * 设置超期
     *
     * @param ids
     * @param warnTime
     * @return
     */
    @GetMapping(value = "/setLogisticsExceed")
    Result setLogisticsExceed(@RequestParam(value = "ids") String ids,
                              @RequestParam(value = "warnTime") Integer warnTime);

    /**
     * 超期预警
     *
     * @param name
     * @param code
     * @return
     */
    @GetMapping(value = "/exceedWarn")
    Result exceedWarn(@RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "code", required = false) String code,
                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 库存预警
     *
     * @param name
     * @param code
     * @return
     */
    @GetMapping(value = "/storeWarn")
    Result storeWarn(@RequestParam(value = "name", required = false) String name,
                     @RequestParam(value = "code", required = false) String code,
                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * PAD扫码获取物料详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getProductDetail")
    Result getProductDetail(@RequestParam(value = "id") Integer id);

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
    Result findDetailList(@RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "storageCode", required = false) String storageCode,
                          @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 获取所有的物料code
     *
     * @return
     */
    @GetMapping(value = "/getAllCode")
    Result getAllCode();

    /**
     * 根据code获取物料名称
     *
     * @param code
     * @return
     */
    @GetMapping(value = "/getNameByCode")
    Result getNameByCode(@RequestParam(value = "code") String code);
    /**
     * 根据code获取物料信息
     * @param codes
     * @return
     */
    @GetMapping(value = "/findListByCode")
    Result findListByCode(@RequestParam(value = "codes") String codes);
}
