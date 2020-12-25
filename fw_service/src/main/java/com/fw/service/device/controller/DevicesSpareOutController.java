package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareOut;
import com.fw.service.device.service.DevicesSpareOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description: 备品备件出库controller
 */
@RestController
@RequestMapping("/devicesSpareOutService")
public class DevicesSpareOutController {

    @Autowired
    private DevicesSpareOutService spareOutService;

    /**
     * 新增备品备件出库
     *
     * @param devicesSpareOut
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody DevicesSpareOut devicesSpareOut) throws Exception {
        return spareOutService.insert(devicesSpareOut);
    }

    /**
     * 查看备品出库信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesSpareOut")
    public Result getDevicesSpareOut(@RequestParam(value = "id") Integer id) {
        return spareOutService.selectByOut(id);
    }

    @GetMapping("/findList")
    public Result FindList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return spareOutService.findAll(pageNum, pageSize);
    }
}
