package com.fw.service.craft.controller;

import com.fw.domain.Result;
import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CardParams;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.service.craft.service.CraftCardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @Description:    工艺卡管理
* @Author:         gchen
* @CreateDate:     2020/11/6 15:56
*/
@RestController
@RequestMapping("/craftCard")
public class CraftCardController {
    @Resource
    private CraftCardService craftCardServiceImpl;

    /**
     * 新增/修改
     * @param cardParams
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody CardParams cardParams){
        return craftCardServiceImpl.save(cardParams);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam("ids") String ids){
        return craftCardServiceImpl.delete(ids);
    }

    /**
     * 工艺卡参数列表查询
     * @param partCode
     * @param partName
     * @param productId
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "partCode",required = false)String partCode,
                           @RequestParam(value = "partName",required = false)String partName,
                           @RequestParam(value = "productId",required = false)Integer productId,
                           @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        return craftCardServiceImpl.findList(partCode,partName,productId,pageNum,pageSize);
    }

    /**
     * 工艺卡列表
     */
    @GetMapping("/findListCard")
    public Result findListCard(){
        return craftCardServiceImpl.findListCard();
    }

    /**
     * 工艺卡变更
     */
    @PostMapping("/saveCardLog")
    public Result saveCardLog(@RequestBody CardLog cardLog){
        return craftCardServiceImpl.saveCardLog(cardLog);
    }

    /**
     * 查看工艺卡变更列表
     */
    @GetMapping("/findCardLog")
    public Result findCardLog(@RequestParam(value = "cardLogId",required = false)Integer cardLogId,
                              @RequestParam(value = "paramsId",required = false)Integer paramsId){
        return craftCardServiceImpl.findCardLog(cardLogId,paramsId);
    }

    /**
     * 工艺卡变更通过
     */
    @GetMapping("/cardChangePass")
    public Result cardChangePass(@RequestParam(value = "id",required = false)Integer id){
        return craftCardServiceImpl.cardChangePass(id);
    }

    /**
     * 根据零件编码查询相关设备
     */
    @GetMapping("/findDeviceByPartCode")
    public Result findDeviceByPartCode(@RequestParam(value = "partCode")String partCode){
        return craftCardServiceImpl.findDeviceByPartCode(partCode);
    }

    /**
     * 查询设备正在生产的生产指令所对应的工艺卡
     */
    @PostMapping(value = "/findIngCardByDeviceId")
    //@RequiresPermissions("injectionMolding:moveUpDownCancel")
    public Result findIngCardByDeviceId(@RequestBody DevicesItemVo devicesItemVo) {
        return craftCardServiceImpl.findIngCardByDeviceId(devicesItemVo);
    }

    /**
     * 设定值修改记录
     */
    @GetMapping(value = "/findUpdateParamLog")
    //@RequiresPermissions("injectionMolding:moveUpDownCancel")
    public Result findUpdateParamLog(@RequestParam(value = "deviceCode",required = false) String deviceCode,
                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        return craftCardServiceImpl.findUpdateParamLog(deviceCode,pageNum,pageSize);
    }

    /**
     * 根据id查询工艺卡参数
     */
    @GetMapping(value = "/findById")
    //@RequiresPermissions("injectionMolding:moveUpDownCancel")
    public Result findById(@RequestParam(value = "id") Integer id) {
        return craftCardServiceImpl.findById(id);
    }

    /**
     * 根据id查询工艺卡变更记录参数
     */
    @GetMapping(value = "/findCardLogById")
    //@RequiresPermissions("injectionMolding:moveUpDownCancel")
    public Result findCardLogById(@RequestParam(value = "id") Integer id) {
        return craftCardServiceImpl.findCardLogById(id);
    }
}
