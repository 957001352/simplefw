package com.fw.web.logistics.service;


import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsStorageHouse;
import com.fw.entity.logistics.LogisticsStorageLocation;
import com.fw.web.logistics.service.fbk.LogisticsStorageHouseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 物流库位Feign接口
 *
 * @Author xkliu
 * @Date 2020/11/3
 **/
@FeignClient(value = "fw-service/logisticsStorageHouse", fallback = LogisticsStorageHouseServiceFbk.class)
public interface LogisticsStorageHouseService {

    /**
     * 新增/修改物流仓库
     *
     * @param logisticsStorageHouse
     * @return
     */
    @PostMapping(value = "/saveStorage")
    Result saveStorage(@RequestBody LogisticsStorageHouse logisticsStorageHouse);


    /**
     * 删除物流仓库
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteStorage")
    Result deleteStorage(@RequestParam(value = "ids") String ids);

    /**
     * 物流仓库列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findStorageList")
    Result findStorageList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 新增/修改物流库位
     *
     * @param logisticsStorageLocation
     * @return
     */
    @PostMapping(value = "/saveLocation")
    Result saveLocation(@RequestBody LogisticsStorageLocation logisticsStorageLocation);


    /**
     * 删除物流库位
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteLocation")
    Result deleteLocation(@RequestParam(value = "ids") String ids);

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
    Result findLocationList(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "productCode", required = false) String productCode,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 查询库位
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findStorageLocationList")
    Result findStorageLocationList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
