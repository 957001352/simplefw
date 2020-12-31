package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldDevices;
import com.fw.entity.mould.MouldStorageHouse;
import com.fw.entity.mould.MouldStorageLocation;
import com.fw.entity.mould.MouldUseRecord;
import com.fw.service.mould.service.MouldStorageHouseService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 模具仓库管理,绑定,解绑,关联，模具使用新增，查询
 * @author lpsong
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/mouldStorageHouse")
public class MouldStorageHouseController {

    @Autowired
    private MouldStorageHouseService mouldStorageHouseService;

    /**
     * 新增/修改模具仓库
     *
     * @param mouldStorageHouse
     * @return
     */
    @PostMapping(value = "/saveStorage")
    @RequiresPermissions("mouldStorageHouse:saveStorage")
    public Result saveStorage(@RequestBody MouldStorageHouse mouldStorageHouse) {
        return mouldStorageHouseService.saveStorage(mouldStorageHouse);
    }


    /**
     * 删除模具仓库
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteStorage")
    @RequiresPermissions("mouldStorageHouse:deleteStorage")
    public Result deleteStorage(@RequestParam(value = "ids") String ids) {
        return mouldStorageHouseService.deleteStorage(ids);
    }

    /**
     * 模具仓库列表查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findStorageList")
    @RequiresAuthentication
    public Result findStorageList(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldStorageHouseService.findStorageList(name, pageNum, pageSize);
    }

    /**
     * 新增/修改模具库位
     * @param mouldStorageLocation
     * @return
     */
    @PostMapping(value = "/saveLocation")
    @RequiresPermissions("mouldStorageHouse:saveLocation")
    public Result saveLocation(@RequestBody MouldStorageLocation mouldStorageLocation) {
        return mouldStorageHouseService.saveLocation(mouldStorageLocation);
    }


    /**
     * 删除模具库位
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/deleteLocation")
    @RequiresPermissions("mouldStorageHouse:deleteLocation")
    public Result deleteLocation(@RequestParam(value = "ids") String ids) {
        return mouldStorageHouseService.deleteLocation(ids);
    }

    /**
     * 模具库位列表查询
     * @param storageHouseId 仓库
     * @param location 库位名称
     * @param mould  模具名称
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findLocationList")
    @RequiresAuthentication
    public Result findLocationList(@RequestParam(value = "storageHouseId", required = false) Integer storageHouseId,
                                   @RequestParam(value = "location", required = false) String location,
                                   @RequestParam(value = "mould", required = false) String mould,
                                   @RequestParam(value = "mouldCode", required = false) String mouldCode,
                                   @RequestParam(value = "status", required = false) Integer status,
                                   @RequestParam(value = "isBand", required = false) Integer isBand,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldStorageHouseService.findLocationList(storageHouseId, location, mould,mouldCode,status,isBand, pageNum, pageSize);
    }

    /**
     * 绑定/关联模具
     * @param mouldDevices
     * @return result
     */
    @PostMapping(value = "/boundMould")
    @RequiresPermissions("mouldStorageHouse:boundMould")
    public Result boundMould(@RequestBody MouldDevices mouldDevices) {
        return mouldStorageHouseService.boundMould(mouldDevices);
    }

    /**
     * 解除绑定
     * @param mouldDevices
     * @return result
     */
    @PostMapping(value = "/unbundMoule")
    @RequiresPermissions("mouldStorageHouse:unbundMoule")
    public Result unbundMoule(@RequestBody MouldDevices mouldDevices) {
        return mouldStorageHouseService.unbundMoule(mouldDevices);
    }
    /**
    * 空库位查询
     * @param name
    * @return
    */
    @GetMapping("/findEmptyLocationList")
    @RequiresAuthentication
    public Result findEmptyLocationList(@RequestParam(value = "name",required = false) String name) {
        return mouldStorageHouseService.findEmptyLocationList(name);
    }
}
