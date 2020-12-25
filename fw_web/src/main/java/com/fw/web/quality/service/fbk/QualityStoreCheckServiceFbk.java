package com.fw.web.quality.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityStoreCheckService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-26 11:03
 **/

@Service
public class QualityStoreCheckServiceFbk implements QualityStoreCheckService {
    @Override
    public Result findList(String productDevicesCode, String checkNo, String productCode, String materialCode, String startTime, String stopTime, Integer status,Integer pageNum, Integer pageSize) {
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
    public Result update(QualityInspectResult qualityInspectResultList) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
