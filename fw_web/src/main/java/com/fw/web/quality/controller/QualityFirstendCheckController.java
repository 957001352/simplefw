package com.fw.web.quality.controller;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityFirstendCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-25 09:03
 **/

@RestController
@RequestMapping(value = "/qualityFirstendCheck")
@Api(description = "首、末、巡检验管理", value = "QualityFirstendCheckController")
public class QualityFirstendCheckController {

    @Autowired
    private QualityFirstendCheckService qualityFirstendCheckService;


    @ApiOperation("获取列表")
    @GetMapping(value = "/findList")
    Result findList(
                    @RequestParam(value = "id", required = false) Integer id,
                    @RequestParam(value = "productCode", required = false) String productCode,
                    @RequestParam(value = "productDevicesCode", required = false) String productDevicesCode,
                    @RequestParam(value = "checkType", required = false) Integer checkType,
                    @RequestParam(value = "startTime", required = false) String startTime,
                    @RequestParam(value = "stopTime", required = false) String stopTime,
                    @RequestParam(value = "status",required = false)Integer status,
                    @RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = true,defaultValue = "10") Integer pageSize) {
        return qualityFirstendCheckService.findList(id,productCode,productDevicesCode,checkType,startTime,stopTime,status,pageNum,pageSize);

    }

    @ApiOperation("查询首末巡外观、尺寸质检结果信息")
    @GetMapping(value = "/findAppearanceOrSizeInspectInfo")
    Result findAppearanceOrSizeInspectInfo(@RequestParam(value = "id",required = true) Integer id){
        if(CheckUtils.isNull(id)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return qualityFirstendCheckService.findAppearanceOrSizeInspectInfo(id);
    }

    @ApiOperation("提交质检结果")
    @PostMapping(value = "/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityFirstendCheckService.submit(qualityInspectResultList);
    }

    @ApiOperation("更新质检结果")
    @PostMapping(value = "/update")
    Result update(@RequestBody QualityInspectResult qualityFirstendCheck){
        return qualityFirstendCheckService.update(qualityFirstendCheck);
    }

    /**
     * 巡检强制关闭
     * @param qualityFirstendCheck
     * @return
     */
    @ApiOperation("巡检强制关闭")
    @PostMapping(value = "/coerceClose")
    Result coerceClose(@RequestBody QualityFirstendCheck qualityFirstendCheck){
        return qualityFirstendCheckService.coerceClose(qualityFirstendCheck);

    }

    /**
     * 巡检延期执行
     * @param qualityFirstendCheck
     * @return
     */
    @ApiOperation("巡检延期执行")
    @PostMapping(value = "/postponeExe")
    Result postponeExe(@RequestBody QualityFirstendCheck qualityFirstendCheck){
        return qualityFirstendCheckService.postponeExe(qualityFirstendCheck);

    }
}
