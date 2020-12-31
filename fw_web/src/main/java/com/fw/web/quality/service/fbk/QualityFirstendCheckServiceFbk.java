package com.fw.web.quality.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityFirstendCheck;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityFirstendCheckService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-25 09:02
 **/



@Service
public class QualityFirstendCheckServiceFbk implements QualityFirstendCheckService {
    @Override
    public Result findList(Integer id,String productCode,String productDevicesCode, Integer checkType, String startTime, String stopTime,Integer status, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findAppearanceOrSizeInspectInfo(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result submit(List<QualityInspectResult> qualityInspectResultList) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(QualityInspectResult qualityInspectResult) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result coerceClose(QualityFirstendCheck qualityFirstendCheck) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result postponeExe(QualityFirstendCheck qualityFirstendCheck) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
