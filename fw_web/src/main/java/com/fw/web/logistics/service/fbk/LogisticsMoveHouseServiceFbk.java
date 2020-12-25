package com.fw.web.logistics.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsMoveHouse;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.logistics.service.LogisticsMoveHouseService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-11-13 11:31
 **/

@Service
public class LogisticsMoveHouseServiceFbk implements LogisticsMoveHouseService {
    @Override
    public Result save(LogisticsMoveHouse logisticsMoveHouse) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result updateMoveHouseStatus(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String moveHouseNo, Integer status, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
    @Override
    public Result delete(@RequestParam(value = "ids") String ids){
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
