package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsCheckHouse;
import com.fw.entity.mould.MouldHouse;
import com.fw.service.logistics.service.LogisticsCheckHouseService;
import com.fw.service.mould.service.MouldHouseService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 盘库
 * @author lpsong
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/logisticsCheckHouse")
public class LogisticsCheckHouseController {

    @Autowired
    private LogisticsCheckHouseService logisticsCheckHouseService;

    /**
     * 新增
     * @param logisticsCheckHouse
     * @return
     */
    @PostMapping(value = "/insert")
    @RequiresPermissions("logisticsCheckHouse:insert")
    public Result insert(@RequestBody LogisticsCheckHouse logisticsCheckHouse) {
        return logisticsCheckHouseService.insert(logisticsCheckHouse);
    }

    /**
     * 盘库
     * @param logisticsCheckHouse
     * @return
     */
    @PostMapping(value = "/update")
    @RequiresPermissions("logisticsCheckHouse:update")
    public Result update(@RequestBody LogisticsCheckHouse logisticsCheckHouse) {
        return logisticsCheckHouseService.update(logisticsCheckHouse);
    }
    /**
     * 关闭
     * @param id
     * @return result
     */
    @GetMapping(value = "/updateStatus")
    @RequiresPermissions("logisticsCheckHouse:updateStatus")
    public Result updateStatus(@RequestParam(value = "id") Integer id) {
        return logisticsCheckHouseService.updateStatus(id);
    }

    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "houseNo", required = false) String houseNo,
                           @RequestParam(value = "checkTime", required = false) String checkTime,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "checkResult", required = false) Integer checkResult,
                           @RequestParam(value = "checkUser", required = false) Integer checkUser,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsCheckHouseService.findList(houseNo, checkTime,status,checkResult,checkUser,pageNum, pageSize);
    }
    /**
    * 盘库明细列表查询
     * @param checkHouseId
    * @return
    */
    @GetMapping("/findDetailList")
    @RequiresAuthentication
    public Result findDetailList(@RequestParam(value = "checkHouseId", required = false) Integer checkHouseId) {
        return logisticsCheckHouseService.findDetailList(checkHouseId);
    }
    /**
     * 根据库位查询要盘库物料
     * @param locationId
     * @return
     */
    @GetMapping("/findStoragePorductList")
    @RequiresAuthentication
    public Result findStoragePorductList(@RequestParam(value = "locationId", required = false) Integer locationId) {
        return logisticsCheckHouseService.findStoragePorductList(locationId);
    }
    @GetMapping("/findTreeList")
    @RequiresAuthentication
    public Result findTreeList(@RequestParam(value = "name", required = false) String name) {
        return logisticsCheckHouseService.findTreeList(name);
    }
}
