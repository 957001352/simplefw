package com.fw.service.quality.controller;


import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspection;
import com.fw.service.quality.service.QualityInspectionService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 检验规范
 *
 * @author xkliu
 * @since 2020-11-24
 */
@RestController
@RequestMapping(value = "/qualityInspection")
public class QualityInspectionController {

    @Autowired
    private QualityInspectionService qualityInspectionService;

    /**
     * 新增检验规范
     *
     * @param qualityInspection
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("qualityInspection:save")
    public Result save(@RequestBody QualityInspection qualityInspection) {
        return qualityInspectionService.save(qualityInspection);
    }

    /**
     * 变更检验规范
     *
     * @param qualityInspection
     * @return
     */
    @PostMapping("/change")
    @RequiresPermissions("qualityInspection:change")
    public Result change(@RequestBody QualityInspection qualityInspection) {
        return qualityInspectionService.change(qualityInspection);
    }

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
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "preciseCode", required = false) String preciseCode,
                           @RequestParam(value = "productName", required = false) String productName,
                           @RequestParam(value = "classify", required = false) Integer classify,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return qualityInspectionService.findList(code, preciseCode, productName, classify, pageNum, pageSize);
    }


    /**
     * 查看检验规范详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getInspectionDetail")
    @RequiresAuthentication
    public Result getInspectionDetail(@RequestParam(value = "id") Integer id) {
        return qualityInspectionService.getInspectionDetail(id);
    }

    /**
     * 查看检验规范变更记录
     *
     * @param id
     * @return
     */
    @GetMapping("/getInspectionChangeLog")
    @RequiresAuthentication
    public Result getInspectionChangeLog(@RequestParam(value = "id") Integer id) {
        return qualityInspectionService.getInspectionChangeLog(id);
    }

    /**
     * 检验规范变更通过
     */
    @GetMapping("/inspectionChangePass")
    public Result inspectionChangePass(@RequestParam(value = "id") Integer id) {
        return qualityInspectionService.inspectionChangePass(id);
    }
}
