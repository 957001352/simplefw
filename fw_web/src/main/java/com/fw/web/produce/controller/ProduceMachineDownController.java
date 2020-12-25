package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMachineDown;
import com.fw.web.produce.service.ProduceMachineDownService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-14 15:33
 **/

@RestController
@RequestMapping("/produceMachineDown")
@Api(description = "生产停机记录", value = "ProduceMachineDownController")
public class ProduceMachineDownController {

    @Autowired
    private ProduceMachineDownService produceMachineDownService;


    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceMachineDown produceMachineDown) {
        return produceMachineDownService.save(produceMachineDown);
    }

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/findList")
    Result findList(
            @RequestParam(value = "ofNo", required = false) String ofNo,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceMachineDownService.findList(ofNo,pageNum,pageSize);
    }
}
