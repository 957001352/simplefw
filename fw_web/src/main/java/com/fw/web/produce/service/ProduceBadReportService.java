package com.fw.web.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBadReport;
import com.fw.web.produce.service.fbk.ProduceBadReportServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @des: 不良上报Feign接口
 * @author: xkliu
 * @date: 2020/12/09
 */
@FeignClient(value = "fw-service/produceBadReport", fallback = ProduceBadReportServiceFbk.class)
public interface ProduceBadReportService {

    /**
     * 新增/修改不良上报
     *
     * @param produceBadReport
     * @return
     */
    @PostMapping("/save")
    Result save(@RequestBody ProduceBadReport produceBadReport);

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/findList")
    @ApiOperation("列表查询")
    Result findList(@RequestParam(value = "id", required = false) Integer moldingId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}