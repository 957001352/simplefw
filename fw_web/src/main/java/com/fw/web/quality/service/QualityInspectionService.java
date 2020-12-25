package com.fw.web.quality.service;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspection;
import com.fw.web.quality.service.fbk.QualityInspectionServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 检验规范 Feign接口
 *
 * @author xkliu
 * @since 2020-11-24
 */
@FeignClient(value = "fw-service/qualityInspection", fallback = QualityInspectionServiceFbk.class)
public interface QualityInspectionService {


    /**
     * 新增检验规范
     *
     * @param qualityInspection
     * @return
     */
    @PostMapping("/save")
    Result save(@Valid @RequestBody QualityInspection qualityInspection);

    /**
     * 变更检验规范
     *
     * @param qualityInspection
     * @return
     */
    @PostMapping("/change")
    Result change(@Valid @RequestBody QualityInspection qualityInspection);


    /**
     * 列表查询
     *
     * @param code
     * @param productName
     * @param classify
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "code", required = false) String code,
                    @RequestParam(value = "preciseCode", required = false) String preciseCode,
                    @RequestParam(value = "productName", required = false) String productName,
                    @RequestParam(value = "classify", required = false) Integer classify,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);


    /**
     * 查看检验规范详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getInspectionDetail")
    Result getInspectionDetail(@RequestParam(value = "id") Integer id);


    /**
     * 查看检验规范变更记录
     *
     * @param id
     * @return
     */
    @GetMapping("/getInspectionChangeLog")
    Result getInspectionChangeLog(@RequestParam(value = "id") Integer id);
}
