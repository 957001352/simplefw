package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceReworkMonitor;
import com.fw.entity.produce.ProduceReworkRecord;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceReworkRecordService;
import org.springframework.stereotype.Service;

/**
 * 二次加工报工记录
 * @author lpsong
 * @since 2020-12-15
 */
@Service
public class ProduceReworkRecordServiceFbk implements ProduceReworkRecordService {
    @Override
    public Result save(ProduceReworkRecord produceReworkRecord) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer planReworkId, Integer pageNum, Integer pageSize) {
            return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result startProduce(ProduceReworkMonitor produceReworkMonitor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result endProduce(ProduceReworkMonitor produceReworkMonitor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result customsInspection(ProduceReworkMonitor produceReworkMonitor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
