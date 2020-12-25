package com.fw.web.craft.service;


import com.fw.domain.Result;
import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CardParams;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.web.craft.service.fbk.CraftCardServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fw-service/craftCard", fallback = CraftCardServiceFbk.class)
public interface CraftCardService {

    @PostMapping(value = "/save")
    Result save(@RequestBody CardParams cardParams);


    @GetMapping(value = "/delete")
    Result delete(@RequestParam("ids") String ids);


    /**
     * 工艺卡参数列表查询
     * @param partCode
     * @param partName
     * @param productId
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "partCode",required = false)String partCode,
                    @RequestParam(value = "partName",required = false)String partName,
                    @RequestParam(value = "productId",required = false)Integer productId,
                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                    @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize);

    /**
     * 工艺卡列表
     */
    @GetMapping("/findListCard")
    Result findListCard();

    /**
     * 工艺卡变更
     */
    @PostMapping("/saveCardLog")
    Result saveCardLog(@RequestBody CardLog cardLog);

    /**
     * 查看工艺卡变更列表
     */
    @GetMapping("/findCardLog")
    Result findCardLog(@RequestParam(value = "cardLogId",required = false)Integer cardLogId,
                       @RequestParam(value = "paramsId",required = false)Integer paramsId);

    /**
     * 根据零件编码查询相关设备
     */
    @GetMapping("/findDeviceByPartCode")
    Result findDeviceByPartCode(@RequestParam(value = "partCode")String partCode);

    /**
     * 查询设备正在生产的生产指令所对应的工艺卡
     */
    @PostMapping(value = "/findIngCardByDeviceId")
    Result findIngCardByDeviceId(@RequestBody DevicesItemVo devicesItemVo);
    /**
     * 设定值修改记录
     */
    @GetMapping(value = "/findUpdateParamLog")
    Result findUpdateParamLog(@RequestParam(value = "deviceCode",required = false) String deviceCode,
                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);
    /**
     * 根据id查询工艺卡参数
     */
    @GetMapping(value = "/findById")
    Result findById(@RequestParam(value = "id") Integer id);

    /**
     * 根据id查询工艺卡变更记录参数
     */
    @GetMapping(value = "/findCardLogById")
    Result findCardLogById(@RequestParam(value = "id") Integer id);

}
