package com.fw.web.mould.service;


import com.fw.domain.Result;
import com.fw.entity.mould.*;
import com.fw.web.mould.service.fbk.MouldStorageHouseServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/mouldStorageHouse", fallback = MouldStorageHouseServiceFbk.class)
public interface MouldStorageHouseService {
    /**
     * 新增/修改模具仓库
     *
     * @param mouldStorageHouse
     * @return
     */
    @PostMapping(value = "/saveStorage")
    Result saveStorage(@RequestBody MouldStorageHouse mouldStorageHouse);


    /**
     * 删除模具仓库
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteStorage")
    Result deleteStorage(@RequestParam(value = "ids") String ids);

    /**
     * 模具仓库列表查询
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
     * 新增/修改模具库位
     *
     * @param mouldStorageLocation
     * @return
     */
    @PostMapping(value = "/saveLocation")
    Result saveLocation(@RequestBody MouldStorageLocation mouldStorageLocation);


    /**
     * 删除模具库位
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteLocation")
    Result deleteLocation(@RequestParam(value = "ids") String ids);

    /**
     * 模具库位列表查询
     *
     * @param storageHouseId 仓库
     * @param location       库位名称
     * @param mould          模具名称
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findLocationList")
    Result findLocationList(@RequestParam(value = "storageHouseId", required = false) Integer storageHouseId,
                            @RequestParam(value = "location", required = false) String location,
                            @RequestParam(value = "mould", required = false) String mould,
                            @RequestParam(value = "mouldCode", required = false) String mouldCode,
                            @RequestParam(value = "status", required = false) Integer status,
                            @RequestParam(value = "isBand", required = false) Integer isBand,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 绑定/关联模具
     *
     * @param mouldDevices
     * @return result
     */
    @PostMapping(value = "/boundMould")
    Result boundMould(@RequestBody MouldDevices mouldDevices);

    /**
     * 解除绑定
     *
     * @param mouldDevices
     * @return result
     */
    @PostMapping(value = "/unbundMoule")
    Result unbundMoule(@RequestBody MouldDevices mouldDevices);


    @GetMapping("/findEmptyLocationList")
    Result findEmptyLocationList(@RequestParam(value = "name",required = false) String name);
}
