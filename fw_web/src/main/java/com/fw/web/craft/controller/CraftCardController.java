package com.fw.web.craft.controller;

import com.fw.domain.Result;
import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CardParams;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.web.craft.service.CraftCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
* @Description:    工艺卡管理
* @Author:         gchen
* @CreateDate:     2020/11/6 15:56
*/
@RestController
@RequestMapping("/craftCard")
@Api(description = "工艺卡管理", value = "CraftCardController")
public class CraftCardController {
    @Autowired
    private CraftCardService craftCardService;

    /**
     * 新增/修改
     * @param cardParams
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("新增/修改")
    public Result save(@RequestBody CardParams cardParams){
        return craftCardService.save(cardParams);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    @ApiOperation("批量删除")
    public Result delete(@RequestParam("ids") String ids){
        return craftCardService.delete(ids);
    }

    /**
     * 工艺卡参数列表查询
     * @param partCode
     * @param partName
     * @param productId
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("工艺卡参数列表查询")
    public Result findList(@RequestParam(value = "partCode",required = false)String partCode,
                           @RequestParam(value = "partName",required = false)String partName,
                           @RequestParam(value = "productId",required = false)Integer productId,
                           @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        return craftCardService.findList(partCode,partName,productId,pageNum,pageSize);
    }

    /**
     * 工艺卡列表
     */
    @GetMapping("/findListCard")
    @ApiOperation("工艺卡列表")
    public Result findListCard(){
        return craftCardService.findListCard();
    }

    /**
     * 工艺卡变更
     */
    @PostMapping("/saveCardLog")
    @ApiOperation("工艺卡变更")
    public Result saveCardLog(@RequestBody CardLog cardLog){
        return craftCardService.saveCardLog(cardLog);
    }

    /**
     * 查看工艺卡变更列表
     */
    @GetMapping("/findCardLog")
    @ApiOperation("查看工艺卡变更列表")
    public Result findCardLog(@RequestParam(value = "cardLogId",required = false)Integer cardLogId,
                              @RequestParam(value = "paramsId",required = false)Integer paramsId){
        return craftCardService.findCardLog(cardLogId,paramsId);
    }
    /**
     * 根据零件编码查询相关设备
     */
    @GetMapping("/findDeviceByPartCode")
    @ApiOperation("根据零件编码查询相关设备")
    public Result findDeviceByPartCode(@RequestParam(value = "partCode")String partCode){
        return craftCardService.findDeviceByPartCode(partCode);
    }
    /**
     * 查询设备正在生产的生产指令所对应的工艺卡
     */
    @PostMapping(value = "/findIngCardByDeviceId")
    @ApiOperation(value ="查询设备正在生产的生产指令所对应的工艺卡")
    public Result findIngCardByDeviceId(@RequestBody DevicesItemVo devicesItemVo) {
        return craftCardService.findIngCardByDeviceId(devicesItemVo);
    }
    /**
     * 设定值修改记录
     */
    @GetMapping(value = "/findUpdateParamLog")
    //@RequiresPermissions("injectionMolding:moveUpDownCancel")
    public Result findUpdateParamLog(@RequestParam(value = "deviceCode",required = false) String deviceCode,
                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        return craftCardService.findUpdateParamLog(deviceCode,pageNum,pageSize);
    }

    /**
     * 根据id查询工艺卡参数
     */
    @GetMapping(value = "/findById")
    //@RequiresPermissions("injectionMolding:moveUpDownCancel")
    @ApiOperation(value ="根据id查询工艺卡参数")
    public Result findById(@RequestParam(value = "id") Integer id) {
        return craftCardService.findById(id);
    }

    /**
     * 根据id查询工艺卡变更记录参数
     */
    @GetMapping(value = "/findCardLogById")
    //@RequiresPermissions("injectionMolding:moveUpDownCancel")
    @ApiOperation(value ="根据id查询工艺卡变更记录参数")
    public Result findCardLogById(@RequestParam(value = "id") Integer id) {
        return craftCardService.findCardLogById(id);
    }
}
