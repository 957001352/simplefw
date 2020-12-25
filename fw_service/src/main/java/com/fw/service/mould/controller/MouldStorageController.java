package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldSpareIn;
import com.fw.entity.mould.MouldSpareOut;
import com.fw.entity.mould.MouldStorageInfo;
import com.fw.service.mould.service.MouldStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 模具备品备件库存管理
 * @Author: fchai
 * @Date: 2020/10/26 12:17
 */

@RestController
@RequestMapping("/mouldStorage")
public class MouldStorageController {


    @Autowired
    private MouldStorageService mouldStorageService;


    @PostMapping(value = "/insertSpareInfo")
    Result insertSpareInfo(@RequestBody MouldStorageInfo mouldStorageInfo){
        return mouldStorageService.insertSpareInfo(mouldStorageInfo);
    }

    @GetMapping(value = "/deleteSpareInfo")
    Result deleteSpareInfo(@RequestParam(value = "ids") String ids){
        return mouldStorageService.deleteSpareInfo(ids);
    }

    @GetMapping(value = "/findListSpareInfo")
    Result findListSpareInfo(@RequestParam(value = "code",required = false) String code,
                             @RequestParam(value = "name",required = false) String name,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        return mouldStorageService.findListSpareInfo(code,name,pageNum,pageSize);
    }
    /**
     * 入库
     *
     * @param mouldSpareIn
     * @return
     */
    @PostMapping(value = "/mouldIn")
    Result mouldIn(@RequestBody MouldSpareIn mouldSpareIn) {
        return mouldStorageService.mouldIn(mouldSpareIn);
    }

    /**
     * 出库
     * @param mouldSpareOut
     * @return
     */
    @PostMapping(value = "/mouldOut")
    Result mouldOut(@RequestBody MouldSpareOut mouldSpareOut) {
        return mouldStorageService.mouldOut(mouldSpareOut);
    }

    /**
     * 获取出入库详情
     * @param outInNo
     * @param operate
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/getInOutInfo")
    Result getInOutInfo(@RequestParam(value = "outInNo" ,required = false) String outInNo,
                        @RequestParam(value = "operate",required = false) Integer operate,
                        @RequestParam(value = "startDate",required = false) String startDate,
                        @RequestParam(value = "endDate", required = false)String endDate,
                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ){
        return mouldStorageService.getInOutInfo(outInNo,operate,startDate,endDate,pageNum,pageSize);
    }


    /**
     * 获取模具库存信息
     * @param name
     * @return
     */
    @GetMapping(value = "findMouldSpareStoreList")
    public Result findMouldSpareStoreList(@RequestParam(value = "name") String name){
        return mouldStorageService.findMouldSpareStoreList(name);
    }

    @GetMapping(value = "findMouldSpareStoreAndUse")
    public Result findMouldSpareStoreAndUse(@RequestParam(value = "code",required = false) String code){
        return mouldStorageService.findMouldSpareStoreAndUse(code);
    }


    @GetMapping(value = "findOutInDetail")
    public Result findOutInDetail(@RequestParam(value = "id") Integer id,@RequestParam(value = "operate") Integer operate){
        return mouldStorageService.findOutInDetail(id,operate);
    }

}
