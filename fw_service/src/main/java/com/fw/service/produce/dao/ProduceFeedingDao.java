package com.fw.service.produce.dao;

import com.fw.entity.produce.ProduceFeeding;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 投料 Dao 接口
 *
 * @author xkliu
 * @since 2020-12-14
 */
@Repository
public interface ProduceFeedingDao {

    /**
     * 新增
     *
     * @param produceFeeding
     * @return
     */
    Integer insert(ProduceFeeding produceFeeding);

    /**
     * 列表查询
     *
     * @param productOrder
     * @return
     */
    List<ProduceFeeding> findList(String productOrder);

    /**
     * 单号查询
     *
     * @param
     * @return
     */
    String findCode(String code);

    /**
     * 根据生产指令获取ProduceFeeding对象
     *
     * @param productOrder
     * @return
     */
    ProduceFeeding getProduceFeeding(String productOrder);

}
