package com.fw.service.mould.controller;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldRepairItem;
import com.fw.service.mould.service.MouldRepairItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-22 15:11
 **/
@RestController
@RequestMapping("/mouldRepairItem")
public class MouldRepairItemController {

    @Autowired
    private MouldRepairItemService mouldRepairItemService;

    /**
     * 新增或修改
     *
     * @param mouldRepairItem
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody MouldRepairItem mouldRepairItem) {
        return mouldRepairItemService.save(mouldRepairItem);
    }

    /**
     * 获取列表
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return mouldRepairItemService.findList(name, pageNum, pageSize);
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return mouldRepairItemService.delete(ids);
    }
}
