package com.fw.service.produce.service;


import com.fw.domain.Result;

public interface ProduceMaterialMonitorService {

    Result findList(String productCode,
                    String productName,
                    String startTime,
                    String stopTime,
                    Integer timeType,Integer pageNum, Integer pageSize);
}
