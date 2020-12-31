package com.fw.service.device.controller;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepairItem;
import com.fw.service.device.service.DevicesRepairItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description: 维修设备控制层
 * @author: wqiang
 * @create: 2020-10-21 09:17
 **/
@RestController
@RequestMapping("/devicesRepairItem")
public class DevicesRepairItemController {


    @Autowired
    private DevicesRepairItemService devicesRepairItemService;

    /**
     * 新增或修改
     * @param devicesRepairItem
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions(value = "devicesRepairItem:save")
    public Result save(@RequestBody DevicesRepairItem devicesRepairItem){
        return devicesRepairItemService.save(devicesRepairItem);
    }


    /**
     * 获取列表
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
        return devicesRepairItemService.findList(name, devicesClassify, pageNum, pageSize);
    }

    /**
     * 获取设备列表
     * @return
     */
    @GetMapping("/findDecicesList")
    @RequiresAuthentication
    public Result findDecicesList() {
        return devicesRepairItemService.findDecicesList();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions(value="devicesRepairItem:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesRepairItemService.delete(ids);
    }
}
