package com.fw.web.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesCheckItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@RestController
@RequestMapping(value = "/devicesCheckItem")
@Api(description = "设备点检项目", value = "DevicesCheckItemController")
public class DevicesCheckItemController {

    @Autowired
    private DevicesCheckItemService devicesCheckItemService;

    /**
     * 新增/修改设备保养项目
     *
     * @param devicesCheckItem
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增/修改设备点检项目")
    @PostMapping("/save")
    public Result save(@Valid @RequestBody DevicesCheckItem devicesCheckItem, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result == null) {
            return devicesCheckItemService.save(devicesCheckItem);
        }
        return result;
    }



    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesCheckItemService.delete(ids);
    }

    /**
     * 列表查询
     * @param name
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("设备点检列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "content", required = false) String content,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesCheckItemService.findList(name, devicesClassify, content,pageNum, pageSize);
    }


}
