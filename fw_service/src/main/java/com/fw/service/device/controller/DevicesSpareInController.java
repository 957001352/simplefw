package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareIn;
import com.fw.service.device.service.DevicesSpareInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther :yangwang
 * Data:2020/10/27
 * Time:9:26
 * @Description: 备品备件入库controller
 */
@RestController
@RequestMapping("/devicesSpareInService")
public class DevicesSpareInController {


    @Autowired
    private DevicesSpareInService spareInService;

    /**
     * 新增备品备件入库
     *
     * @param devicesSpareIn
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody DevicesSpareIn devicesSpareIn) throws Exception {
        return spareInService.insert(devicesSpareIn);
    }

    /**
     * 查看备品入库信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesSpareIn")
    public Result getDevicesSpareOut(@RequestParam(value = "id") Integer id) {
        return spareInService.selectByIn(id);
    }

    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result FindList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return spareInService.findAll(pageNum, pageSize);
    }

}
