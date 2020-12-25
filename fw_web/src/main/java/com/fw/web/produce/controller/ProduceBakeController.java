package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBake;
import com.fw.web.produce.service.ProduceBakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 17:54
 **/

@RestController
@RequestMapping("/produceBake")
@Api(description = "烘烤时长", value = "ProduceBakeController")
public class ProduceBakeController {



    @Autowired
    private ProduceBakeService produceBakeService;

    @ApiOperation("保存")
    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceBake produceBake) {
        return produceBakeService.save(produceBake);
    }

    @ApiOperation("获取列表")
    @GetMapping(value = "/findList")
    Result findList( @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceBakeService.findList(pageNum,pageSize);
    }
}
