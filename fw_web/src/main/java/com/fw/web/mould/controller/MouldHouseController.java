package com.fw.web.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldHouse;
import com.fw.web.mould.service.MouldHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 模具入库、出库、移库
 * @author lpsong
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/mouldHouse")
@Api(description = "模具入库、出库、移库", value = "MouldStorageHouseController")
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
    @ApiOperation(value= "新增/修改")
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
    @ApiOperation(value= "删除")
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
    @ApiOperation(value= "列表查询")
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "mouldCode", required = false) String mouldCode,
                           @RequestParam(value = "operate", required = false) Integer operate,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldHouseService.findList(code,mouldCode, operate,startTime,endTime,status,pageNum, pageSize);
    }
}
