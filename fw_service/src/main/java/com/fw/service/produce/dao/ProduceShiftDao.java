package com.fw.service.produce.dao;


import com.fw.entity.produce.ProduceShift;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ProduceShiftDao {

    Integer insert(ProduceShift produceShift);

    List<ProduceShift> findList(@Param(value = "planMoldingId") Integer planMoldingId);
}
