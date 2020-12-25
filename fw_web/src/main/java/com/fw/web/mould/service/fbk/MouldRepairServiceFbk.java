package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldRepair;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldRepairService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-10-23 09:07
 **/
@Service
public class MouldRepairServiceFbk implements MouldRepairService {
    @Override
    public Result save(MouldRepair mouldRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer id, String code, String mouldId, String mouldName, String priority, Integer repairProjectStatus, Integer repairExeStatus, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result makeProject(MouldRepair mouldRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result repairTaskExecute(MouldRepair mouldRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getTask(MouldRepair mouldRepair) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
