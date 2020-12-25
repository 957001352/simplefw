package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckTeam;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesCheckTeamService;
import org.springframework.stereotype.Service;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-22
 */
@Service
public class DevicesCheckTeamServiceFbk implements DevicesCheckTeamService {

    @Override
    public Result save(DevicesCheckTeam devicesCheckTeam) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }


    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, String devicesClassify, String devicesCode, String checkItem, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findCheckItemListByDevicve(Integer devicesId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
