package com.fw.web.device.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.device.DevicesOutinDetail;
import com.fw.entity.device.DevicesOutinDetailDTO;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.device.service.DevicesOutinDetailService;
import org.springframework.stereotype.Service;

/**
 * @Auther :yangwang
 * Data:2020/10/22
 * @Description:
 */
@Service
public class DevicesOutinDetailServiceFbk implements DevicesOutinDetailService {


    @Override
    public Result findAll(String outOrInNo, String operate, String startTime, String stopTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
