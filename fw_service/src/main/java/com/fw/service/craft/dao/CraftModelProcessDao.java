package com.fw.service.craft.dao;

import com.fw.entity.craft.CraftModelProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CraftModelProcessDao {


    Integer insert(CraftModelProcess craftModelProcess);

    Integer insertAll(List<CraftModelProcess> list);

    Integer deleteByCraftModelId(@Param(value = "craftModelId") Integer craftModelId);

    Integer deleteByCraftModelIds(List<String> list);

}
