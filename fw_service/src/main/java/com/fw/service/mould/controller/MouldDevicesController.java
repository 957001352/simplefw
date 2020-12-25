package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.*;
import com.fw.service.mould.service.*;
import com.fw.service.mould.service.impl.MouldTurnServiceImpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @Description:    模具履历
* @Author:         gchen
* @CreateDate:     2020/10/26 12:16
*/
@RestController
@RequestMapping("/mouldDevices")
public class MouldDevicesController {

    @Resource
    private MouldDevicesService MouldDevicesServiceImpl;
    @Resource
    private MouldScrapService mouldScrapServiceImpl;
    @Resource
    private MouldStopService mouldStopServiceImpl;
    @Resource
    private MouldTurnService mouldTurnServiceImpl;

    /**
     * 新增/修改
     * @param mouldDevices
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody MouldDevices mouldDevices) {
        return MouldDevicesServiceImpl.save(mouldDevices);
    }

    /**
     * 批量删除
     * @param ids
     */
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        return MouldDevicesServiceImpl.delete(ids);
    }

    /**
     * 列表查询
     */
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "mouldName", required = false) String mouldName,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "turnStatus", required = false) Integer turnStatus,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return MouldDevicesServiceImpl.findList(code, mouldName,status,turnStatus,pageNum,pageSize);
    }

    /**
     * 模具报废申请
     */
    @PostMapping(value = "/saveScrap")
    public Result saveScrap(@RequestBody MouldScrap mouldScrap) {
        return mouldScrapServiceImpl.save(mouldScrap);
    }

    /**
     * 模具停用申请
     */
    @PostMapping(value = "/saveStop")
    public Result saveStop(@RequestBody MouldStop mouldStop) {
        return mouldStopServiceImpl.save(mouldStop);
    }

    /**
     * 模具转段申请
     */
    @PostMapping(value = "/saveTurn")
    public Result saveTurn(@RequestBody MouldTurn mouldTurn) {
        return mouldTurnServiceImpl.save(mouldTurn);
    }


    /**
     * 转段履历
     */
    @GetMapping(value = "/findTurn")
    public Result findTurn(@RequestParam("mouldDeviceId") Integer mouldDeviceId) {
        return mouldTurnServiceImpl.findTurn(mouldDeviceId);
    }


    /**
     * 查询所有未绑定库位的模具
     */
    @GetMapping(value = "/findUnBoundMould")
    public Result findUnBoundMould() {
        return MouldDevicesServiceImpl.findUnBoundMould();
    }


    /**
     * 新增模具使用
     * @param mouldUseRecord
     * @return result
     */
    @PostMapping(value = "/saveUseRecord")
    //@RequiresPermissions("mouldStorageHouse:saveUseRecord")
    public Result saveUseRecord(@RequestBody MouldUseRecord mouldUseRecord) {
        return MouldDevicesServiceImpl.saveUseRecord(mouldUseRecord);
    }

    /**
     * 查询模具使用
     * @param status
     * @return result
     */
    @GetMapping(value = "/findListUseRecord")
//    @RequiresAuthentication
    public Result findListUseRecord(@RequestParam(value = "mouldCode",required = false) String mouldCode,
                                    @RequestParam(value = "status",required = false) Integer status,
                                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        return MouldDevicesServiceImpl.findListUseRecord(mouldCode,status,pageNum,pageSize);
    }

    /**
     * 根据id查询转段信息
     * @param id
     * @return result
     */
    @GetMapping(value = "/findOneById")
//    @RequiresAuthentication
    public Result findOneById(@RequestParam("id") Integer id){
        return mouldTurnServiceImpl.findOneById(id);
    }

    /**
     * 转段申请通过
     * @param id
     */
    @GetMapping(value = "/mouldTurnPass")
//    @RequiresAuthentication
    public Result mouldTurnPass(@RequestParam("id") Integer id){
        return mouldTurnServiceImpl.mouldTurnPass(id);
    }
}

