package com.fw.service.device.service;


import com.fw.domain.Result;
import com.fw.entity.device.DevicesProblem;

import java.util.List;

public interface DevicesProblemService {

    Result save(DevicesProblem devicesProblem);


    Result delete(String ids);

    Result findList(String name,String content,Integer status,Integer type, Integer pageNum, Integer pageSize);
}
