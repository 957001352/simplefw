package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBadReport;
import com.fw.service.produce.service.ProduceBadReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @des: 不良上报控制器
 * @author: xkliu
 * @date: 2020/12/08
 */
@RequestMapping(value = "/produceBadReport")
@RestController
public class ProduceBadReportController {

    @Autowired
    private ProduceBadReportService produceBadReportService;

    /**
     * 新增不良上报
     *
     * @param produceBadReport
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody ProduceBadReport produceBadReport) {
        return produceBadReportService.save(produceBadReport);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return produceBadReportService.delete(ids);
    }


    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "id", required = false) Integer moldingId,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceBadReportService.findList(moldingId,pageNum, pageSize);
    }
}
