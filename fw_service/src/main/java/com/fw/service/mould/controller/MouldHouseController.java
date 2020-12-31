package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldHouse;
import com.fw.service.mould.service.MouldHouseService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 模具入库、出库、移库
 * @author lpsong
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/mouldHouse")
public class MouldHouseController {

    @Autowired
    private MouldHouseService mouldHouseService;

    /**
     * 新增/修改
     *
     * @param mouldHouse
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("mouldHouse:save")
    public Result save(@RequestBody MouldHouse mouldHouse) {
        return mouldHouseService.save(mouldHouse);
    }


    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("mouldHouse:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return mouldHouseService.delete(ids);
    }

    /**
     * 列表查询
     * @param code
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
                           @RequestParam(value = "operate", required = false) Integer operate,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldHouseService.findList(code, mouldCode,operate,startTime,endTime,status,pageNum, pageSize);
    }
}
