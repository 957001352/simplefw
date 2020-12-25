package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesRepair;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesRepairService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-22 10:04
 **/
@Service
public class DevicesRepairServiceFbk implements DevicesRepairService {
    @Override
    public Result save(DevicesRepair devicesRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer id, String code, String productDevicesId, String devicesClassify, String priority, Integer repairProjectStatus, Integer repairExeStatus, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result makeProject(DevicesRepair devicesRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getTask(DevicesRepair devicesRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result repairTaskExecute(DevicesRepair devicesRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
