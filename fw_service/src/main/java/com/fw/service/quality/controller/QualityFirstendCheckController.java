package com.fw.service.quality.controller;
import com.fw.domain.Result;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.service.quality.service.QualityFirstendCheckService;
import com.fw.utils.CheckUtils;
import com.fw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首末检验管理 前端控制器
 * </p>
 *
 * @author wangqiang
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/qualityFirstendCheck")
public class QualityFirstendCheckController {

    @Autowired
    private QualityFirstendCheckService qualityFirstendCheckService;


    /**
     * 获取列表
     * @param productCode
     * @param productDevicesCode
     * @param checkType
     * @param startTime
     * @param stopTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "productCode", required = false) String productCode,
                    @RequestParam(value = "productDevicesCode", required = false) String productDevicesCode,
                    @RequestParam(value = "checkType", required = false) Integer checkType,
                    @RequestParam(value = "startTime", required = false) String startTime,
                    @RequestParam(value = "stopTime", required = false) String stopTime,
                    @RequestParam(value = "status",required = false)Integer status,
                    @RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = true,defaultValue = "10") Integer pageSize) {
        return qualityFirstendCheckService.findList(productCode,productDevicesCode,checkType,startTime,stopTime,status,pageNum,pageSize);

    }

    /**
     *获取首、末、巡质检结果信息
     * @param id
     * @return
     */
    @GetMapping(value = "/findAppearanceOrSizeInspectInfo")
    Result findAppearanceOrSizeInspectInfo(@RequestParam(value = "id",required = true) Integer id){
        if(CheckUtils.isNull(id)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return qualityFirstendCheckService.findAppearanceOrSizeInspectInfo(id);
    }

    /**
     * 提交检验结果
     * @param qualityInspectResultList
     * @return
     */
    @PostMapping(value = "/submit")
    Result submit(@RequestBody List<QualityInspectResult> qualityInspectResultList){
        return qualityFirstendCheckService.submit(qualityInspectResultList);
    }

    /**
     * 更新质检结果
     * @param qualityInspectResult
     * @return
     */
    @PostMapping(value = "/update")
    Result update(@RequestBody QualityInspectResult qualityInspectResult){
        return qualityFirstendCheckService.update(qualityInspectResult);
    }


    /**
     * 强制关闭
     * @param qualityFirstendCheck
     * @return
     */
    @PostMapping(value = "/coerceClose")
    Result coerceClose(@RequestBody QualityFirstendCheck qualityFirstendCheck){
        return qualityFirstendCheckService.coerceClose(qualityFirstendCheck);

    }

    /**
     * 延期执行
     * @param qualityFirstendCheck
     * @return
     */
    @PostMapping(value = "/postponeExe")
    Result postponeExe(@RequestBody QualityFirstendCheck qualityFirstendCheck){
        return qualityFirstendCheckService.postponeExe(qualityFirstendCheck);

    }


    /**
     * 审核后回调的强制关闭方法
     * 1 延期执行 2 强制关闭 3 执行完成
     * @param id
     * @return
     */
    @GetMapping("updateCoerceCloseStatus")
    Result updateCoerceCloseStatus( @RequestParam(value = "id") Integer id){
        return qualityFirstendCheckService.updateCoerceCloseStatus(id);
    }


    /**
     * 审核后回调的延期执行方法
     * 1 延期执行 2 强制关闭 3 执行完成
     * @param id
     * @return
     */
    @GetMapping("updatepostponeExeStatus")
    Result updatepostponeExeStatus(@RequestParam(value = "id") Integer id){
        return qualityFirstendCheckService.updatepostponeExeStatus(id);
    }

}
