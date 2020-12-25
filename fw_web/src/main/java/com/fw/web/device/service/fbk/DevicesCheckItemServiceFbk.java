package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesCheckItem;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesCheckItemService;
import org.springframework.stereotype.Service;

/**
 * 设备点检项目
 * @author lpsong
 * @since 2020-10-21
 */
@Service
public class DevicesCheckItemServiceFbk implements DevicesCheckItemService {

    @Override
    public Result save(DevicesCheckItem devicesCheckItem) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, String devicesClassify,String content, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}
