package com.fw.service.craft.service;

import com.fw.domain.Result;
import com.fw.entity.craft.CraftModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CraftModelService {

    Result save(CraftModel craftModel);

    Result findList(String productCode, String productName, Integer modelType,Integer pageNum, Integer pageSize);

    Result delete(String ids);

    Result findByProductCode(String productCode);
}
