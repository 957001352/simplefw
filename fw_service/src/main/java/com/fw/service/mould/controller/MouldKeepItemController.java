package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepItem;
import com.fw.service.mould.service.MouldKeepItemService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @des 模具保养项目
 * @date 2020/10/27
 */
@RestController
@RequestMapping(value = "/mouldKeepItem")
public class MouldKeepItemController {

    @Autowired
    private MouldKeepItemService mouldKeepItemService;

    /**
     * 新增/修改设备保养项目
     *
     * @param mouldKeepItem
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("mouldKeepItem:save")
    public Result save(@RequestBody MouldKeepItem mouldKeepItem) {
        return mouldKeepItemService.save(mouldKeepItem);
    }


    /**
     * 查看模具保养项目
     *
     * @param id
     * @return
     */
    @GetMapping("/getMouldKeepItem")
    @RequiresAuthentication
    public Result getMouldKeepItem(@RequestParam(value = "id") Integer id) {
        return mouldKeepItemService.getMouldKeepItem(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @RequiresPermissions("mouldKeepItem:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return mouldKeepItemService.delete(ids);
    }

    /**
     * 列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldKeepItemService.findList(name, pageNum, pageSize);
    }


}
