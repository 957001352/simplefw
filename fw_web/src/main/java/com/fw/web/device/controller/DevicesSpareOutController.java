package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareOut;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesSpareOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/10/22
 * Time:15:30
 * @Description:
 */
@RestController
@RequestMapping(value = "/devicesSpareOutService")
@Api(description = "备品备件出库！！！", value = "DevicesSpareOutController")
public class DevicesSpareOutController {

    @Autowired
    private DevicesSpareOutService spareOutService;

    /**
     * 新增备品备件出库
     *
     * @param devicesSpareOut
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增出库")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody DevicesSpareOut devicesSpareOut, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return spareOutService.save(devicesSpareOut);
        }
        return result;
    }

    /**
     *查看备品出库信息
     *
     * @param id
     * @return
     */
    @ApiOperation("查看备品出库信息")
    @GetMapping("/getProductDevicesSpare")
    public Result getProductDevicesSpare(@RequestParam(value = "id") Integer id) {
        return spareOutService.getDevicesSpareOut(id);
    }

    /**
     * 列表全查
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result FindList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return spareOutService.findList(pageNum, pageSize);
    }
}