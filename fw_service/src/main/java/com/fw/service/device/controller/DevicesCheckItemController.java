package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.service.device.service.DevicesCheckItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/devicesCheckItem")
public class DevicesCheckItemController {

    @Autowired
    private DevicesCheckItemService devicesCheckItemService;

    /**
     * 新增/修改设备保养项目
     *
     * @param devicesCheckItem
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresPermissions("devicesCheckItem:save")
    public Result save(@RequestBody DevicesCheckItem devicesCheckItem) {
        return devicesCheckItemService.save(devicesCheckItem);
    }


    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("devicesCheckItem:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return devicesCheckItemService.delete(ids);
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
                           @RequestParam(value = "content", required = false) String content,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return devicesCheckItemService.findList(name, devicesClassify,content, pageNum, pageSize);
    }
}
