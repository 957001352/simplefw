package com.fw.web.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceReworkMonitor;
import com.fw.entity.produce.ProduceReworkRecord;
import com.fw.web.produce.service.fbk.ProduceReworkRecordServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 二次加工报工记录
 *
 * @author lpsong
 * @since 2020-12-15
 */
@FeignClient(value = "fw-service/produceReworkRecord", fallback = ProduceReworkRecordServiceFbk.class)
public interface ProduceReworkRecordService {

    @PostMapping(value = "/save")
    Result save(@RequestBody ProduceReworkRecord produceReworkRecord);


    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "planReworkId", required = false) Integer planReworkId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 开始生产
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/startProduce")
    Result startProduce(@RequestBody ProduceReworkMonitor produceReworkMonitor);

    /**
     * 完成生产
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/endProduce")
    Result endProduce(@RequestBody ProduceReworkMonitor produceReworkMonitor);

    /**
     * 报检
     *
     * @param produceReworkMonitor
     * @return
     */
    @PostMapping(value = "/customsInspection")
    Result customsInspection(@RequestBody ProduceReworkMonitor produceReworkMonitor);
}
