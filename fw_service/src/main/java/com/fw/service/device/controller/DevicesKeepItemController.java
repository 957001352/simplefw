package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepItem;
import com.fw.service.device.service.DevicesKeepItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 设备保养项目控制器
 * @date 2020/10/20
 */
@RestController
@RequestMapping("/devicesKeepItem")
public class DevicesKeepItemController {

    @Autowired
    private DevicesKeepItemService devicesKeepItemService;

    /**
     * 新增/修改设备保养项目
     *
     * @param devicesKeepItem
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("devicesKeepItem:save")
    public Result save(@RequestBody DevicesKeepItem devicesKeepItem) {
        return devicesKeepItemService.save(devicesKeepItem);
    }

    /**
     * 查看设备保养项目
     *
     * @param id
     * @return
     */
    @GetMapping("/getDevicesKeepItem")
    @RequiresAuthentication
    public Result getDevicesKeepItem(@RequestParam(value = "id") Integer id) {
        return devicesKeepItemService.getDevicesKeepItem(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("devicesKeepItem:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesKeepItemService.delete(ids);
    }

    /**
     * 列表查询
     *
     * @param name
     * @param devicesClassify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "devicesClassify", required = false) String devicesClassify,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesKeepItemService.findList(name, devicesClassify, pageNum, pageSize);
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    @GetMapping("/getDevicesClassify")
    @RequiresAuthentication
    public Result getDevicesClassify() {
        return devicesKeepItemService.getDevicesClassify();
    }
}
