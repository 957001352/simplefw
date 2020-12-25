package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceFeeding;
import com.fw.web.produce.service.ProduceFeedingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @des: 投料 前端控制器
 * @author: xkliu
 * @date: 2020/12/14
 */
@RequestMapping(value = "/produceFeeding")
@RestController
@Api(description = "投料", value = "ProduceFeedingController")
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
    public Result findList(@RequestParam(value = "productOrder", required = false) String productOrder,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceFeedingService.findList(productOrder, pageNum, pageSize);
    }


    /**
     *扫码获取出库数量
     *
     * @return
     */
    @GetMapping(value = "/getStorageCount")
    public Result getStorageCount(@RequestParam(value = "storageDetailId") Integer storageDetailId) {
        return produceFeedingService.getStorageCount(storageDetailId);
    }

}
