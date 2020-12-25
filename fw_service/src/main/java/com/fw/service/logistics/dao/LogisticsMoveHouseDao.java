package com.fw.service.logistics.dao;


import com.fw.entity.logistics.LogisticsMoveHouse;
import com.fw.entity.logistics.LogisticsMoveHouseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.bind.ValidationEvent;
import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface LogisticsMoveHouseDao {

    Integer insert(LogisticsMoveHouse logisticsMoveHouse);

    Integer insertDetail(List<LogisticsMoveHouseDetail> list);

    Integer updateMoveHouseStatus(@Param(value = "id") Integer id,
                                  @Param(value = "status") Integer status,
                                  @Param(value = "executeUser") Integer executeUser);

    List<LogisticsMoveHouse> findList(@Param(value = "moveHouseNo") String moveHouseNo,@Param(value = "status") Integer status);

    Integer delete(@Param(value = "ids") List<String> ids);

    Integer deleteDetail(@Param(value = "moveHouseId") Integer moveHouseId);

    List<LogisticsMoveHouse> findMoveHouseById(@Param(value = "ids") List<String> ids);

    String findCode(@Param(value = "code") String code);
}
