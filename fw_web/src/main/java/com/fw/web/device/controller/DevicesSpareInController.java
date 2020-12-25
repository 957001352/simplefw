package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesSpareIn;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesSpareInService;
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
 * Data:2020/10/27
 * Time:9:37
 * @Description:
 */
@RestController
@RequestMapping(value = "/devicesSpareInService")
@Api(description = "备品备件ruku！！！", value = "DevicesSpareInController")
public class DevicesSpareInController {

    @Autowired
    private DevicesSpareInService spareInService;

    /**
     * 新增备品备件入库
     *
     * @param devicesSpareIn
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增入库")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody DevicesSpareIn devicesSpareIn, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return spareInService.save(devicesSpareIn);
        }
        return result;
    }

    /**
     *查看备品出库信息
     *
     * @param id
     * @return
     */
    @ApiOperation("查看备品入库信息")
    @GetMapping("/getProductDevicesSpare")
    public Result getProductDevicesSpare(@RequestParam(value = "id") Integer id) {
        return spareInService.getDevicesSpareOut(id);
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
        return spareInService.findList(pageNum, pageSize);
    }

}
