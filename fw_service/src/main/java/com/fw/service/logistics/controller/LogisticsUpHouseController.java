package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsDownHouse;
import com.fw.entity.logistics.LogisticsUpHouse;
import com.fw.service.logistics.service.LogisticsUpHouseService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 上架管理
 *
 * @author xkliu
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/logisticsUpHouse")
public class LogisticsUpHouseController {


    @Autowired
    private LogisticsUpHouseService logisticsUphouseService;

    /**
     * 新增上架
     *
     * @param logisticsUpHouse
     * @return
     */
    @PostMapping(value = "/upHouse")
    @RequiresAuthentication
    public Result upHouse(@RequestBody LogisticsUpHouse logisticsUpHouse) {
        return logisticsUphouseService.upHouse(logisticsUpHouse);
    }


    /**
     * 新增下架
     *
     * @param logisticsDownHouse
     * @return
     */
    @PostMapping(value = "/downHouse")
    @RequiresAuthentication
    public Result upHouse(@RequestBody LogisticsDownHouse logisticsDownHouse) {
        return logisticsUphouseService.downHouse(logisticsDownHouse);
    }


    /**
     * 上架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findUpList")
    @RequiresAuthentication
    public Result findUpList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsUphouseService.findUpList(code, pageNum, pageSize);
    }

    /**
     * 下架列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findDownList")
    @RequiresAuthentication
    public Result findDownList(
                            @RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsUphouseService.findDownList(code,pageNum, pageSize);
    }

    /**
     * 查看上架明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getUpHouse")
    @RequiresAuthentication
    public Result getUpHouse(@RequestParam(value = "id") Integer id) {
        return logisticsUphouseService.getUpHouse(id);
    }

    /**
     * 查看下架明细
     *
     * @param id
     * @return
     */
    @GetMapping("/getDownHouse")
    @RequiresAuthentication
    public Result getDownHouse(@RequestParam(value = "id") Integer id) {
        return logisticsUphouseService.getDownHouse(id);
    }

    /**
     * 上下架记录
     *
     * @param code
     * @param storageName
     * @param startCreateTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findAllList")
    @RequiresAuthentication
    public Result findAllList(@RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "storageName", required = false) String storageName,
                              @RequestParam(value = "startCreateTime", required = false) String startCreateTime,
                              @RequestParam(value = "endCreateTime", required = false) String endCreateTime,
                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return logisticsUphouseService.findAllList(code, storageName, startCreateTime, endCreateTime,pageNum, pageSize);
    }
}
