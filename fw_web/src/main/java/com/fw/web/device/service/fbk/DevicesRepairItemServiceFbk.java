package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepairItem;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesRepairItemService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-21 09:58
 **/
@Service
public class DevicesRepairItemServiceFbk implements DevicesRepairItemService {
    @Override
    public Result save(DevicesRepairItem devicesRepairItem) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, String devicesClassify, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findDecicesList() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
