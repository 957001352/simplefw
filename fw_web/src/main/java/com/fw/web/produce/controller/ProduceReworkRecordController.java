package com.fw.web.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceReworkMonitor;
import com.fw.entity.produce.ProduceReworkRecord;
import com.fw.web.produce.service.ProduceReworkRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 二次加工报工记录
 *
 * @author lpsong
 * @since 2020-12-15
 */
@RestController
@RequestMapping("/produceReworkRecord")
@Api(description = "二次加工报工记录", value = "ProduceReworkRecordController")
public class ProduceReworkRecordController {

    @Autowired
    private ProduceReworkRecordService produceReworkRecordService;


    @ApiOperation(value = "报工")
    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceReworkRecord produceReworkRecord) {
        return produceReworkRecordService.save(produceReworkRecord);
    }

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "planReworkId", required = false) Integer planReworkId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return produceReworkRecordService.findList(planReworkId, pageNum, pageSize);
    }

    /**
     * 开始生产
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/startProduce")
    @ApiOperation(value = "开始生产")
    public Result startProduce(@RequestBody ProduceReworkMonitor produceReworkMonitor) {
        return produceReworkRecordService.startProduce(produceReworkMonitor);
    }

    /**
     * 完成生产
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/endProduce")
    @ApiOperation(value = "完成生产")
    public Result endProduce(@RequestBody ProduceReworkMonitor produceReworkMonitor) {
        return produceReworkRecordService.endProduce(produceReworkMonitor);
    }

    /**
     * 报检
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/customsInspection")
    @ApiOperation(value = "报检")
    public Result customsInspection(@RequestBody ProduceReworkMonitor produceReworkMonitor) {
        return produceReworkRecordService.customsInspection(produceReworkMonitor);
    }
}
