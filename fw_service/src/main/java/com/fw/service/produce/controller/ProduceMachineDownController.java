package com.fw.service.produce.controller;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMachineDown;
import com.fw.entity.produce.ProduceMoldingRecord;
import com.fw.service.produce.service.ProduceMachineDownService;
import com.fw.service.produce.service.ProduceMoldingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 生产停机记录 前端控制器
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
@RestController
@RequestMapping("/produceMachineDown")
public class ProduceMachineDownController {

    @Autowired
    private ProduceMachineDownService produceMachineDownService;

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceMachineDown produceMachineDown) {
        return produceMachineDownService.save(produceMachineDown);
    }

    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "ofNo", required = false) String ofNo,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceMachineDownService.findList(ofNo,pageNum,pageSize);
    }

}
