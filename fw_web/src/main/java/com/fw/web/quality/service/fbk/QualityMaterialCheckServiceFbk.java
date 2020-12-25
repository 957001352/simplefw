package com.fw.web.quality.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityMaterialCheckService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-25 17:35
 **/
@Service
public class QualityMaterialCheckServiceFbk implements QualityMaterialCheckService {
    @Override
    public Result findList(String checkNo, String startTime, String stopTime, String exeStartTime, String exeStopTime, Integer status, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getCheckoutMaterialInfoById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result submit(List<QualityInspectResult> qualityInspectResultList) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findResultByDataIdAndClassify(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
