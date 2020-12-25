package com.fw.service.logistics.service;

import com.fw.domain.Result;
import com.fw.entity.logistics.LogisticsMoveHouse;

import java.util.List;

public interface LogisticsMoveHouseService {


    Result save(LogisticsMoveHouse logisticsMoveHouse);

    Result updateMoveHouseStatus(Integer id);

    Result findList(String moveHouseNo,Integer status,Integer pageNum, Integer pageSize);

    Result delete(String ids);
}
