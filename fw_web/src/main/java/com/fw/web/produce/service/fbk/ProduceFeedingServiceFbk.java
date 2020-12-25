package com.fw.web.produce.service.fbk;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceFeeding;
import com.fw.enums.ResultEnum;
import com.fw.utils.ResultUtils;
import com.fw.web.produce.service.ProduceFeedingService;
import org.springframework.stereotype.Service;

/**
 * @des: 投料Feign接口调用失败
 * @author: xkliu
 * @date: 2020/12/14
 */
@Service
public class ProduceFeedingServiceFbk implements ProduceFeedingService {

    @Override
    public Result save(ProduceFeeding produceFeeding) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String productOrder, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getStorageCount(Integer storageDetailId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
