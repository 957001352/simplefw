package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBadReport;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceBadReportService;
import org.springframework.stereotype.Service;

/**
 * @des: 不良上报Feign接口调用失败
 * @author: xkliu
 * @date: 2020/12/09
 */
@Service
public class ProduceBadReportServiceFbk implements ProduceBadReportService {

    @Override
    public Result save(ProduceBadReport produceBadReport) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer moldingId, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
