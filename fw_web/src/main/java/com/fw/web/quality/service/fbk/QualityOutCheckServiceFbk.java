package com.fw.web.quality.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityOutCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-26 17:14
 **/
@Service
public class QualityOutCheckServiceFbk implements QualityOutCheckService {
    @Override
    public Result findList(String checkNo, String customer, String materialCode, String startTime, String stopTime, Integer status,Integer pageNum, Integer pageSize) {
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

    @Override
    public Result update(QualityInspectResult qualityInspectResultList) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
