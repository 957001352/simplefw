package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;

public interface ProduceDutyService {


    Result save(ProduceDuty produceDuty);

    Result findList(Integer pageNum, Integer pageSize);
}
