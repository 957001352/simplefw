package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesKeepTask;
import com.fw.entity.mould.MouldKeepTask;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesKeepTaskService;
import com.fw.web.mould.service.MouldKeepTaskService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @des 模具保计划执行Feign接口调用失败
 * @date 2020/10/28
 */
@Service
public class MouldKeepTaskServiceFbk implements MouldKeepTaskService {

    @Override
    public Result findList(String keepOrder, String mouldCode, String taskStatus, Integer auditStatus, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result postPoned(String ids, String carryDay, String type, String nonExecution) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findHistoryList(String keepOrder, String mouldCode, String executeUserName, String executeTime, String taskStatus,Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getMouldTask(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result explainTask(MouldKeepTask MouldKeepTask) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getUnfinishedTask(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getMouldKeepTask(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}
