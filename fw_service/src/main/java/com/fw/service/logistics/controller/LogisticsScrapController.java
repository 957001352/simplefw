package com.fw.service.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsScrap;
import com.fw.service.logistics.service.LogisticsScrapService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:报废管理
 * @author: wqiang
 * @create: 2020-12-16 10:33
 **/
@RestController
@RequestMapping(value = "/logisticsScrap")
public class LogisticsScrapController {

    @Autowired
    private LogisticsScrapService logisticsScrapService;

    @PostMapping(value = "save")
    @RequiresPermissions("logisticsScrap:save")
    public Result save(@RequestBody LogisticsScrap logisticsScrap) {
        return logisticsScrapService.save(logisticsScrap);
    }

    @GetMapping(value = "/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "productCode",required = false) String productCode,
                           @RequestParam(value = "productName",required = false) String productName,
                           @RequestParam(value = "productOrder",required = false) String productOrder,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize

    ) {
        return logisticsScrapService.findList(productCode, productName, productOrder, pageNum, pageSize);
    }

    @GetMapping(value = "/delete")
    @RequiresPermissions("logisticsScrap:delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return logisticsScrapService.delete(ids);
    }
}
