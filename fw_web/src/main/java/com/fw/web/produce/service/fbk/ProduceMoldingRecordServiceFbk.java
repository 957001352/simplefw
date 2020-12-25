package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingRecord;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceMoldingRecordService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-14 14:21
 **/

@Service
public class ProduceMoldingRecordServiceFbk implements ProduceMoldingRecordService {
    @Override
    public Result save(ProduceMoldingRecord produceMoldingRecord) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String ofNo, String userName, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result callMaterialOrCastMaterial(String productDeviceCode, Integer callType, String CastType) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findCallAndCastList() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
