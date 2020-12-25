package com.fw.web.craft.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.craft.CardLog;
import com.fw.entity.craft.CardParams;
import com.fw.entity.e2c.DevicesItemVo;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.craft.service.CraftCardService;
import org.springframework.stereotype.Service;

@Service
public class CraftCardServiceFbk implements CraftCardService {
    @Override
    public Result save(CardParams cardParams) {
        return  ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return  ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String partCode, String partName,Integer productId,Integer pageNum,Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findListCard() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result saveCardLog(CardLog cardLog) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findCardLog(Integer cardLogId,Integer paramsId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findDeviceByPartCode(String partCode) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findIngCardByDeviceId(DevicesItemVo devicesItemVo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findUpdateParamLog(String deviceCode, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findCardLogById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
