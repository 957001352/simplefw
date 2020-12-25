package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesRepairSpareService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-27 10:14
 **/
@Service
public class DevicesRepairSpareServiceFbk implements DevicesRepairSpareService {
    @Override
    public Result findSpareStoreAndUse(String code) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
