package com.fw.service.produce.service;

import com.fw.domain.Result;
import com.fw.entity.produce.ProduceDuty;
import com.fw.entity.produce.ProduceShift;

public interface ProduceShiftService {

    Result save(ProduceShift produceShift);

    Result findList(Integer planMoldingId,Integer pageNum, Integer pageSize);
}
