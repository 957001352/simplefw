package com.fw.service.produce.controller;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceReworkMonitor;
import com.fw.entity.produce.ProduceReworkRecord;
import com.fw.service.produce.service.ProduceReworkMonitorService;
import com.fw.service.produce.service.ProduceReworkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 二次加工报工记录
 *
 * @author lpsong
 * @since 2020-12-15
 */
@RestController
@RequestMapping(value = "/produceReworkRecord")
public class ProduceReworkRecordController {

    @Autowired
    private ProduceReworkRecordService produceReworkRecordService;

    @Autowired
    private ProduceReworkMonitorService produceReworkMonitorService;


    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceReworkRecord produceReworkRecord) {
        return produceReworkRecordService.save(produceReworkRecord);
    }

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
    public Result startProduce(@RequestBody ProduceReworkMonitor produceReworkMonitor) {
        return produceReworkMonitorService.startProduce(produceReworkMonitor);
    }

    /**
     * 完成生产
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/endProduce")
    public Result endProduce(@RequestBody ProduceReworkMonitor produceReworkMonitor) {
        return produceReworkMonitorService.endProduce(produceReworkMonitor);
    }

    /**
     * 报检
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/customsInspection")
    public Result customsInspection(@RequestBody ProduceReworkMonitor produceReworkMonitor) {
        return produceReworkMonitorService.customsInspection(produceReworkMonitor);
    }


}
