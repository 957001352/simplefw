package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMachineDown;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceMachineDownService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-14 15:32
 **/

@Service
public class ProduceMachineDownServiceFbk implements ProduceMachineDownService {
    @Override
    public Result save(ProduceMachineDown produceMachineDown) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String ofNo, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
