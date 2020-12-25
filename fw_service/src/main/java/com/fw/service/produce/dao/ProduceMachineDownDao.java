package com.fw.service.produce.dao;


import com.fw.entity.produce.ProduceMachineDown;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 生产停机记录 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-14
 */
@Repository
public interface ProduceMachineDownDao{

    Integer insert(ProduceMachineDown produceMachineDown);

    List<ProduceMachineDown> findList(@Param(value = "ofNo") String ofNo);

    Integer update(@Param("productDevicesCode") String productDevicesCode);

}
