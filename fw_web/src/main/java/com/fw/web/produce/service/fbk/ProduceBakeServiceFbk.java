package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBake;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceBakeService;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk_fw_plat
 * @description:
 * @author: wqiang
 * @create: 2020-12-08 17:54
 **/
@Service
public class ProduceBakeServiceFbk implements ProduceBakeService {
    @Override
    public Result save(ProduceBake produceBake) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
