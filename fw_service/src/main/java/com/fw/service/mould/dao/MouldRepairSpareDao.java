package com.fw.service.mould.dao;

import com.fw.entity.mould.MouldRepairSpare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouldRepairSpareDao {

    public Integer insert(MouldRepairSpare mouldRepairSpare);

    public List<MouldRepairSpare> findMouldSpareByMouldRepaiID(@Param(value = "mouldRepairSpareId") Integer mouldRepairSpareId);

    public Integer updateMouldSpare(MouldRepairSpare mouldRepairSpare);

    public MouldRepairSpare findMouldSpare(@Param(value = "mouldRepairId") Integer mouldRepairId,
                                           @Param(value = "mouldSpareId") Integer mouldSpareId);
    public Integer insertAll(List<MouldRepairSpare> list);
    public Integer deleteByMouldRepairId(@Param(value = "mouldRepairId") Integer mouldRepairId);

}
