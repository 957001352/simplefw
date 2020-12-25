package com.fw.service.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMachineDown;

/**
 * <p>
 * 生产停机记录 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
public interface ProduceMachineDownService {


    Result save(ProduceMachineDown produceMachineDown);

    Result findList(String ofNo,Integer pageNum, Integer pageSize);

}
