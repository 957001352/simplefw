package com.fw.web.quality.service;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.fbk.QualityFirstendCheckServiceFbk;
import com.fw.web.quality.service.fbk.QualityInspectionServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "fw-service/qualityFirstendCheck", fallback = QualityFirstendCheckServiceFbk.class)
public interface QualityFirstendCheckService {


    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "productCode", required = false) String productCode,
                    @RequestParam(value = "productDevicesCode", required = false) String productDevicesCode,
                    @RequestParam(value = "checkType", required = false) Integer checkType,
                    @RequestParam(value = "startTime", required = false) String startTime,
                    @RequestParam(value = "stopTime", required = false) String stopTime,
                    @RequestParam(value = "status",required = false)Integer status,
                    @RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = true,defaultValue = "10") Integer pageSize);

    /**
     * 查询首末、巡检信息
     * @param id
     * @return
     */
    @GetMapping(value = "/findAppearanceOrSizeInspectInfo")
    Result findAppearanceOrSizeInspectInfo(@RequestParam(value = "id",required = true) Integer id);

    /**
     * 保存质检结果
     * @param qualityInspectResultList
     * @return
     */
    @PostMapping(value = "/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList);


    /**
     * 更新质检结果
     * @return
     */
    @PostMapping(value = "/update")
    Result update(@RequestBody  QualityInspectResult qualityInspectResult);


    /**
     * 强制关闭
     * @param qualityFirstendCheck
     * @return
     */
    @PostMapping(value = "/coerceClose")
    Result coerceClose(@RequestBody QualityFirstendCheck qualityFirstendCheck);

    /**
     * 延期执行
     * @param qualityFirstendCheck
     * @return
     */
    @PostMapping(value = "/postponeExe")
    Result postponeExe(@RequestBody QualityFirstendCheck qualityFirstendCheck);
}
