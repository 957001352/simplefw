package com.fw.web.quality.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.quality.QualityInspectResult;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.quality.service.QualityInspectResultService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-25 09:36
 **/

@Service
public class QualityInspectResultServiceFbk implements QualityInspectResultService {

    @Override
    public Result findHistoryResultByOfon(String ofNo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
