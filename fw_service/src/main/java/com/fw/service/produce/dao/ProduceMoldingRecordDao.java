package com.fw.service.produce.dao;


import com.fw.entity.produce.ProduceMoldingRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 生产报工记录 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
@Repository
public interface ProduceMoldingRecordDao  {


    Integer insert(ProduceMoldingRecord produceMoldingRecord);


    List<ProduceMoldingRecord> findList(@Param(value = "ofNo") String ofNo);
 }
