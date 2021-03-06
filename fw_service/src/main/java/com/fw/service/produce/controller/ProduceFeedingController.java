package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBadReport;
import com.fw.entity.produce.ProduceFeeding;
import com.fw.service.produce.service.ProduceFeedingService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @des: 投料 前端控制器
 * @author: xkliu
 * @date: 2020/12/14
 */
@RequestMapping(value = "/produceFeeding")
@RestController
public class ProduceFeedingController {

    @Autowired
    private ProduceFeedingService produceFeedingService;


    /**
     * 新增投料
     *
     * @param produceFeeding
     * @return
     */
    @PostMapping("/save")
    @RequiresAuthentication
    public Result save(@RequestBody ProduceFeeding produceFeeding) {
        return produceFeedingService.save(produceFeeding);
    }


    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "productOrder", required = false) String productOrder,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceFeedingService.findList(productOrder,pageNum, pageSize);
    }


    /**
     *扫码获取出库数量
     *
     * @return
     */
    @GetMapping(value = "/getStorageCount")
    @RequiresAuthentication
    public Result getStorageCount(@RequestParam(value = "storageDetailId") Integer storageDetailId) {
        return produceFeedingService.getStorageCount(storageDetailId);
    }

}
