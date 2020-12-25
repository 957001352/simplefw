package com.fw.web.logistics.controller;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsScrap;
import com.fw.web.logistics.service.LogisticsScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-16 10:41
 **/

@RestController
@RequestMapping("/logisticsScrap")
@Api(description = "报废管理", value = "LogisticsScrapController")
public class LogisticsScrapController {

    @Autowired
    private LogisticsScrapService logisticsScrapService;

    @ApiOperation("新增/更新")
    @PostMapping(value = "save")
    public Result save(@RequestBody LogisticsScrap logisticsScrap) {
        return logisticsScrapService.save(logisticsScrap);
    }

    @ApiOperation("获取列表")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "productCode",required = false) String productCode,
                           @RequestParam(value = "productName",required = false) String productName,
                           @RequestParam(value = "productOrder",required = false) String productOrder,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize

    ) {
        return logisticsScrapService.findList(productCode, productName, productOrder, pageNum, pageSize);
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return logisticsScrapService.delete(ids);
    }
}
