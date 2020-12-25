package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepTaskService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @des 设备保计划执行Feign接口调用失败
 * @date 2020/10/23
 */
@Service
public class DevicesKeepTaskServiceFbk implements DevicesKeepTaskService {

    @Override
    public Result findList(String keepOrder, String devicesCode, String taskStatus, Integer auditStatus, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result postPoned(String ids, String carryDay, String type, String nonExecution) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findHistoryList(String keepOrder, String devicesCode, String executeUserName, String executeTime, String taskStatus,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesTask(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result explainTask(DevicesKeepTask devicesKeepTask) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getUnfinishedTask(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesKeepTask(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}
