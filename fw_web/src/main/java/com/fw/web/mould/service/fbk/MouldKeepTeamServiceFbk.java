package com.fw.web.mould.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.mould.MouldKeepTeam;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.mould.service.MouldKeepTeamService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @des 模具保养项目Feign接口调用失败
 * @date 2020/10/27
 */
@Service
public class MouldKeepTeamServiceFbk implements MouldKeepTeamService {

    @Override
    public Result save(MouldKeepTeam mouldKeepTeam) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getDevicesKeepTeam(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getAllKeepItem() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, String mouldCode, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getMouldCode() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getMouldKeepTeam(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getKeepItemByCycle(Integer cycle) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}
