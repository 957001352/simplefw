package com.fw.service.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceReworkRecord;

/**
 * 二次加工报工记录
 * @author lpsong
 * @since 2020-12-15
 */
public interface ProduceReworkRecordService {

    Result save(ProduceReworkRecord produceReworkRecord);

    Result findList(Integer planReworkId, Integer pageNum, Integer pageSize);

}
