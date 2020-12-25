package com.fw.service.produce.service;


import com.fw.domain.Result;
import com.fw.entity.produce.ProduceMoldingRecord;

/**
 * <p>
 * 生产报工记录 服务类
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
public interface ProduceMoldingRecordService  {

    Result save(ProduceMoldingRecord produceMoldingRecord);

    Result findList(String ofNo,String userName,Integer pageNum, Integer pageSize);

    Result callMaterialOrCastMaterial(String productDeviceCode,Integer callType,String CastType);

    Result findCallAndCastList();

}
