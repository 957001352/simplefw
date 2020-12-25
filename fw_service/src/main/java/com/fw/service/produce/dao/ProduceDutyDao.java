package com.fw.service.produce.dao;


import com.fw.entity.produce.ProduceDuty;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 安排员工 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2020-12-08
 */
@Repository
public interface ProduceDutyDao  {


    Integer insert(ProduceDuty produceDuty);

    List<ProduceDuty> findList();
}
