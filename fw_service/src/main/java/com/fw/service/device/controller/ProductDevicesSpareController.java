package com.fw.service.device.controller;

import com.fw.domain.Result;
import com.fw.entity.device.ProductDevicesSpare;
import com.fw.service.device.service.ProductDevicesSpareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther :yangwang
 * Data:2020/10/21
 * @Description: 备品备件控制器
 */
@RestController
@RequestMapping("/productDevicesSpare")
public class ProductDevicesSpareController {

    @Autowired
    private ProductDevicesSpareService productDevicesSpareService;

    /**
     * 新增和修改
     *
     * @param productDevicesSpare
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody ProductDevicesSpare productDevicesSpare) {
        return productDevicesSpareService.save(productDevicesSpare);
    }

    /**
     * 查看备品
     *
     * @param id
     * @return
     */
    @GetMapping("/getProductDevicesSpare")
    public Result getProductDevicesSpare(@RequestParam(value = "id") Integer id) {
        return productDevicesSpareService.getProductDevicesSpare(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return productDevicesSpareService.delete(ids);
    }

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result FindList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return productDevicesSpareService.findList(code,name,pageNum, pageSize);
    }
}
