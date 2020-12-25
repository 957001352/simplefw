package com.fw.web.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.*;
import com.fw.web.mould.service.MouldDevicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @Description:    模具履历
* @Author:         gchen
* @CreateDate:     2020/10/26 12:16
*/
@RestController
@RequestMapping("/mouldDevices")
@Api(description = "模具履历", value = "MouldDevicesController")
public class MouldDevicesController {

    @Autowired
    private MouldDevicesService mouldDevicesServiceImpl;

    @PostMapping(value = "/save")
    @ApiOperation(value = "新增/修改")
    public Result save(@RequestBody MouldDevices mouldDevices) {
        return mouldDevicesServiceImpl.save(mouldDevices);
    }

    /**
     * 批量删除
     * @param ids
     */
    @GetMapping(value = "/delete")
    @ApiOperation(value = "批量删除")
    public Result delete(@RequestParam String ids) {
        return mouldDevicesServiceImpl.delete(ids);
    }


    @GetMapping(value = "/findList")
    @ApiOperation(value = "列表查询")
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "mouldName", required = false) String mouldName,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "turnStatus", required = false) Integer turnStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldDevicesServiceImpl.findList(code, mouldName,status,turnStatus,pageNum,pageSize);
    }

    /**
     * 模具报废申请
     */
    @PostMapping(value = "/saveScrap")
    @ApiOperation(value = "模具报废申请")
    public Result saveScrap(@RequestBody MouldScrap mouldScrap) {
        return mouldDevicesServiceImpl.saveScrap(mouldScrap);
    }

    /**
     * 模具停用申请
     */
    @PostMapping(value = "/saveStop")
    @ApiOperation(value = "模具停用申请")
    public Result saveStop(@RequestBody MouldStop mouldStop) {
        return mouldDevicesServiceImpl.saveStop(mouldStop);
    }

    /**
     * 模具转段申请
     */
    @PostMapping(value = "/saveTurn")
    @ApiOperation(value = "模具转段申请")
    public Result saveTurn(@RequestBody MouldTurn mouldTurn) {
        return mouldDevicesServiceImpl.saveTurn(mouldTurn);
    }

    /**
     * 转段履历
     */
    @GetMapping(value = "/findTurn")
    @ApiOperation(value = "转段履历")
    public Result findTurn(@RequestParam("mouldDeviceId") Integer mouldDeviceId) {
        return mouldDevicesServiceImpl.findTurn(mouldDeviceId);
    }

    /**
     * 查询所有未绑定库位的模具
     */
    @GetMapping(value = "/findUnBoundMould")
    @ApiOperation(value = "查询所有未绑定库位的模具")
    public Result findUnBoundMould() {
        return mouldDevicesServiceImpl.findUnBoundMould();
    }


    /**
     * 新增模具使用
     * @param mouldUseRecord
     * @return result
     */
    @PostMapping(value = "/saveUseRecord")
    @ApiOperation(value= "新增模具使用")
    public Result saveUseRecord(@RequestBody MouldUseRecord mouldUseRecord) {
        return mouldDevicesServiceImpl.saveUseRecord(mouldUseRecord);
    }

    /**
     * 查询模具使用
     * @param status
     * @return result
     */
    @GetMapping(value = "/findListUseRecord")
    @ApiOperation(value= "查询模具使用")
    public Result findListUseRecord(@RequestParam(value = "mouldCode",required = false) String mouldCode,
                                    @RequestParam(value = "status",required = false) Integer status,
                                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        return mouldDevicesServiceImpl.findListUseRecord(mouldCode,status,pageNum,pageSize);
    }

    /**
     * 根据id查询转段信息
     * @param id
     * @return result
     */
    @GetMapping(value = "/findOneById")
    @ApiOperation(value= "根据id查询转段信息")
    public Result findOneById(@RequestParam("id") Integer id){
        return mouldDevicesServiceImpl.findOneById(id);
    }
}

