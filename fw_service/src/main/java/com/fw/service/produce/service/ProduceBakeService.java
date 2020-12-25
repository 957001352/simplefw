package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceBake;
import com.fw.entity.produce.ProduceDuty;

public interface ProduceBakeService {

    Result save(ProduceBake produceBake);

    Result findList(Integer pageNum, Integer pageSize);
}
